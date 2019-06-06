package com.wb.msfverify;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.UnknownHostException;

@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@SpringBootApplication(scanBasePackages = {"com.wb.*"})
@MapperScan(basePackages = "com.wb.**.dao")
public class MsfVerifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsfVerifyApplication.class, args);
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
//        //2.更改默认的序列化规则
        template.setDefaultSerializer(jackson2JsonRedisSerializer);

        //设置使用哪个仓库
        LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) template.getConnectionFactory();
        jedisConnectionFactory.setDatabase(2);
        return template;

    }

}
