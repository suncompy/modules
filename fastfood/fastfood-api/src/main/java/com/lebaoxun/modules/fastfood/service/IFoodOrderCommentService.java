package com.lebaoxun.modules.fastfood.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodOrderCommentServiceHystrix;

/**
 * 评价表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */

@FeignClient(value="fastfood-service",fallback=FoodOrderCommentServiceHystrix.class)
public interface IFoodOrderCommentService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodordercomment/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodordercomment/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodordercomment/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderCommentEntity foodOrderComment);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodordercomment/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderCommentEntity foodOrderComment);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodordercomment/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids);
    
    @RequestMapping("/fastfood/foodordercomment/publish")
    ResponseMessage publish(@RequestParam("userId") Long userId, 
    		@RequestParam("childId") Long childId,
    		@RequestBody FoodOrderCommentEntity comment);
	
    @RequestMapping("/fastfood/foodordercomment/findByMacId")
    ResponseMessage findByMacId(@RequestParam("macId") Integer macId);
	
    @RequestMapping("/fastfood/foodordercomment/findLastByMacId")
    ResponseMessage findLastByMacId(@RequestParam("macId") Integer macId);
}

