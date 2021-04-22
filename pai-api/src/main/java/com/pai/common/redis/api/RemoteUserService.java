package com.pai.common.redis.api;

import com.pai.common.redis.api.vo.LoginUserVO;
import com.pai.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @创建人 dmm
 */
@FeignClient(contextId = "remotUserService")
public interface RemoteUserService {


    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/user/info/{username}")
    public R<LoginUserVO> getUserInfo(@PathVariable("username") String username);

}
