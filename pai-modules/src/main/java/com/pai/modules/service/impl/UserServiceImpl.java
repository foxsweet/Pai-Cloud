package com.pai.modules.service.impl;

import com.pai.api.domain.SysUser;
import com.pai.modules.mapper.UserMapper;
import com.pai.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建人 dmm
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public SysUser selectUserByUserName(String username) {

        return userMapper.selectUserByUsername(username);
    }


}
