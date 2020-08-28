package com.xiaomi.service;


import entity.UserBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@RequestMapping("/error")
@Component
public class LoginServiceFallBack implements LoginServiceFeign{


    @Override
    public UserBean findUserInfoByAccount(String account) {
        System.out.println("熔断处理，测试方法");
        return null;
    }

    @Override
    public Map sendSms(String phone) throws Exception {
        System.out.println("熔断处理，发送验证码方法");
        return null;
    }

    @Override
    public Map sendMsg(String phone, String checkCode) {
        System.out.println("熔断处理，验证码登录方法");
        return null;
    }
}
