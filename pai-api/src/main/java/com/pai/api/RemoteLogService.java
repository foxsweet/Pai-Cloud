package com.pai.api;

import com.pai.api.domain.SysOperLog;
import com.pai.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @创建人 dmm
 */

@FeignClient(contextId = "remotLogService")
public interface RemoteLogService {

    /**
     * 保存操作日志
     *
     * @param sysOperLog
     * @return
     */
    @PostMapping("operlog")
    R<Boolean> saveLog(@RequestBody SysOperLog sysOperLog);

    /**
     * 保存访问记录
     *
     * @param username
     * @param status
     * @param message
     * @return
     */
    @PostMapping("/logininfo")
    R<Boolean> saveLogininfor(@RequestParam("username") String username, @RequestParam("status") String status,
                              @RequestParam("message") String message);


}
