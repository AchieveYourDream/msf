package com.wb.msfprovider.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.mysql.cj.jdbc.DatabaseMetaDataUsingInfoSchema;
import com.wb.msfprovider.service.UserService;
import com.wb.msfcore.entity.MsfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-04-20
 * @Version
 **/
@RestController
public class UserController {
     @Autowired
    private UserService userService;

    @Autowired
    private DiscoveryClient discoveryClient;

     @GetMapping(value="user/list")
    public List<MsfUser> getAll(){
         return userService.selectAll();
     }

     @GetMapping(value="/login")
    public String login(){
         return "hello";
     }




     @GetMapping(value="discovery")
    public   Object  discovery() {

         List<String> list=discoveryClient.getServices();
         System.out.println(list);

         List<ServiceInstance> servlist=discoveryClient.getInstances("msf-provider");
         for(ServiceInstance s:servlist){

             System.out.println(s.getServiceId()+"------"+s.getHost()+"------"+s.getUri()+"------"+s.getPort());
         }
         return this.discoveryClient;
    }



}
