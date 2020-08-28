package com.xiaomi.service;


import com.xiaomi.mapper.MainMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MainServiceImpl implements MainSevice{

    @Resource
    private MainMapper mainMapper;
}
