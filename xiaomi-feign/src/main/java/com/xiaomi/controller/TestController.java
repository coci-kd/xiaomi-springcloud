package com.xiaomi.controller;


import com.xiaomi.service.TestServiceFeign;
import entity.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {


    @Resource
    private TestServiceFeign testServiceFeign;

    @RequestMapping("/toTest")
    public String toTest(){
        return "test";
    }


    @RequestMapping("/test")
    @ResponseBody
    public List<Test> test(){
        List<Test> test = testServiceFeign.test();
        return  test;
    }



}
