package com.hbfangrui.shop.core.product.repository.impl;

import com.hbfangrui.shop.core.product.model.Product;
import com.hbfangrui.shop.core.product.repository.ProductRepository;
import com.hbfangrui.shop.core.product.repository.impl.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Created by tao.li on 2016/4/29.
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private ProductDao productDao;

    @Override
    public void saveOrUpdate(Product build) {
        productDao.save(build);
    }

    @Override
    public Product getById(BigInteger id) {
        return productDao.findOne(id);
    }
}
