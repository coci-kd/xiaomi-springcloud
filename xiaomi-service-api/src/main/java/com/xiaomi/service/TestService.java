package com.xiaomi.service;

import entity.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public interface TestService {

    @RequestMapping("/test")
    List<Test> test();
}
