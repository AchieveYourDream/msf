package com.wb.msfconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MsfConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsfConfigApplication.class, args);
    }

}
