package com.wb.msfverify.impl;

import com.wb.msfcore.dao.SysUserMapper;
import com.wb.msfcore.entity.LoginUser;
import com.wb.msfcore.entity.SysUser;
import com.wb.msfcore.util.MD5Util;
import com.wb.msfcore.util.UUIDUtil;
import com.wb.msfcore.vo.ResponseResult;
import com.wb.msfverify.jwt.JWTTokenUtil;
import com.wb.msfverify.jwt.JWTUserDetails;
import com.wb.msfverify.redis.RedisUtil;
import com.wb.msfverify.service.SecurityService;
import com.wb.msfverify.util.ImgFontByte;
import com.wb.msfverify.util.RSAUtil;
import com.wb.msfverify.util.ValidateCode;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-05-08
 * @Version
 **/
@Service
public class SecurityServiceImpl implements SecurityService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    @Autowired
    SysUserMapper sysUserMapper;

    // 图片的宽度。
    private static int width = 160;
    // 图片的高度。
    private static int height = 40;
    // 验证码字符个数
    private static int codeCount = 5;
    // 验证码干扰线数
    private static int lineCount = 150;
    //MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDABYfAwSMKonyj4lCjKeaovpGd477TW/ShYKYivvXR020c9NQt/ng3e88PUzfgcmUM8HPVVa/fogVhznNOpvsX82rO4YIXdu3nUBld8d4BW+UOg8vB1sR2JTnvgkQvTy12Jmh0Qh0nmAyGszuV20tX2k1SuUhoTy/Jb2y63orDnQIDAQAB
    private static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMAFh8DBIwqifKPiUKMp5qi+kZ3jvtNb9KFgpiK+9dHTbRz01C3+eDd7zw9TN+ByZQzwc9VVr9+iBWHOc06m+xfzas7hghd27edQGV3x3gFb5Q6Dy8HWxHYlOe+CRC9PLXYmaHRCHSeYDIazO5XbS1faTVK5SGhPL8lvbLreisOdAgMBAAECgYAc3bf2phIaeyq5iEAPBzGBlIRXc/Ev3Nb9m4XeG07g6COzAqsS7vorfRU3fI8d0pSWSV9gZq01tSmR9nMnE7sTL+Q1YwM5/h66e1FIVtW376PeUNQHoo71QWuZ/fg1sJ1Wbn+MVQIIegw8rpkMk57wf3QAuZo73jTxHH+KxMSVgQJBAOqZ4IYYwC2vx7ipI9ah+Viux+Wg9eGHbawj3D+5QjHLGtD9UjHkX4wLnBqMJSvJFOIqDU3v9lmoNGb66w1yq7UCQQDRiWNpbV5RpJdGegGPl4dn1FCuh0os/GNovuLjRyrVLCAycZuNgon+Xn5tTE3z6a1XBTp2zkXguI4IHaQM+rlJAkBRuyotjZHmWMcL3mFN9UAZVUrvBRSkbboXrqaczL3y+v9sr9Ri7V0cPS81UH3isztDT8My09bPhXo8Jk6dcMXBAkEAza5KCjQUUJn68HBU1enNPatYo2kj87KvtiMiPi0eq3AMDtgb9DV+VVXWTl2Ha3Um9POp5vMl/qfKq8bEwQTgGQJBAIGc2yALXyv6SAzpqRSpQxHwxY267A+3z+eXwKuxejotcD8ykhl6QicvMSUdbLJ75dh/NfUkkeD+DKpcmV78Sbc=";

    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Autowired
    RedisUtil redisUtil;

    /**
     * @Description: 登陆
     * @Author: 王斌
     * @Date: 2019/5/8
     */
    public ResponseResult login(LoginUser user) throws Exception {
        ResponseResult result = new ResponseResult();
        String loginStatus = "S";
        String loginMessage = "";
        String verificationCode = redisUtil.get(user.getImageVerificationId());
        if (verificationCode != null && verificationCode.equalsIgnoreCase(user.getImageVerificationCode())) {
//            //查询有没有会员信息
            SysUser merber = sysUserMapper.selectByPrimaryKey(user.getUsername());
            //验证账号
            if (merber == null) {
                loginStatus = "E";
                loginMessage = "账号不存在";
            }
            if (loginStatus.equals("S")) {
                String decryptPassword = RSAUtil.decrypt(user.getPassword(), privateKey);
                System.out.println(user.getPassword());
                System.out.println(MD5Util.MD5(decryptPassword).toLowerCase());
                System.out.println(decryptPassword);
                if (!merber.getPassword().equalsIgnoreCase(MD5Util.MD5(decryptPassword))) {
                    loginStatus = "E";
                    loginMessage = "密码不正确!";
                }
            }
            //获取token
            if (loginStatus.equals("S")) {
                String access_token = JWTTokenUtil.generateToken(secret, expiration, new JWTUserDetails(user.getUsername()));
                user.setToken(access_token);
                result.setData(user);
            }
            user.setPassword("");
            //删除缓存验证码
            redisUtil.remove(user.getImageVerificationId());
            result.setCode(loginStatus);
            result.setInfo(loginMessage);

        } else {
            result.setCode("E");
            result.setInfo("请输入正确验证码");
        }
        return result;

    }


    /**
     * @Description: 获取验证码
     * @Author: 王斌
     * @Date: 2019/5/8
     */
    public Map getVerificationCode() throws Exception {

        Map map = ValidateCode.createCode();
        if(map.isEmpty()){
           throw new Exception("生成验证码错误");
        }
        //放到缓存中
        redisUtil.set(map.get("imageVerificationCode").toString(), map.get("imageCoed"), 5L);
        //删除验证码
        map.remove("imageCoed");
        return map;
    }


}
