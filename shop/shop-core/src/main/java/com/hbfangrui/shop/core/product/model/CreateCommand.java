package com.hbfangrui.shop.core.product.model;

/**
 * Created by tao.li on 2016/4/29.
 */
public interface CreateCommand<A, ID> extends Command<A, ID>{
    A create();
}
