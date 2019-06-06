package com.wb.msfverify.service;

import com.wb.msfcore.entity.LoginUser;
import com.wb.msfcore.vo.ResponseResult;
import org.apache.ibatis.annotations.Select;

import java.io.IOException;
import java.util.Map;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-05-08
 * @Version
 **/
public interface SecurityService {


    ResponseResult login(LoginUser user) throws Exception;

    Map getVerificationCode() throws  Exception;
}
