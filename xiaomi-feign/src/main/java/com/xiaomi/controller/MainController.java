package com.xiaomi.controller;


import com.xiaomi.service.MainServiceFeign;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/main")
public class MainController {

    @Resource
    private MainServiceFeign mainServiceFeign;


    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }


}
