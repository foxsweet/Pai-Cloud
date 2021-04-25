package com.pai.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pai.common.core.constant.CacheConstants;
import com.pai.common.core.constant.Constants;
import com.pai.common.core.domain.R;
import com.pai.common.core.utils.StringUtils;
import com.pai.gateway.config.properties.IgnoreWhiteProperties;
import com.pai.common.redis.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @创建人 dmm
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private final static long EXPIRE_Time = Constants.TOKEN_EXPIRE*60;

    @Autowired
    private IgnoreWhiteProperties ignoreWhiteProperties;

    @Resource(name = "StringRedisTemplate")
    private ValueOperations<String,String> soap;

    @Autowired
    private RedisService redisService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String url = exchange.getRequest().getURI().getPath();
        if(StringUtils.matches(url,ignoreWhiteProperties.getWhiteUrls())){
            return chain.filter(exchange);
        }
        String token = getToken(exchange.getRequest());
        if(StringUtils.isBlank(token)){
            return setUnauthorizedResponse(exchange,"令牌不能为空");
        }
        String userStr = soap.get(getToken(exchange.getRequest()));
        if(StringUtils.isBlank(userStr)){
            return setUnauthorizedResponse(exchange,"登录状态已过期");
        }
        JSONObject jsonObject = JSONObject.parseObject(userStr);
        String userId = jsonObject.getString("userId");
        String username = jsonObject.getString("username");
        if(StringUtils.isBlank(userId)|| StringUtils.isBlank(username)){
            return setUnauthorizedResponse(exchange,"令牌校验失败");
        }
        //设置过期时间
        redisService.expire(getToken(exchange.getRequest()),EXPIRE_Time);
        //设置用户信息到请求
        ServerHttpRequest header = (ServerHttpRequest) exchange.getRequest().mutate().header(CacheConstants.DETAILS_USER_ID, userId)
                .header(CacheConstants.DETAILS_USERNAME, username);
        ServerWebExchange build = exchange.mutate().request(header).build();
        return chain.filter(build);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String msg)
    {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        logger.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(R.fail(msg)));
        }));
    }

    private String getToken(ServerHttpRequest request){
        String token = request.getHeaders().getFirst(CacheConstants.HEADER);
        if(StringUtils.isNotEmpty(token)&&token.startsWith(CacheConstants.TOKEN_PREFIX)){
            token = token.replace(CacheConstants.TOKEN_PREFIX,"");
        }
        return token;

    }
}
