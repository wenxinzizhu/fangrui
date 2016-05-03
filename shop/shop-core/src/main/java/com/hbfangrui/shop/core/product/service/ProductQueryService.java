package com.hbfangrui.shop.core.product.service;

import com.hbfangrui.shop.core.product.model.Product;

import java.math.BigInteger;

/**
 * Created by tao.li on 2016/4/29.
 */
public interface ProductQueryService {
    Product getProductById(BigInteger id);
}
