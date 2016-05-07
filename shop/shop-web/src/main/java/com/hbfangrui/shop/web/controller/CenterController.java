package com.hbfangrui.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by taoli on 16/5/7.
 */
@Controller
@RequestMapping("center")
public class CenterController {

    @RequestMapping("index")
    public ModelAndView showIndex(){
        return new ModelAndView("center/index");
    }

    @RequestMapping("order/index")
    public ModelAndView showOrderIndex(){
        return new ModelAndView("center/order/index");
    }
}
