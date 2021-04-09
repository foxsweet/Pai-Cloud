package com.pai.modules.service;

import com.pai.api.domain.SysUser;

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
