package com.wb.msfconsumer.controller;

import com.wb.msfcore.entity.MsfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClaseName
 * @Auther wangbin
 * @Date
 * @Version
 **/
@RestController
public class ConsumerController {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;
    List<String> s=new ArrayList<String>();
/*
    @Autowired
    private DiscoveryClient discoveryClient;
*/


    //量体图片存放地址
    @Value("${provider.url}")
    private String providerUrl;

    @GetMapping(value="consumer/list")
    public List<MsfUser> getAll(){
        return restTemplate.getForObject(providerUrl+"user/list",List.class);
    }


    @GetMapping(value="consumer/{id}")
    public MsfUser getAll(@PathVariable("id") Integer id) throws Exception {
        return restTemplate.getForObject(providerUrl+"user/"+id,MsfUser.class);
    }



  /*  @GetMapping(value="discovery")
    public   Object  discovery() {

        List<String> list=discoveryClient.getServices();
        System.out.println(list);

        List<ServiceInstance> servlist=discoveryClient.getInstances("msf-consumer");
        for(ServiceInstance s:servlist){

            System.out.println(s.getServiceId()+"------"+s.getHost()+"------"+s.getUri()+"------"+s.getPort());
        }
        return this.discoveryClient;
    }*/

}