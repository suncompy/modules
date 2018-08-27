package com.lebaoxun.modules.fastfood.service.hystrix;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineAisleService;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 取餐机通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@Component
public class FoodMachineAisleServiceHystrix implements IFoodMachineAisleService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,FoodMachineAisleEntity foodMachineAisle) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodMachineAisleEntity foodMachineAisle) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
	@Override
	public ResponseMessage findProductByMacIdAndProductCatId(Integer macId,
			Integer productCatId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage findMachineAisleListByMacId(Integer macId){
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage refProductAndType(Long adminId,FoodMachineRefAisleEntity foodMachineAisle){
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findProductByMacIdAndWeek(Integer macId, Integer week) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
}

