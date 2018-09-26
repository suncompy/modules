package com.lebaoxun.modules.manager.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.manager.service.ISysUserLogService;

/**
 * 管理员日志表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-26 12:04:12
 */

@Component
public class SysUserLogServiceHystrix implements ISysUserLogService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findLogByUserId(Long adminId, String logType) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage queryAllLogType() {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
}

