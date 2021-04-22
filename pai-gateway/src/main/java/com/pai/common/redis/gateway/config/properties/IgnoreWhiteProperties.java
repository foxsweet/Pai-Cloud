package com.pai.common.redis.gateway.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 dmm
 */
@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
public class IgnoreWhiteProperties {

    private List<String> whiteUrls = new ArrayList<>();

    public List<String> getWhiteUrls() {
        return whiteUrls;
    }

    public void setWhiteUrls(List<String> whiteUrls) {
        this.whiteUrls = whiteUrls;
    }
}
