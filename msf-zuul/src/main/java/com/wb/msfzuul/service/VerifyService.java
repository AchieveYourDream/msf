package com.wb.msfzuul.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface VerifyService {

    /**
     * 生成图形验证码
     * @return
     * @throws Exception
     */
    @GetMapping("/getVerifiCode")
    byte[] getVerifiCode() throws Exception;

    /**
     * 验证
     * @param code
     * @return
     */
    @PostMapping("/checkVerifiCode")
    Boolean checkVerifiCode(String code);


}
