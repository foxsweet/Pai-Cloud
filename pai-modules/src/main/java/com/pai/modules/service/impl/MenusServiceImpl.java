package com.pai.modules.service.impl;

import com.pai.api.domain.SysUser;
import com.pai.common.core.utils.StringUtils;
import com.pai.modules.mapper.MenuMapper;
import com.pai.modules.mapper.UserMapper;
import com.pai.modules.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @创建人 dmm
 */
public class MenusServiceImpl implements MenusService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Set<String> selectMenusPermsByUserId(Long userId) {
        List<String> strings = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : strings)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
