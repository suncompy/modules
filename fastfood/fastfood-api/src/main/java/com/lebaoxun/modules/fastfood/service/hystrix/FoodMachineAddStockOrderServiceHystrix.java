package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineAddStockOrderService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 取餐机进货派单
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@Component
public class FoodMachineAddStockOrderServiceHystrix implements IFoodMachineAddStockOrderService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Integer id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,List<FoodMachineAddStockOrderEntity> addStockOrderList) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,FoodMachineAddStockOrderEntity foodMachineAddStockOrder) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Integer[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage queryReplenishManList(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "mobile", required = false) String mobile, @RequestParam(value = "createTime", required = false) String createTime) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage queryPickingManList(@RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "mobile", required = false) String mobile, @RequestParam(value = "createTime", required = false) String createTime) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage queryPickingOrderList(@RequestParam(value = "status", required = false) String status, @RequestParam(value = "macInfo", required = false) String macInfo, @RequestParam(value = "id", required = false) String id, @RequestParam(value = "sendOrderTime", required = false) String sendOrderTime) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage queryPickingLineByHeadId(@RequestParam(value = "status", required = false) String status, @RequestParam(value = "headId", required = false) String headId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage sendMsg(@RequestParam(value = "macId") Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

}

