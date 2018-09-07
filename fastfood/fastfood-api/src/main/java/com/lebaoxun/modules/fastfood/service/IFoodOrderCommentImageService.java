package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentImageEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodOrderCommentImageServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 评价图片表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */

@FeignClient(value="fastfood-service",fallback=FoodOrderCommentImageServiceHystrix.class)
public interface IFoodOrderCommentImageService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodordercommentimage/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodordercommentimage/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodordercommentimage/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderCommentImageEntity foodOrderCommentImage);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodordercommentimage/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderCommentImageEntity foodOrderCommentImage);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodordercommentimage/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids);
    
}

