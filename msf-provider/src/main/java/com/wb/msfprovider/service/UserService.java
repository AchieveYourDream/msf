package com.wb.msfprovider.service;

import com.wb.msfcore.entity.MsfUser;

import java.util.List;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-04-20
 * @Version
 **/
public interface UserService  {


    int deleteByPrimaryKey(Integer id);

    int insert(MsfUser record);

    MsfUser selectByPrimaryKey(Integer id);

    List<MsfUser> selectAll();

    int updateByPrimaryKey(MsfUser record);

}
