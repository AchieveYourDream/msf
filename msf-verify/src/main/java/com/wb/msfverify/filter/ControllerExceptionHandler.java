package com.wb.msfverify.filter;

import com.wb.msfcore.vo.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-05-09
 * @Version
 **/
@ControllerAdvice
public class ControllerExceptionHandler   extends RuntimeException{

    @ExceptionHandler(Exception.class) //获取所有异常
    @ResponseBody
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handlerUserNotExistException(Exception ex){
        ResponseResult result=new ResponseResult();
        result.setCode("E");
        result.setInfo(ex.getMessage());
        return result;
    }
}
