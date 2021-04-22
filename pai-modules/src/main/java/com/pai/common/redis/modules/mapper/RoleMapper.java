package com.pai.common.redis.modules.mapper;

import com.pai.common.redis.api.domain.SysRole;

import java.util.List;

/**
 * @创建人 dmm
 */
public interface RoleMapper {


    /**
     * 根据用户id用户所有权限
     *
     * @param userId
     * @return
     */
    public List<SysRole> selectRolePermissionByUserId(Long userId);

}
