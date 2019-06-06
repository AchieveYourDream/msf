package com.wb.msfproviter01.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.wb.msfcore.entity.MsfUser;
import com.wb.msfproviter01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    @GetMapping(value="user/{id}")
    @HystrixCommand(fallbackMethod = "getAllHystrix")
    public MsfUser getAll(@PathVariable("id")Integer id) throws Exception {
        MsfUser ms=    userService.selectByPrimaryKey(id);
         if(ms==null){
             throw new Exception("返回异常");
         }

        return ms;
    }
    public MsfUser getAllHystrix(@PathVariable("id")Integer id) throws Exception {
        MsfUser ms=    new MsfUser();
        return ms;
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
