package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachinePartnerEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachinePartnerService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 合作公司
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */
@RestController
public class FoodMachinePartnerController {
    @Autowired
    private FoodMachinePartnerService foodMachinePartnerService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinepartner/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachinePartnerService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinepartner/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachinePartnerEntity foodMachinePartner = foodMachinePartnerService.selectById(id);
        return ResponseMessage.ok().put("foodMachinePartner", foodMachinePartner);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinepartner/save")
    @RedisLock(value="fastfood:foodmachinepartner:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachinePartnerEntity foodMachinePartner){
		foodMachinePartnerService.insert(foodMachinePartner);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinepartner/update")
    @RedisLock(value="fastfood:foodmachinepartner:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachinePartnerEntity foodMachinePartner){
		foodMachinePartnerService.updateById(foodMachinePartner);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinepartner/delete")
    @RedisLock(value="fastfood:foodmachinepartner:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachinePartnerService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
