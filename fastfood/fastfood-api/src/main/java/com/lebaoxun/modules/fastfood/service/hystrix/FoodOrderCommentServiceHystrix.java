package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentEntity;
import com.lebaoxun.modules.fastfood.service.IFoodOrderCommentService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;

/**
 * 评价表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */

@Component
public class FoodOrderCommentServiceHystrix implements IFoodOrderCommentService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Long id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,FoodOrderCommentEntity foodOrderComment) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodOrderCommentEntity foodOrderComment) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Long[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage publish(Long userId, FoodOrderCommentEntity comment) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findByMacId(Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findLastByMacId(Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
}

