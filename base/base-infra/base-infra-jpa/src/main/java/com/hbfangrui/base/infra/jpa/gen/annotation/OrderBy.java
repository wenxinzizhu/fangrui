package com.hbfangrui.base.infra.jpa.gen.annotation;

/**
 * Created by tao.li on 2015/11/3.
 */
public enum  OrderBy {
    DESC("Desc"), ASC("Asc");
    private final String str;

    OrderBy(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
