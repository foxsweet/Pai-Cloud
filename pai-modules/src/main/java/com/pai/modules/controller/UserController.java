package com.pai.modules.controller;

import com.pai.api.domain.SysUser;
import com.pai.api.vo.LoginUserVO;
import com.pai.common.core.domain.R;
import com.pai.common.core.utils.SecurityUtils;
import com.pai.common.core.utils.StringUtils;
import com.pai.modules.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户相关信息
 * @创建人 dmm
 */
public class UserController {

    private UserService userService;
    /**
     * 获取当前用户信息
     */
    public R<LoginUserVO> info(@PathVariable("username") String username){
        SysUser sysUser = userService.selectUserByUserName(username);
        if(StringUtils.isNull(sysUser)){
            return R.fail("用户名或密码错误");
        }
        return null;
    }



}
