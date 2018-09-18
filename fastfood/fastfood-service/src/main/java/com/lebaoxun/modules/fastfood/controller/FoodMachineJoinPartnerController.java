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

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineJoinPartnerEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineJoinPartnerService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 加盟商
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 12:17:47
 */
@RestController
public class FoodMachineJoinPartnerController {
    @Autowired
    private FoodMachineJoinPartnerService foodMachineJoinPartnerService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineJoinPartnerService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineJoinPartnerEntity foodMachineJoinPartner = foodMachineJoinPartnerService.selectById(id);
        return ResponseMessage.ok().put("foodMachineJoinPartner", foodMachineJoinPartner);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/save")
    @RedisLock(value="fastfood:foodmachinejoinpartner:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineJoinPartnerEntity foodMachineJoinPartner){
    	foodMachineJoinPartner.setCreateTime(new Date());
		foodMachineJoinPartnerService.insert(foodMachineJoinPartner);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/update")
    @RedisLock(value="fastfood:foodmachinejoinpartner:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineJoinPartnerEntity foodMachineJoinPartner){
		foodMachineJoinPartnerService.updateById(foodMachineJoinPartner);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinejoinpartner/delete")
    @RedisLock(value="fastfood:foodmachinejoinpartner:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineJoinPartnerService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
