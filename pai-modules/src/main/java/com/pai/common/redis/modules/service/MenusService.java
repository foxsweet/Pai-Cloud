package com.pai.common.redis.modules.service;

import java.util.Set;

/**
 * @创建人 dmm
 */
public interface MenusService {

    /**
     * 根据用户id获取权限
     * @param userId
     * @return
     */
    public Set<String> selectMenusPermsByUserId(Long userId);

}
