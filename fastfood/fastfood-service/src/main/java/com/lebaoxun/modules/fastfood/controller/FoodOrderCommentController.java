package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodOrderCommentEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderCommentService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 评价表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-07 17:58:09
 */
@RestController
public class FoodOrderCommentController {
    @Autowired
    private FoodOrderCommentService foodOrderCommentService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodordercomment/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodOrderCommentService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodordercomment/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
		FoodOrderCommentEntity foodOrderComment = foodOrderCommentService.selectById(id);
        return ResponseMessage.ok().put("foodOrderComment", foodOrderComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodordercomment/save")
    @RedisLock(value="fastfood:foodordercomment:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderCommentEntity foodOrderComment){
		foodOrderCommentService.insert(foodOrderComment);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodordercomment/update")
    @RedisLock(value="fastfood:foodordercomment:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderCommentEntity foodOrderComment){
		foodOrderCommentService.updateById(foodOrderComment);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodordercomment/delete")
    @RedisLock(value="fastfood:foodordercomment:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids){
		foodOrderCommentService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
    @RequestMapping("/fastfood/foodordercomment/publish")
    @RedisLock(value="fastfood:foodordercomment:publish:lock:#arg0")
    ResponseMessage publish(@RequestParam("userId") Long userId, 
    		@RequestParam("childId") Long childId,
    		@RequestBody FoodOrderCommentEntity comment){
    	foodOrderCommentService.publish(userId, childId, comment);
    	return ResponseMessage.ok();
    }
	
    @RequestMapping("/fastfood/foodordercomment/findByMacId")
    ResponseMessage findByMacId(@RequestParam("macId") Integer macId){
    	return ResponseMessage.ok(foodOrderCommentService.selectByMacId(macId));
    }
	
    @RequestMapping("/fastfood/foodordercomment/findLastByMacId")
    ResponseMessage findLastByMacId(@RequestParam("macId") Integer macId){
    	return ResponseMessage.ok(foodOrderCommentService.selectLastByMacId(macId));
    }

}
