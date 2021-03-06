package com.lebaoxun.modules.operate.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.operate.entity.AdPosEntity;
import com.lebaoxun.modules.operate.service.IAdPosService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 广告位
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-22 23:06:49
 */

@Component
public class AdPosServiceHystrix implements IAdPosService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,AdPosEntity adPos) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,AdPosEntity adPos) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	@Override
	public ResponseMessage getAdvertInfo(String macCode,String advertId,String advertCode,String advertType){
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
}

