package com.pai.common.core.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @创建人 dmm
 */
public class SecurityUtils {


    /**
     * 判断密码是否相同
     *
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


}
