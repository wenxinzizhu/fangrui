package com.hbfangrui.home.core.product.repository;

import com.hbfangrui.home.core.product.model.Product;

import java.math.BigInteger;

/**
 * Created by tao.li on 2016/4/29.
 */
public interface ProductRepository {
    void saveOrUpdate(Product build);

    Product getById(BigInteger id);
}
