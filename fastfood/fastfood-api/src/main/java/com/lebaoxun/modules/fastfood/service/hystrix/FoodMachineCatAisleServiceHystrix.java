package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineCatAisleService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 取餐机分类通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@Component
public class FoodMachineCatAisleServiceHystrix implements IFoodMachineCatAisleService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findAisleInfoByCatId(@RequestParam("catId") Integer catId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage refMacCatByAisle(@RequestParam("adminId") Long adminId, @RequestBody List<FoodMachineCatAisleEntity> catAisleEntityList) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,FoodMachineCatAisleEntity foodMachineCatAisle) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodMachineCatAisleEntity foodMachineCatAisle) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
}

