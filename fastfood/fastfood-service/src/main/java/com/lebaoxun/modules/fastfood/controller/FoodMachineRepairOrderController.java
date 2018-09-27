package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineRepairOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 维修派单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineRepairOrderController {
    @Autowired
    private FoodMachineRepairOrderService foodMachineRepairOrderService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineRepairOrderService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineRepairOrderEntity foodMachineRepairOrder = foodMachineRepairOrderService.selectById(id);
        return ResponseMessage.ok().put("foodMachineRepairOrder", foodMachineRepairOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/save")
    @RedisLock(value="fastfood:foodmachinerepairorder:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineRepairOrderEntity foodMachineRepairOrder){
        foodMachineRepairOrder.setCreateBy(adminId);
        foodMachineRepairOrder.setCreateTime(new Date());
        foodMachineRepairOrder.setStatus(0);
        foodMachineRepairOrder.setUpdateTime(new Date());
		foodMachineRepairOrderService.insert(foodMachineRepairOrder);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/update")
    @RedisLock(value="fastfood:foodmachinerepairorder:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineRepairOrderEntity foodMachineRepairOrder){
        foodMachineRepairOrder.setUpdateTime(new Date());
        foodMachineRepairOrder.setRepairFinishTime(new Date());
		foodMachineRepairOrderService.updateById(foodMachineRepairOrder);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/delete")
    @RedisLock(value="fastfood:foodmachinerepairorder:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineRepairOrderService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    /**
     * 维修员列表
     * @param userName
     * @param mobile
     * @param createTime
     * @return
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/queryMaintenanceManList")
    ResponseMessage queryMaintenanceManList(@RequestParam(value = "userName",required = false)String userName,
                                        @RequestParam(value = "mobile",required = false)String mobile,
                                        @RequestParam(value = "createTime",required = false)String createTime){

        List<Map<String,Object>> ReplenishManList = foodMachineRepairOrderService.queryMaintenanceManList(userName,mobile,createTime);
        int totalCount=ReplenishManList.size();
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(ReplenishManList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }

    /**
     * 维修派单列表
     * @return
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/queryRepairOrderList")
    ResponseMessage queryRepairOrderList(@RequestParam(value = "status",required = false)String status,
                                          @RequestParam(value = "macInfo",required = false)String macInfo,
                                          @RequestParam(value = "id",required = false)String id,
                                          @RequestParam(value = "sendOrderTime",required = false)String sendOrderTime){
        List<Map<String,Object>> RepairOrderList = foodMachineRepairOrderService.queryRepairOrderList(status,macInfo,id,sendOrderTime);
        int totalCount=RepairOrderList.size();
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(RepairOrderList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }

}
