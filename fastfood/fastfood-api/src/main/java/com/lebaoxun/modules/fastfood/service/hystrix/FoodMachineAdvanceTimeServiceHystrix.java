package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineAdvanceTimeService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 取餐机预定时间配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@Component
public class FoodMachineAdvanceTimeServiceHystrix implements IFoodMachineAdvanceTimeService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public List<FoodMachineAdvanceTimeEntity> findPreDatesByMacIdOrAisleId(@RequestParam("macId") Integer macId, @RequestParam("aisleId") Integer aisleId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,FoodMachineAdvanceTimeEntity foodMachineAdvanceTime) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage batchSave(@RequestParam("adminId")Long adminId,@RequestBody List<FoodMachineAdvanceTimeEntity> advanceTimeList) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodMachineAdvanceTimeEntity foodMachineAdvanceTime) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
	@Override
	public ResponseMessage findAdvanceTimeByMacId(Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findPreOrderAndProByMacId(@RequestParam("macId") Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
}

