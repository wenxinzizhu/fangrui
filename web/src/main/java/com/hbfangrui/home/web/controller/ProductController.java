package com.hbfangrui.home.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tao.li on 2016/4/23.
 */
@Controller
@RequestMapping("product")
public class ProductController extends BaseController{

    @RequestMapping("index")
    public ModelAndView showIndex(){
        return new ModelAndView("product/index");
    }
}
