package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineRepairOrderService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 维修派单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@Component
public class FoodMachineRepairOrderServiceHystrix implements IFoodMachineRepairOrderService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,FoodMachineRepairOrderEntity foodMachineRepairOrder) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodMachineRepairOrderEntity foodMachineRepairOrder) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage queryMaintenanceManList(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "mobile", required = false) String mobile, @RequestParam(value = "createTime", required = false) String createTime) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage queryRepairOrderList(@RequestParam(value = "status", required = false) String status, @RequestParam(value = "macInfo", required = false) String macInfo, @RequestParam(value = "id", required = false) String id, @RequestParam(value = "sendOrderTime", required = false) String sendOrderTime) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage sendMsg(@RequestParam(value = "macId") Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

}

