package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodOrderBackEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderBackService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 订单退款表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodOrderBackController {
    @Autowired
    private FoodOrderBackService foodOrderBackService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodorderback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodOrderBackService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorderback/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
		FoodOrderBackEntity foodOrderBack = foodOrderBackService.selectById(id);
        return ResponseMessage.ok().put("foodOrderBack", foodOrderBack);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorderback/save")
    @RedisLock(value="fastfood:foodorderback:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderBackEntity foodOrderBack){
		foodOrderBackService.insert(foodOrderBack);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorderback/update")
    @RedisLock(value="fastfood:foodorderback:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderBackEntity foodOrderBack){
		foodOrderBackService.updateById(foodOrderBack);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorderback/delete")
    @RedisLock(value="fastfood:foodorderback:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids){
		foodOrderBackService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
