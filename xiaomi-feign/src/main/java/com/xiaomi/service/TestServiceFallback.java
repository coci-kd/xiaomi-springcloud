package com.xiaomi.service;


import entity.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/error")
@Component
public class TestServiceFallback implements TestServiceFeign{

    public List<Test> test() {
        System.out.println("熔断处理，测试方法");
        return null;
    }
}
