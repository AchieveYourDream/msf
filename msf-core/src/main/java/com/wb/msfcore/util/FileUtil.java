package com.wb.msfcore.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


import org.springframework.web.multipart.MultipartFile;



public class FileUtil {
    /**
     * 通用上传方法
     * @param savePath
     * @param multipartFile
     * @return
     */
    public static String uploadFile(String savePath,String identification, MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename();
        //获取源文件后缀
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        //拼装新文件名称
        String newFileName= identification.equals("")?UUID.randomUUID()+suffix:identification+UUID.randomUUID()+suffix;//UUID.randomUUID()随机字符串
        File file=new File(savePath+ newFileName);//    D:/images/jin.jpg
        try {
            //调用spring提供的方法进行文件读写
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }
    
}
