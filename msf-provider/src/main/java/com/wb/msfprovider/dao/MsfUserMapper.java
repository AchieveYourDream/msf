package com.wb.msfprovider.dao;

import com.wb.msfcore.entity.MsfUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MsfUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsfUser record);

    MsfUser selectByPrimaryKey(Integer id);

    List<MsfUser> selectAll();

    int updateByPrimaryKey(MsfUser record);
}