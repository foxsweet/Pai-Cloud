package com.pai.common.redis.modules.service;

import java.util.Set;

/**
 * @创建人 dmm
 */
public interface PermissionService {

    /**
     * 根据userId获取用户权限
     *
     * @param userId
     * @return
     */
    public Set<String> getRolePermission(Long userId);

    /**
     * 根据userId获取用户权限
     *
     * @param userId
     * @return
     */
    public Set<String> getMenuPermission(Long userId);
}
