package com.hbfangrui.shop.core.product.model;

/**
 * Created by tao.li on 2016/4/29.
 */
public interface Command<A, ID> {
    ID getId();

    void accept(A a);
}
