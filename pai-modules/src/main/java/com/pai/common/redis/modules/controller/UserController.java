package com.pai.common.redis.modules.controller;

import com.pai.common.redis.api.domain.SysUser;
import com.pai.common.redis.api.vo.LoginUserVO;
import com.pai.common.core.domain.R;
import com.pai.common.core.utils.StringUtils;
import com.pai.common.redis.modules.service.PermissionService;
import com.pai.common.redis.modules.service.MenusService;
import com.pai.common.redis.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * 用户相关信息
 *
 * @创建人 dmm
 */
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MenusService menusService;

    /**
     * 获取当前用户信息
     */
    @GetMapping("/info/{usernamme}")
    public R<LoginUserVO> info(@PathVariable("username") String username) {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (StringUtils.isNull(sysUser)) {
            return R.fail("用户名或密码错误");
        }
        //角色集合
        Set<String> rolePermission = permissionService.getRolePermission(sysUser.getUserId());

        //权限集合
        Set<String> strings = menusService.selectMenusPermsByUserId(sysUser.getUserId());

        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setSysUser(sysUser);
        loginUserVO.setRoles(rolePermission);
        loginUserVO.setPermissions(strings);
        return R.ok(loginUserVO);
    }


}
