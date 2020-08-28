package com.xiaomi.service;


import entity.UserBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/error")
@Component
public class MainServiceFallBack implements MainServiceFeign{



}
