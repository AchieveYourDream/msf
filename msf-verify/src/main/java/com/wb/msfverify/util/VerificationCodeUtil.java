package com.wb.msfverify.util;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.UUID;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-05-05
 * @Version
 **/
public class VerificationCodeUtil {



        /**
         */
//        @RequestMapping(value="/generateCode",method={RequestMethod.POST,RequestMethod.GET})
//        public @ResponseBody
//        ModelMap generateCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            ModelMap model = new ModelMap();
//
//            //生成验证码uuid
//            model.put("id", UUID.randomUUID().toString());
//
//            // 设置页面不缓存
//            response.setHeader("Pragma","No-cache");
//            response.setHeader("Cache-Control","no-cache");
//            response.setDateHeader("Expires", 0);
//
//            model.put("img",encodeBase64ImgCode());//获取通过base64加密后图形码字符串
//            return model;
//        }

//        @RequestMapping(value="/springmvc/getOutImgCode")
//        public void getOutImgCode(HttpServletResponse response) throws ServletException, IOException {
//            PrintWriter out = response.getWriter();
//            response.setContentType("text/html;charset=utf-8");
//            out.println("<img src=\""+encodeBase64ImgCode()+"\">");
//            out.close();
//        }
public static void main(String[] args) throws Exception {
    ValidateCode a=new ValidateCode();
    System.out.println(a.createCode());
}
}
