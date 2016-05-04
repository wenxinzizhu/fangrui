package com.hbfangrui.home.web.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tao.li on 2016/4/23.
 */
@Controller
@RequestMapping("product")
public class ProductController extends BaseController{

    @RequestMapping("index")
    public ModelAndView showIndex(){
        List<Product> products = loadProducts();
        ModelAndView mv = new ModelAndView("product/index");
        mv.addObject("products", products);
        return mv;
    }

    private List<Product> loadProducts() {
        return Arrays.asList(
                new Product(1, 10, 8000, 6d,"1KW标准化户用微型光伏电站系统"),
                new Product(2, 30, 26000, 5d, "3KW标准化户用微型光伏电站系统"),
                new Product(3, 50, 38000, 4.8d, "5KW标准化户用微型光伏电站系统"),
                new Product(4, 100, 75000, 4.5d, "10KW标准化户用微型光伏电站系统"),
                new Product(5, 300, 23700, 4.3d, "30KW标准化户用微型光伏电站系统"),
                new Product(6, 500, 39500, 4.3d, "50KW标准化户用微型光伏电站系统")
        );
    }

    @RequestMapping("detail/{id}")
    public ModelAndView showDetail(@PathVariable Integer id){
        ModelAndView mv = new ModelAndView("product/detail");
        mv.addObject("product", loadProduct(id));
        return mv;
    }

    private Product loadProduct(Integer id) {
        return loadProducts().stream().filter(product->product.id.equals(id)).findFirst().get();
    }

    @Data
    @AllArgsConstructor
    public static class Product{
        private Integer id;
        private Integer area;
        private Integer cost;
        private Double recoveryPeriod;
        private String name;
    }
}
