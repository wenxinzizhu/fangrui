package com.hbfangrui.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tao.li on 2016/4/23.
 */
@Controller
@RequestMapping("home")
public class HomeController extends BaseController {
    @RequestMapping(value = {"", "/", "index"})
    public ModelAndView showHome() {
        return new ModelAndView("home/index");
    }
}
