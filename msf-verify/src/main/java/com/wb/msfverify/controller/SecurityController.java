package com.wb.msfverify.controller;

import com.wb.msfcore.entity.LoginUser;
import com.wb.msfcore.vo.ResponseResult;
import com.wb.msfverify.redis.RedisUtil;
import com.wb.msfverify.service.SecurityService;
import com.wb.msfverify.util.ValidateCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-05-08
 * @Version
 **/
@RestController
public class SecurityController {

    @Autowired
    SecurityService securityService;


    /**
     * 登录
     */
    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody  LoginUser user) throws Exception {
        ResponseResult result = securityService.login(user);
        return result;

    }

    /**
     * 测试不加token
     */
    @ApiOperation(value = "获取数据")
    @PostMapping(value = "/getData")
    public String  getData() throws Exception {
       return "hello";

    }


    @GetMapping("/getVerificationCode")
    public Map getVerificationCode() throws Exception {
        return securityService.getVerificationCode();
    }
}
