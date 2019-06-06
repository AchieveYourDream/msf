package com.wb.msfconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-04-22
 * @Version
 **/
@SpringBootApplication
@EnableEurekaClient
public class MsfConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsfConsumerApplication.class,args);

    }
}
