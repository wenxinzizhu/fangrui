package com.hbfangrui.shop.core.product.service;


import com.hbfangrui.shop.core.product.model.ProductCreateCommand;
import com.hbfangrui.shop.core.product.model.ProductUpdateCommand;

/**
 * Created by tao.li on 2016/4/29.
 */
public interface ProductCommandService {
    void execute(ProductCreateCommand createCommand);

    void execute(ProductUpdateCommand updateCommand);
}
