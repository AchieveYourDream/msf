package com.wb.msfprovider.service.impl;

import com.wb.msfprovider.service.UserService;
import com.wb.msfprovider.dao.MsfUserMapper;
import com.wb.msfcore.entity.MsfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-04-20
 * @Version
 **/
@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private MsfUserMapper msfUserMapper;


    @Override
    public int deleteByPrimaryKey(Integer id) {
        return msfUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MsfUser record) {
        return msfUserMapper.insert(record);
    }

    @Override
    public MsfUser selectByPrimaryKey(Integer id) {
        return msfUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MsfUser> selectAll() {
        return msfUserMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(MsfUser record) {
        return msfUserMapper.updateByPrimaryKey(record);
    }
}
