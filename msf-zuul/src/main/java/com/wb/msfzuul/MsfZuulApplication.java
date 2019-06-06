package com.wb.msfzuul;

import com.wb.msfzuul.filter.LoginFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class MsfZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsfZuulApplication.class, args);
    }
    //覆盖默认的自动配置
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        //修改默认的序列化规则
        //1.创建序列化规则对象
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);
        //2.更改默认的序列化规则
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    //添加zuul网关验证拦截器
    @Bean
    public LoginFilter LoginFilter(){
        return  new LoginFilter();
    }
}
