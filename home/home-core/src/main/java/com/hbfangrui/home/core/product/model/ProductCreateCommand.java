package com.hbfangrui.home.core.product.model;

import org.bson.types.ObjectId;

import java.math.BigInteger;

/**
 * Created by tao.li on 2016/4/29.
 */
public class ProductCreateCommand extends AbstractCreateCommand<Product, BigInteger> {
    public ProductCreateCommand(BigInteger id) {
        super(id);
    }

    @Override
    Product createInstance(BigInteger id) {
        return new Product(id);
    }

    public ProductCreateCommand name(String name) {
        addCommands(product -> product.setName(name));
        return this;
    }

    public ProductCreateCommand desc(String desc) {
        addCommands(product -> product.setDesc(desc));
        return this;
    }

    public static ProductCreateCommand newInstance() {
        return new ProductCreateCommand(createId());
    }

    static public BigInteger createId() {
        return new BigInteger(new ObjectId().toString(), 16);
    }


}