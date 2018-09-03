package com.lebaoxun.modules.fastfood.service;

import com.lebaoxun.modules.fastfood.entity.FoodMachineActivityRefEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCouponRefEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProActivityRefEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * 取餐机
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineServiceHystrix.class)
public interface IFoodMachineService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachine/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachine/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachine/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineEntity foodMachine);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachine/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineEntity foodMachine);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachine/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
    /**
     * 根据省市区搜索机器列表
     * @param areaCode
     * @return
     */
    @RequestMapping("/fastfood/foodmachine/findByAreaCode")
    ResponseMessage findByAreaCode(@RequestParam("areaCode")String areaCode);
    
    /**
     * 查询机器详情
     * @param macId
     * @return
     */
    @RequestMapping("/fastfood/foodmachine/findByMacId")
    ResponseMessage findByMacId(@RequestParam("macId") Integer macId);

    @RequestMapping("/fastfood/foodmachine/findByMacRefProductById")
    ResponseMessage findByMacRefProductById(@RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("pageNo") Integer pageNo,
                                            @RequestParam("macId") Integer macId,
                                            @RequestParam("catId") Integer catId);
    
    /**
     * 搜索
     * @param keyword
     * @return
     */
    @RequestMapping("/fastfood/foodmachine/search")
    ResponseMessage search(@RequestParam("keyword") String keyword);

    /**
     * 关联机器优惠券
     */
    @RequestMapping("/fastfood/foodmachinecouponref/findMacCouponListByMacId")
    ResponseMessage findMacCouponListByMacId(@RequestParam("macId")Integer macId);

    /**
     * 关联机器优惠券
     */
    @RequestMapping("/fastfood/foodmachinecouponref/refCouponByMacId")
    ResponseMessage refCouponByMacId(
            @RequestParam("adminId")Long adminId,
            @RequestBody List<FoodMachineCouponRefEntity> foodMachineCouponRefList);


    /**
     * 关联机器活动
     */
    @RequestMapping("/fastfood/foodmachineactivityref/findMacActivityListByMacId")
    ResponseMessage findMacActListByMacId(@RequestParam("macId")Integer macId);

    /**
     */
    @RequestMapping("/fastfood/foodmachineactivityref/refMacActivity")
    ResponseMessage refActByMacId(
            @RequestParam("adminId")Long adminId,
            @RequestBody List<FoodMachineActivityRefEntity> activityRefEntityList);

    /**
     * 关联机器产品活动
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/findProActivityListByMacId")
    ResponseMessage findMacProActListByMacId(@RequestParam("macId")Integer macId);

    /**
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/refMacActivity")
    ResponseMessage refProActByMacId(
            @RequestParam("adminId")Long adminId,
            @RequestBody List<FoodMachineProActivityRefEntity> activityRefEntityList);
}

