package com.wb.msfcore.dao;

import com.wb.msfcore.entity.SysUser;
import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String username);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(String username);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);
}