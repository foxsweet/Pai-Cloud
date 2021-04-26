package com.pai.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.pai.common.core.domain.R;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

/**
 * @创建人 dmm
 */
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(GatewayExceptionHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        ServerHttpResponse response = serverWebExchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(throwable);
        }
        String message;
        if (throwable instanceof NotFoundException) {
            message = "服务未找到";
        } else if (throwable instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) throwable;
            message = responseStatusException.getMessage();
        } else {
            message = "内部服务器错误";

        }

        //logger.log("[网关异常处理]请求路径:{},异常信息:{}", serverWebExchange.getRequest().getPath(), throwable.getMessage());
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);


        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JSON.toJSONBytes(R.fail(message)));
        }));
    }
}
