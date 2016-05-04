package com.hbfangrui.base.infra.jpa.gen.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tao.li on 2015/7/31.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface QueryIndexes {
    QueryIndex[] value();
    String pkgName();
    String clsName() default "";
    boolean async() default true;
    QueryType queryType() default QueryType.JPA;
}
