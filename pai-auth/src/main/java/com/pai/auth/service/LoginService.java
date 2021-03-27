package com.pai.auth.service;

import com.pai.api.RemoteUserService;
import com.pai.api.domain.SysUser;
import com.pai.api.vo.LoginUserVO;
import com.pai.common.core.constant.Constants;
import com.pai.common.core.constant.UserConstant;
import com.pai.common.core.domain.R;
import com.pai.common.core.enums.UserStatus;
import com.pai.common.core.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.pai.api.RemoteLogService;
import org.apache.commons.lang3.StringUtils;

/**
 * @创建人 dmm
 */
@Component
public class LoginService {

    @Autowired
    private RemoteLogService remoteLogService;

    @Autowired
    private RemoteUserService remoteUserService;


    public LoginUserVO login(String username , String password){

        //用户名密码必须填写
        if(StringUtils.isAnyBlank(username,password)){
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL,"用户名密码必须填写");
            throw new BaseException("用户名密码必须填写");
        }

        //密码不在指定范围内
        if(password.length()< UserConstant.PASSWORD_MIN_LENGTH || password.length()>UserConstant.PASSWORD_MAX_LENGTH){
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL,"用户输入的密码不在指定范围");
            throw new BaseException("用户输入的密码不在指定范围");
        }

        //用户名不在指定范围
        if(username.length()< UserConstant.USERNAME_MIN_LENGTH || username.length()>UserConstant.USERNAME_MAX_LENGTH){
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL,"用户输入的用户名不在指定范围");
            throw new BaseException("用户输入的用户名不在指定范围");
        }

        //查询用户信息
        R<LoginUserVO> userInfo = remoteUserService.getUserInfo(username);
        if(R.FAIL == userInfo.getCode()){
            throw new BaseException(userInfo.getMsg());
        }
        LoginUserVO data = userInfo.getData();
        SysUser sysUser = userInfo.getData().getSysUser();

        if(UserStatus.DELETED.getCode().equals(sysUser.getDelFlag())){
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL,"您的账号已被删除，请联系管理员");
            throw new BaseException("您的账号已被删除，请联系管理员");
        }

        if(UserStatus.DISABLE.getCode().equals(sysUser.getStatus())){
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL,"用户已停用，请联系管理员");
            throw new BaseException("用户已停用，请联系管理员");
        }

        return null;
    }

}
