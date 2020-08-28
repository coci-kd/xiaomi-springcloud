package com.xiaomi.service;


import entity.UserBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/error")
@Component
public class LoginServiceFallBack implements LoginServiceFeign{


    @Override
    public UserBean findUserInfoByAccount(String account) {
        System.out.println("熔断处理，测试方法");
        return null;
    }
}
