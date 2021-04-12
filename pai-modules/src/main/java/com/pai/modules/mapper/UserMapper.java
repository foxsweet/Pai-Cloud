package com.pai.modules.mapper;

import com.pai.api.domain.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @创建人 dmm
 */
@Component
public interface UserMapper {

    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return
     */
     public List<String> selectUserByUsername(Long username);


}
