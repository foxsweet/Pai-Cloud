package com.pai.common.redis.modules.service.impl;

import com.pai.common.redis.api.domain.SysUser;
import com.pai.common.redis.modules.service.PermissionService;
import com.pai.common.redis.modules.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @创建人 dmm
 */
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RoleService roleService;

    /**
     * 根据userId获取用户权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<>();

        //管理员拥有所有权限
        if(SysUser.isAdmin(userId)){
            roles.add("admin");
        }else{
            roles.addAll(roleService.selectRloePermissionByUserId(userId));
        }
        return roles;
    }

    @Override
    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<>();

        //管理员拥有所有权限
        if(SysUser.isAdmin(userId)){
            perms.add("*:*:");
        }else {

        }
        return null;
    }

}
