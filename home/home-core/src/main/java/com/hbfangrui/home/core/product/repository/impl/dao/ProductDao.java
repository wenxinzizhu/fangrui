package com.hbfangrui.home.core.product.repository.impl.dao;

import com.hbfangrui.home.core.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

/**
 * Created by tao.li on 2016/4/29.
 */
public interface ProductDao extends MongoRepository<Product, BigInteger> {
}
