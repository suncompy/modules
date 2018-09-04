package com.lebaoxun.modules.fastfood.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.FoodOrderBackEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodOrderBackServiceHystrix;

/**
 * 订单退款表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodOrderBackServiceHystrix.class)
public interface IFoodOrderBackService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodorderback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorderback/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorderback/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderBackEntity foodOrderBack);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorderback/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderBackEntity foodOrderBack);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorderback/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids);
    
    /**
     * 审核
     * @param checkId
     * @param orderNo
     * @param checkRemark
     * @param status
     * @return
     */
    @RequestMapping("/fastfood/foodorderback/checkOrderBack")
    ResponseMessage checkOrderBack(@RequestParam("checkId")Long checkId,
    		@RequestParam("orderNos")String[] orderNos,
    		@RequestParam("checkRemark")String checkRemark,
    		@RequestParam("status")Integer status);
}

