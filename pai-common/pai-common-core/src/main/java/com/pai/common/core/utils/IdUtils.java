package com.pai.common.core.utils;


import com.pai.common.core.text.UUID;

/**
 * ID生成器
 *
 * @创建人 dmm
 */
public class IdUtils {

    /**
     * 获取随机的UUID
     * @return
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }
}
