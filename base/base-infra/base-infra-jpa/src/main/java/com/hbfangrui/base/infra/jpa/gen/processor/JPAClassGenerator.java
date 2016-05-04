package com.hbfangrui.base.infra.jpa.gen.processor;

import com.hbfangrui.base.infra.jpa.gen.annotation.QueryIndexes;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao.li on 2015/11/3.
 */
public class JPAClassGenerator extends AbstractClassGenerator {
    JPAClassGenerator(Elements elementUtils, TypeElement element, QueryIndexes indexes) {
        super(elementUtils, element, indexes);
    }

    @Override
    protected List<String> getCommonDependencyTypes(boolean async) {
        List<String> str = new ArrayList<String>();
        str.add("org.springframework.data.domain.Pageable");
        str.add("org.springframework.data.domain.Page");
        str.add("java.util.Optional");
        if (async) {
            str.add("java.util.concurrent.CompletableFuture");
        }
        str.add("JpaQueryDao");
        return str;
    }

    @Override
    protected String getSuperClass(TypeMirror beanType) {
        return "JpaQueryDao<ID, "+ getName(beanType.toString()) +">";
    }

    @Override
    protected String getName(String clsName) {
        return clsName+"<ID>";
    }
}
