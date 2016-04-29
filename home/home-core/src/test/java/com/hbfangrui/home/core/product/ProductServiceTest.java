package com.hbfangrui.home.core.product;

import com.hbfangrui.home.core.AbstractBaseTest;
import com.hbfangrui.home.core.product.model.Product;
import com.hbfangrui.home.core.product.model.ProductCreateCommand;
import com.hbfangrui.home.core.product.model.ProductUpdateCommand;
import com.hbfangrui.home.core.product.service.ProductCommandService;
import com.hbfangrui.home.core.product.service.ProductQueryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

import static com.hbfangrui.home.core.TestDataUtils.randomString;

/**
 * Created by tao.li on 2016/4/29.
 */
public class ProductServiceTest extends AbstractBaseTest {
    private BigInteger id;

    private String name = randomString();
    private String uName = randomString();

    private String dec = randomString();
    private String uDesc = randomString();

    @Autowired
    private ProductCommandService commandService;

    @Autowired
    private ProductQueryService queryService;

    @Test
    public void testGetById() {
        Product product = this.queryService.getProductById(id);
        Assert.assertNotNull(product);
        Assert.assertEquals(name, product.getName());
        Assert.assertEquals(dec, product.getDesc());
    }

    @Test
    public void testUpdateProduct() {
        for (int i = 0; i < 100; i++) {
            ProductUpdateCommand updater = ProductUpdateCommand.newInstance(this.id)
                    .name(uName)
                    .desc(uDesc);
            this.commandService.execute(updater);
            Product product = this.queryService.getProductById(this.id);
            Assert.assertEquals(id, product.getId());
            Assert.assertEquals(uName, product.getName());
            Assert.assertEquals(uDesc, product.getDesc());
        }
    }

    @Before
    public void init() {
        ProductCreateCommand builder = ProductCreateCommand.newInstance()
                .name(name)
                .desc(dec);
        this.id = builder.getId();
        this.commandService.execute(builder);
    }
}
