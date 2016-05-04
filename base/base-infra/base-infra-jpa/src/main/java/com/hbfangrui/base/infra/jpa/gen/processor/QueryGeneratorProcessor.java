package com.hbfangrui.base.infra.jpa.gen.processor;

import com.hbfangrui.base.infra.jpa.gen.annotation.QueryIndexes;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tao.li on 2015/11/3.
 */
public class QueryGeneratorProcessor extends AbstractProcessor {
    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
        for (TypeElement te : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
                QueryIndexes queryIndexes = e.getAnnotation(QueryIndexes.class);
                ClassGenerator classGenerator = null;
                switch (queryIndexes.queryType()){
                    case JPA:
                        classGenerator =new JPAClassGenerator(this.elementUtils, (TypeElement) e, queryIndexes);
                        break;
                }
                String cls = classGenerator.generate();
                writ2File(queryIndexes.pkgName(),queryIndexes.clsName(), cls);
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.toString());
//                System.out.println("Printing-------: " + e.toString());
            }
        }
        return true;
    }

    private void writ2File(String pkgName, String clsName, String cls) {
        try {
            JavaFileObject jfo = this.filer.createSourceFile(pkgName + "."+ clsName);
            Writer writer = jfo.openWriter();
            writer.append(cls);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<String>();
        set.add(QueryIndexes.class.getName());
        return set;
    }
}
