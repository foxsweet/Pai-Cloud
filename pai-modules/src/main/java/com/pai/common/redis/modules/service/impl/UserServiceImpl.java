package com.pai.common.redis.modules.service.impl;

import com.pai.common.redis.api.domain.SysUser;
import com.pai.common.redis.modules.mapper.UserMapper;
import com.pai.common.redis.modules.service.UserService;
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
