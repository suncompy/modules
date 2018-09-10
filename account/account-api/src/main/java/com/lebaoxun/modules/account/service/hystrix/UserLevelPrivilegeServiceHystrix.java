package com.lebaoxun.modules.account.service.hystrix;
import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.account.entity.UserLevelPrivilegeEntity;
import com.lebaoxun.modules.account.service.IUserLevelPrivilegeService;

/**
 * 等级特权表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-10 15:54:53
 */

@Component
public class UserLevelPrivilegeServiceHystrix implements IUserLevelPrivilegeService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,UserLevelPrivilegeEntity userLevelPrivilege) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,UserLevelPrivilegeEntity userLevelPrivilege) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public UserLevelPrivilegeEntity findLevelByUserId(Long userId, String payLogType) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
}

