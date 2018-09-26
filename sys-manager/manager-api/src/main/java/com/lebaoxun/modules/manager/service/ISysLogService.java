package com.lebaoxun.modules.manager.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.manager.service.hystrix.SysLogServiceHystrix;

@FeignClient(value="manager-service",fallback=SysLogServiceHystrix.class)
public interface ISysLogService {
	
	@RequestMapping("/sys/log/list")
	public ResponseMessage list(@RequestParam Map<String, Object> params);
}
