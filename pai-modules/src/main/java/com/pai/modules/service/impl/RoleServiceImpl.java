package com.pai.modules.service.impl;

import com.pai.api.domain.SysRole;
import com.pai.api.domain.SysUser;
import com.pai.common.core.utils.StringUtils;
import com.pai.modules.mapper.RoleMapper;
import com.pai.modules.mapper.UserMapper;
import com.pai.modules.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @创建人 dmm
 */
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    /**
     * 根据用户id用户所有权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> selectRloePermissionByUserId(Long userId) {
        List<SysRole> sysUserList = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for(SysRole sysRole:sysUserList){
            if(StringUtils.isNotNull(sysRole)){
                permsSet.addAll(Arrays.asList(sysRole.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}
