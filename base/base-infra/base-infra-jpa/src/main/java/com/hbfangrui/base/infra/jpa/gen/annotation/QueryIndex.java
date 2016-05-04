package com.hbfangrui.base.infra.jpa.gen.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tao.li on 2015/11/3.
 */
@Retention(RetentionPolicy.SOURCE)
public @interface QueryIndex {
    String name();
    String[] fields();
    boolean unique() default false;
    OrderBy[] orderBy() default {};
}
