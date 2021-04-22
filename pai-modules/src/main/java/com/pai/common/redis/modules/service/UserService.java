package com.pai.common.redis.modules.service;

import com.pai.common.redis.api.domain.SysUser;

/**
 * @创建人 dmm
 */
public interface UserService {

    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return
     */
    public SysUser selectUserByUserName(String username);


}
