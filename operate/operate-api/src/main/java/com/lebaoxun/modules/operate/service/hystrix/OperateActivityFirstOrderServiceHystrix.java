package com.lebaoxun.modules.operate.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.operate.entity.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.operate.service.IOperateActivityFirstOrderService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;

/**
 * 首单活动表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */

@Component
public class OperateActivityFirstOrderServiceHystrix implements IOperateActivityFirstOrderService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,OperateActivityFirstOrderEntity operateActivityFirstOrder) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,OperateActivityFirstOrderEntity operateActivityFirstOrder) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
}

