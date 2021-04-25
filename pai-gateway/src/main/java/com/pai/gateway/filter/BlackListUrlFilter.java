package com.pai.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @创建人 dmm
 */
@Component
public class BlackListUrlFilter extends AbstractGatewayFilterFactory<BlackListUrlFilter.Config> {


    @Override
    public GatewayFilter apply(BlackListUrlFilter.Config config) {
        return null;
    }

    protected static class Config{
        private List<String> blackUrls;

        private List<Pattern> blackurlsPattern = new ArrayList<>();

        public boolean matchBlackurls(String url){
            return blackurlsPattern.isEmpty()?false:blackurlsPattern.stream().filter(p -> p.matcher(url).find()).findAny().isPresent();
        }

        public List<String> getBlackUrls(){
            return blackUrls;
        }
        public void setBlacklistUrl(List<String> blackUrls)
        {
            this.blackUrls = blackUrls;
            this.blackurlsPattern.clear();
            this.blackUrls.forEach(url -> {
                this.blackurlsPattern.add(Pattern.compile(url.replaceAll("\\*\\*", "(.*?)"), Pattern.CASE_INSENSITIVE));
            });
        }
    }
}
