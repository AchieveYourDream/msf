package com.wb.msfcore.entity;

import lombok.Data;

/**
 * @Name: 登录用户
 * @Description: 登录用户
 */
@Data
public class LoginUser {

    private String username;

    private String password;

    private String imageVerificationId;

    private String imageVerificationCode;

    private String token;
}
