package com.wb.msfzuul.redis;

import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void remove() {
       redisUtil.remove("wangbin");
    }

    @Test
    public void removePattern() {
    }

    @Test
    public void remove1() {
    }

    @Test
    public void exists() {
        System.out.println(redisUtil.exists("wangbin"));
    }

    @Test
    public void get() {
        System.out.println(redisUtil.get("wangbin"));
    }

    @Test
    public void set() {
    }

    @Test
    public void set1() {

        redisUtil.set("wangbin","22222",2L);
    }

    @Test
    public void hmset() {
    }

    @Test
    public void hmget() {
    }
}