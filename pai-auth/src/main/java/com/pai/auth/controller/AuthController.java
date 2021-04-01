package com.pai.auth.controller;

import com.pai.api.vo.LoginUserVO;
import com.pai.auth.form.Login;
import com.pai.auth.service.LoginService;
import com.pai.common.core.domain.R;
import com.pai.common.core.utils.StringUtils;
import com.pai.common.security.service.TokenService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 认证模块
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@Api(value = "用户授权认证", tags = "授权接口")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public R<?> login(@RequestParam Login login){
        LoginUserVO login1 = loginService.login(login.getUsername(), login.getPassworld());
        //获取登录token
        return R.ok(tokenService.createToken(login1));
    }

    public R<?> logout(HttpServletRequest request){
        LoginUserVO loginUser = tokenService.getLoginUser(request);
        if(StringUtils.isNotNull(loginUser)){
            String username = loginUser.getUsername();
            //删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            //记录退出日志
            loginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request){
        LoginUserVO loginUser = tokenService.getLoginUser(request);
        if(StringUtils.isNotNull(loginUser)){
            //刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return  R.ok();
        }
        return  R.ok();
    }




}
