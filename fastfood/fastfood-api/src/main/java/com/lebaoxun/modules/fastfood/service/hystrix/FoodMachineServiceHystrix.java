package com.lebaoxun.modules.fastfood.service.hystrix;
import java.util.List;
import java.util.Map;

import com.lebaoxun.modules.fastfood.entity.FoodMachineActivityRefEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCouponRefEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProActivityRefEntity;
import org.springframework.stereotype.Component;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineService;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseMessage findByMacRefProductById(Integer pageSize,Integer pageNo,Integer macId,Integer catId){
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
	@Override
	public ResponseMessage search(String keyword) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findMacCouponListByMacId(Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage refCouponByMacId(@RequestParam("adminId") Long adminId, @RequestBody List<FoodMachineCouponRefEntity> foodMachineCouponRefList) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findMacActListByMacId(@RequestParam("macId") Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage refActByMacId(@RequestParam("adminId") Long adminId, @RequestBody List<FoodMachineActivityRefEntity> activityRefEntityList) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage findMacProActListByMacId(@RequestParam("macId") Integer macId) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage refProActByMacId(@RequestParam("adminId") Long adminId, @RequestBody List<FoodMachineProActivityRefEntity> activityRefEntityList) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage updateMacNetStatus(@RequestParam("macCode") String macCode) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
}

