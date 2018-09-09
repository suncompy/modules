package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAdvanceTimeService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机预定时间配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineAdvanceTimeController {
    @Autowired
    private FoodMachineAdvanceTimeService foodMachineAdvanceTimeService;
    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineAdvanceTimeService.queryPage(params);
        return ResponseMessage.ok(page);
    }

    /**
     * 查询机器 预订单产品列表
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/findPreOrderAndProByMacId")
    ResponseMessage findPreOrderAndProByMacId(@RequestParam("macId")Integer macId){
        List<FoodMachineAdvanceTimeEntity> preOderProList=foodMachineAdvanceTimeService.findPreOrderAndProByMacId(macId);
        int totalCount=preOderProList.size();
        if (totalCount>0){
            for (int i=0;i<totalCount;i++){
                preOderProList.get(i).setRowId(i+1);
            }
        }
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(preOderProList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }

    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineAdvanceTimeEntity foodMachineAdvanceTime = foodMachineAdvanceTimeService.selectById(id);
        return ResponseMessage.ok().put("foodMachineAdvanceTime", foodMachineAdvanceTime);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/save")
    @RedisLock(value="fastfood:foodmachineadvancetime:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAdvanceTimeEntity foodMachineAdvanceTime){
		foodMachineAdvanceTimeService.insert(foodMachineAdvanceTime);
        return ResponseMessage.ok();
    }
    @RequestMapping("/fastfood/foodmachineadvancetime/batchSave")
    ResponseMessage batchSave(@RequestParam("adminId")Long adminId,@RequestBody List<FoodMachineAdvanceTimeEntity> advanceTimeList){
        advanceTimeList.forEach(e->{
            e.setCreateBy(adminId);
            e.setCreateTime(new Date());
            foodMachineAdvanceTimeService.insert(e);
        });
        return ResponseMessage.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/update")
    @RedisLock(value="fastfood:foodmachineadvancetime:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAdvanceTimeEntity foodMachineAdvanceTime){
		foodMachineAdvanceTimeService.updateById(foodMachineAdvanceTime);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/delete")
    @RedisLock(value="fastfood:foodmachineadvancetime:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineAdvanceTimeService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
    @RequestMapping("/fastfood/foodmachineadvancetime/findAdvanceTimeByMacId")
    ResponseMessage findAdvanceTimeByMacId(@RequestParam("macId")Integer macId){
    	return ResponseMessage.ok(foodMachineAdvanceTimeService.findAdvanceTimeByMacId(macId));
    }

}
