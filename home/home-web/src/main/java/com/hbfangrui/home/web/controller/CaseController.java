package com.hbfangrui.home.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tao.li on 2016/4/23.
 */
@Controller
@RequestMapping("case")
public class CaseController extends BaseController{
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    @RequestMapping("index")
    public ModelAndView showIndex(){
        return new ModelAndView("case/index");
    }

//    @RequestMapping("detail/{id}")
//    public DeferredResult<ModelAndView> showDetail(@PathVariable("id") Integer id){
//        final DeferredResult<ModelAndView> result = new DeferredResult<>(3000l);
//        scheduledExecutorService.schedule(
//                ()->result.setResult(new ModelAndView("case/detail" + id)),
//                1, TimeUnit.SECONDS);
//
//        return result;
//    }

    @RequestMapping("detail/{id}")
    public ModelAndView showDetail(@PathVariable("id") Integer id){
        return new ModelAndView("case/detail" + id);
    }
}
