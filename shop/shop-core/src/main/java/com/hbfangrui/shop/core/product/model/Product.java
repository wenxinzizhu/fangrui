package com.hbfangrui.shop.core.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * Created by tao.li on 2016/4/29.
 */
@Document
public class Product implements Agg<Product> {
    @Id
    private BigInteger id;

    @Version
    public Integer version;

    private String name;

    private String desc;

    Product(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public void acceptCommand(Consumer<Product> command) {
        command.accept(this);
    }

    @Override
    public void acceptCommand(Collection<Consumer<Product>> commands) {
        commands.forEach(this::acceptCommand);
    }
}
