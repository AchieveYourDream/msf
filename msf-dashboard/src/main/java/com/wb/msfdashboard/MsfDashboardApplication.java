package com.wb.msfdashboard;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class MsfDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsfDashboardApplication.class, args);

    }



}
