package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineErrorLogEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineErrorLogService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 终端机器错误日志表
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-09-23 19:35:01
 */
@RestController
public class FoodMachineErrorLogController {
    @Autowired
    private FoodMachineErrorLogService foodMachineErrorLogService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineErrorLogService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineErrorLogEntity foodMachineErrorLog = foodMachineErrorLogService.selectById(id);
        return ResponseMessage.ok().put("foodMachineErrorLog", foodMachineErrorLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/save")
    @RedisLock(value="fastfood:foodmachineerrorlog:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineErrorLogEntity foodMachineErrorLog){
		foodMachineErrorLogService.insert(foodMachineErrorLog);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/update")
    @RedisLock(value="fastfood:foodmachineerrorlog:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineErrorLogEntity foodMachineErrorLog){
		foodMachineErrorLogService.updateById(foodMachineErrorLog);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineerrorlog/delete")
    @RedisLock(value="fastfood:foodmachineerrorlog:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineErrorLogService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
