package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 取餐机
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@Component
public class FoodMachineServiceHystrix implements IFoodMachineService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,FoodMachineEntity foodMachine) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodMachineEntity foodMachine) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findByAreaCode(String areaCode) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	
	@Override
	public ResponseMessage findByMacId(Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
	@Override
	public ResponseMessage findByMacRefProductById(Integer macId){
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
}

