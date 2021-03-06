package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachinePartnerRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachinePartnerRefService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 机器合作商关联表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */
@RestController
public class FoodMachinePartnerRefController {
    @Autowired
    private FoodMachinePartnerRefService foodMachinePartnerRefService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachinePartnerRefService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachinePartnerRefEntity foodMachinePartnerRef = foodMachinePartnerRefService.selectById(id);
        return ResponseMessage.ok().put("foodMachinePartnerRef", foodMachinePartnerRef);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/save")
    @RedisLock(value="fastfood:foodmachinepartnerref:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachinePartnerRefEntity foodMachinePartnerRef){
		foodMachinePartnerRefService.insert(foodMachinePartnerRef);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/update")
    @RedisLock(value="fastfood:foodmachinepartnerref:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachinePartnerRefEntity foodMachinePartnerRef){
		foodMachinePartnerRefService.updateById(foodMachinePartnerRef);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinepartnerref/delete")
    @RedisLock(value="fastfood:foodmachinepartnerref:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachinePartnerRefService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
