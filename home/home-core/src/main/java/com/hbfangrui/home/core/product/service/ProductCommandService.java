package com.hbfangrui.home.core.product.service;

import com.hbfangrui.home.core.product.model.ProductCreateCommand;
import com.hbfangrui.home.core.product.model.ProductUpdateCommand;

/**
 * Created by tao.li on 2016/4/29.
 */
public interface ProductCommandService {
    void execute(ProductCreateCommand createCommand);

    void execute(ProductUpdateCommand updateCommand);
}
