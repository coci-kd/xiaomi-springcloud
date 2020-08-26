package com.xiaomi.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "xiaomi-provider", fallback = TestServiceFallback.class)
public interface TestServiceFeign extends TestService{


}
