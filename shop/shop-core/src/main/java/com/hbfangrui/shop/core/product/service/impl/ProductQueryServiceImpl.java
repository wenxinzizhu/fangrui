package com.hbfangrui.shop.core.product.service.impl;

import com.hbfangrui.shop.core.product.model.Product;
import com.hbfangrui.shop.core.product.repository.ProductRepository;
import com.hbfangrui.shop.core.product.service.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by tao.li on 2016/4/29.
 */
@Service
public class ProductQueryServiceImpl implements ProductQueryService{
    @Autowired
    private ProductRepository repository;

    @Override
    public Product getProductById(BigInteger id) {
        return repository.getById(id);
    }
}
