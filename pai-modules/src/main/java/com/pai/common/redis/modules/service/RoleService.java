package com.pai.common.redis.modules.service;

import java.util.Set;

/**
 * @创建人 dmm
 */
public interface RoleService {


    /**
     * 根据用户id用户所有权限
     *
     * @param useerId
     * @return
     */
    public Set<String> selectRloePermissionByUserId(Long useerId);
}
