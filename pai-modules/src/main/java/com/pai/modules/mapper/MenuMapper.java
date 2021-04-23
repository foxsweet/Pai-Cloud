package com.pai.modules.mapper;

import java.util.List;

/**
 * @创建人 dmm
 */
public interface MenuMapper {

    /**
     * 根据用户id获取权限
     *
     * @param userId
     * @return
     */
    public List<String> selectMenuPermsByUserId(Long userId);

}
