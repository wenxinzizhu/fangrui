package com.hbfangrui.home.core.product.service.impl;

import com.hbfangrui.home.core.product.model.*;
import com.hbfangrui.home.core.product.repository.ProductRepository;
import com.hbfangrui.home.core.product.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * Created by tao.li on 2016/4/29.
 */
@Service
public class ProductCommandServiceImpl implements ProductCommandService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void execute(ProductCreateCommand createCommand) {
        executeCommand(createCommand);
    }

    @Override
    public void execute(ProductUpdateCommand updateCommand) {
        executeCommand(updateCommand);
    }

    public void executeCommand(CreateCommand<Product, BigInteger> createCommand) {
        productRepository.saveOrUpdate(createCommand.create());
    }

    public void executeCommand(UpdateCommand<Product, BigInteger> updateCommand) {
        Product product = productRepository.getById(updateCommand.getId());
        if (product != null) {
            updateCommand.accept(product);
            this.productRepository.saveOrUpdate(product);
        }
    }
}
