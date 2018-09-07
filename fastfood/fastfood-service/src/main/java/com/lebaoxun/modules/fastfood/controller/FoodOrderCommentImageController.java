package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentImageEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderCommentImageService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 评价图片表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */
@RestController
public class FoodOrderCommentImageController {
    @Autowired
    private FoodOrderCommentImageService foodOrderCommentImageService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodordercommentimage/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodOrderCommentImageService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodordercommentimage/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
		FoodOrderCommentImageEntity foodOrderCommentImage = foodOrderCommentImageService.selectById(id);
        return ResponseMessage.ok().put("foodOrderCommentImage", foodOrderCommentImage);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodordercommentimage/save")
    @RedisLock(value="fastfood:foodordercommentimage:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderCommentImageEntity foodOrderCommentImage){
		foodOrderCommentImageService.insert(foodOrderCommentImage);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodordercommentimage/update")
    @RedisLock(value="fastfood:foodordercommentimage:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderCommentImageEntity foodOrderCommentImage){
		foodOrderCommentImageService.updateById(foodOrderCommentImage);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodordercommentimage/delete")
    @RedisLock(value="fastfood:foodordercommentimage:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids){
		foodOrderCommentImageService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
