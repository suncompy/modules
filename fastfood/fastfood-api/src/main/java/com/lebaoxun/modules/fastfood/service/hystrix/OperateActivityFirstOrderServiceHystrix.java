package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.fastfood.service.IOperateActivityFirstOrderService;

/**
 * 首单活动表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:31
 */

@Component
public class OperateActivityFirstOrderServiceHystrix implements IOperateActivityFirstOrderService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info() {
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

	@Override
	public OperateActivityFirstOrderEntity findUnderwayActivity() {
		// TODO Auto-generated method stub
		return null;
	}
    
}

