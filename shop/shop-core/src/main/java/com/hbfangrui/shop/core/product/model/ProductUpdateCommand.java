package com.hbfangrui.shop.core.product.model;

import java.math.BigInteger;


/**
 * Created by tao.li on 2016/4/29.
 */
public class ProductUpdateCommand extends AbstractUpdateCommand<Product, BigInteger>{

    public ProductUpdateCommand(BigInteger id) {
        super(id);
    }


    public ProductUpdateCommand name(String name) {
        addCommands(product -> product.setName(name));
        return this;
    }

    public ProductUpdateCommand desc(String desc) {
        addCommands(product -> product.setDesc(desc));
        return this;
    }

    public static ProductUpdateCommand newInstance(BigInteger id) {
        return new ProductUpdateCommand(id);
    }
}
