package com.xiaomi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class XiaomiEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaomiEurekaApplication.class, args);
    }

}
