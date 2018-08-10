package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodWeekMenuEntity;
import com.lebaoxun.modules.fastfood.service.FoodWeekMenuService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 每周菜谱
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodWeekMenuController {
    @Autowired
    private FoodWeekMenuService foodWeekMenuService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodweekmenu/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodWeekMenuService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodweekmenu/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodWeekMenuEntity foodWeekMenu = foodWeekMenuService.selectById(id);
        return ResponseMessage.ok().put("foodWeekMenu", foodWeekMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodweekmenu/save")
    @RedisLock(value="fastfood:foodweekmenu:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodWeekMenuEntity foodWeekMenu){
		foodWeekMenuService.insert(foodWeekMenu);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodweekmenu/update")
    @RedisLock(value="fastfood:foodweekmenu:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodWeekMenuEntity foodWeekMenu){
		foodWeekMenuService.updateById(foodWeekMenu);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodweekmenu/delete")
    @RedisLock(value="fastfood:foodweekmenu:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodWeekMenuService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
