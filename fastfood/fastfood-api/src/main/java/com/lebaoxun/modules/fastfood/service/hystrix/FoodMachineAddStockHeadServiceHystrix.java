package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockHeadEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineAddStockHeadService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;

/**
 * 取餐机进货派单主表
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-25 16:41:14
 */

@Component
public class FoodMachineAddStockHeadServiceHystrix implements IFoodMachineAddStockHeadService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,FoodMachineAddStockHeadEntity foodMachineAddStockHead) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodMachineAddStockHeadEntity foodMachineAddStockHead) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
}

