package com.pai.common.security.service;

import com.pai.api.vo.LoginUserVO;
import com.pai.common.core.constant.CacheConstants;
import com.pai.common.core.constant.Constants;
import com.pai.common.core.ip.IpUtils;
import com.pai.common.core.ip.ServletUtils;
import com.pai.common.core.utils.IdUtils;
import com.pai.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TokenService {

    @Autowired
    private RedisService redisService;

    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    protected static final long MILLIS_SECOND = 1000;


    /**
     * 创建令牌
     */
    public Map<String,Object> createToken(LoginUserVO loginUserVO){

        //生成token
        String token = IdUtils.fastUUID();
        loginUserVO.setToken(token);
        loginUserVO.setUserid(loginUserVO.getSysUser().getUserId());
        loginUserVO.setUsername(loginUserVO.getSysUser().getUserName());
        loginUserVO.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginUserVO);

        // 保存或更新用户token
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("access_token", token);
        map.put("expires_in", EXPIRE_TIME);
        redisService.setCacheObject(ACCESS_TOKEN + token, loginUserVO, EXPIRE_TIME, TimeUnit.SECONDS);
        return map;



    }



    private void refreshToken(LoginUserVO loginUserVO){
        loginUserVO.setLoginTime(System.currentTimeMillis());
        loginUserVO.setExpireTime(loginUserVO.getLoginTime() + EXPIRE_TIME * MILLIS_SECOND);
        // 根据uuid将loginUser缓存
        String userKey = ACCESS_TOKEN + loginUserVO.getToken();
        redisService.setCacheObject(userKey, loginUserVO, EXPIRE_TIME, TimeUnit.SECONDS);


    }

}
