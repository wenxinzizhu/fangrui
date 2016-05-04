package com.hbfangrui.base.infra.jpa.gen.processor;


import com.hbfangrui.base.infra.jpa.gen.annotation.OrderBy;
import com.hbfangrui.base.infra.jpa.gen.annotation.QueryIndex;
import com.hbfangrui.base.infra.jpa.gen.annotation.QueryIndexes;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.*;

abstract class AbstractClassGenerator implements ClassGenerator {
    private final String pkgName;
    private final String clsName;
    private final QueryIndex[] indexes;
    private final boolean async;
    private final TypeMirror beanType;
    private final Map<String, TypeMirror> filedTypeMap = new HashMap<String, TypeMirror>();

    AbstractClassGenerator(Elements elementUtils, TypeElement element, QueryIndexes indexes) {
        this.pkgName = indexes.pkgName();
        this.clsName = indexes.clsName();
        this.indexes = indexes.value();
        this.beanType = element.asType();
        this.async = indexes.async();

        TypeElement typeElement = element;
        while (typeElement != null){
            for (Element e : elementUtils.getAllMembers(typeElement)) {
                if (e.getKind() == ElementKind.FIELD) {
                    filedTypeMap.put(e.getSimpleName().toString(), e.asType());
                }
            }
            TypeMirror mirror = typeElement.getSuperclass();
            if (mirror instanceof DeclaredType) {
                typeElement = (TypeElement) ((DeclaredType) mirror).asElement();
            }else {
                typeElement = null;
            }
        }
    }

    protected List<String> getDependencyTypes() {
        List<String> str = new ArrayList<String>();
        str.addAll(getCommonDependencyTypes(this.async));

        str.add(this.beanType.toString());

        str.addAll(getFiledDependencyTypes());

        return str;
    }

    private List<String> getFiledDependencyTypes() {
        List<String> str = new ArrayList<String>();
        for (TypeMirror type : filedTypeMap.values()) {
            String typeCls = type.toString();
            if (!typeCls.startsWith("java.lang")) {
                str.add(type.toString());
            }
        }
        return str;
    }

    protected abstract List<String> getCommonDependencyTypes(boolean async);

    protected String getSuperClass(TypeMirror beanType) {
        return null;
    }

    protected String getClsName(String clsName){
        return clsName;
    }

    public String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(createPackage());
        stringBuilder.append("\n\r");
        stringBuilder.append("\n\r");
        stringBuilder.append(createImports());
        stringBuilder.append("\n\r");
        stringBuilder.append(appendClass()).append("{");
        stringBuilder.append("\n\r");
        stringBuilder.append(appendMethods());
        stringBuilder.append("}");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    private String appendMethods() {
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> methods = new LinkedHashSet<String>();
        for (QueryIndex index : indexes) {
            for (int i = 0; i < index.fields().length; i++) {
                String[] fs = new String[i + 1];
                System.arraycopy(index.fields(), 0, fs, 0, i + 1);
                if (i== index.fields().length -1) {
                    if (index.unique()){
                        methods.add(createGetUniqueMethod(fs, this.async));
                    }else if (index.orderBy().length > 0) {
                        methods.add(createGetMethod(fs, this.async));
                        for (OrderBy orderBy : index.orderBy()) {
                            String method = createGetOrderByMethod(fs, this.async, orderBy);
                            if (method != null) {
                                methods.add(method);
                            }
                        }
                        methods.add(createCountMethod(fs, this.async));
                    }else {
                        methods.add(createCountMethod(fs, this.async));
                        methods.add(createGetMethod(fs, this.async));
                    }
                }else {
                    methods.add(createGetMethod(fs, this.async));
                    methods.add(createCountMethod(fs, this.async));
                }

            }
        }
        for (String method : methods) {
            stringBuilder.append("    ");
            stringBuilder.append(method);
            stringBuilder.append("\n\r");
        }
        return stringBuilder.toString();
    }


    private String appendClass() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("public interface ").append(getClsName(this.clsName));
        String superCls = getSuperClass(this.beanType);
        if (superCls != null) {
            stringBuilder.append(" extends ").append(superCls);
        }
        return stringBuilder.toString();
    }


    private String createImports() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String iType : getDependencyTypes()) {
            stringBuilder.append("import ").append(iType).append(";");
            stringBuilder.append("\n\r");
        }
        return stringBuilder.toString();
    }

    private String createPackage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package ").append(this.pkgName).append(";");
        return stringBuilder.toString();
    }

    private String createCountMethod(String[] fs, boolean async) {
        String method = "countBy" + createBy(fs);
        StringBuilder methodBuilder = new StringBuilder();
        if (async) {
            methodBuilder.append("CompletableFuture<Integer> ").append(method).append("(");
        } else {
            methodBuilder.append("Integer ").append(method).append("(");
        }
        for (int j = 0; j < fs.length; j++) {
            String fName = fs[j];
            TypeMirror typeMirror = this.filedTypeMap.get(fName);
            methodBuilder.append(getName(typeMirror.toString())).append(" ").append(fName);
            if (j != fs.length - 1) {
                methodBuilder.append(", ");
            }
        }
        methodBuilder.append(");");
        return methodBuilder.toString();
    }

    private String createGetMethod(String[] fs, boolean async) {
        String method = "getBy" + createBy(fs);
        StringBuilder methodBuilder = new StringBuilder();
        if (async) {
            methodBuilder.append("CompletableFuture<Page<").append(getName(this.beanType.toString())).append(">> ").append(method).append("(");
        } else {
            methodBuilder.append("Page<").append(getName(this.beanType.toString())).append("> ").append(method).append("(");
        }
        for (int j = 0; j < fs.length; j++) {
            String fName = fs[j];
            TypeMirror typeMirror = this.filedTypeMap.get(fName);
            methodBuilder.append(getName(typeMirror.toString())).append(" ").append(fName);
            methodBuilder.append(", ");
        }
        methodBuilder.append("Pageable pageable").append(");");
        return methodBuilder.toString();
    }

    private String createGetOrderByMethod(String[] fs, boolean async, OrderBy orderBy) {
        if (fs.length < 2) {
            return null;
        }
        String method = "getBy" + createByOrder(fs, orderBy);
        StringBuilder methodBuilder = new StringBuilder();
        if (async) {
            methodBuilder.append("CompletableFuture<Page<").append(getName(this.beanType.toString())).append(">> ").append(method).append("(");
        } else {
            methodBuilder.append("Page<").append(getName(this.beanType.toString())).append("> ").append(method).append("(");
        }
        for (int j = 0; j < fs.length - 1; j++) {
            String fName = fs[j];
            TypeMirror typeMirror = this.filedTypeMap.get(fName);
            methodBuilder.append(getName(typeMirror.toString())).append(" ").append(fName);
            methodBuilder.append(", ");
        }
        methodBuilder.append("Pageable pageable").append(");");
        return methodBuilder.toString();
    }

    private String createGetUniqueMethod(String[] fs, boolean async) {
        String method = "getBy" + createBy(fs);
        StringBuilder methodBuilder = new StringBuilder();
        if (async) {
            methodBuilder.append("CompletableFuture<Optional<").append(getName(this.beanType.toString())).append(">> ").append(method).append("(");
        } else {
            methodBuilder.append("Optional<").append(getName(this.beanType.toString())).append("> ").append(method).append("(");
        }
        for (int j = 0; j < fs.length; j++) {
            String fName = fs[j];
            TypeMirror typeMirror = this.filedTypeMap.get(fName);
            methodBuilder.append(getName(typeMirror.toString())).append(" ").append(fName);
            if (j != fs.length - 1) {
                methodBuilder.append(", ");
            }
        }
        methodBuilder.append(");");
        return methodBuilder.toString();
    }


    private String createByOrder(String[] fs, OrderBy orderBy) {
        if (fs.length == 1) {
            return upperFirst(fs[0]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < fs.length - 1; j++) {
            stringBuilder.append(upperFirst(fs[j]));
            if (j != fs.length - 2) {
                stringBuilder.append("And");
            }
        }
        stringBuilder.append("OrderBy").append(upperFirst(fs[fs.length - 1])).append(orderBy.getStr());
        return stringBuilder.toString();
    }

    private String createBy(String[] fs) {
        if (fs.length == 1) {
            return upperFirst(fs[0]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < fs.length; j++) {
            stringBuilder.append(upperFirst(fs[j]));
            if (j != fs.length - 1) {
                stringBuilder.append("And");
            }
        }
        return stringBuilder.toString();
    }

    private String upperFirst(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    protected String getName(String clsName) {
        return clsName.substring(clsName.lastIndexOf(".") + 1);
    }
}