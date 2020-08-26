package com.xiaomi.service;


import com.xiaomi.mapper.TestMapper;
import entity.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestServiceImpl implements TestService{

    @Resource
    private TestMapper testMapper;

    @Override
    @RequestMapping("/test")
    public List<Test> test() {
        List<Test> test = testMapper.test();
        return test;
    }
}
