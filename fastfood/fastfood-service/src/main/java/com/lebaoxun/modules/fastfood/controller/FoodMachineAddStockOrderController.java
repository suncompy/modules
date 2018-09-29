package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockHeadEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAddStockHeadService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAddStockOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机进货派单
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineAddStockOrderController {
    @Autowired
    private FoodMachineAddStockOrderService foodMachineAddStockOrderService;
    @Autowired
    private FoodMachineAddStockHeadService foodMachineAddStockHeadService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineAddStockOrderService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineAddStockOrderEntity foodMachineAddStockOrder = foodMachineAddStockOrderService.selectById(id);
        return ResponseMessage.ok().put("foodMachineAddStockOrder", foodMachineAddStockOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/save")
    @RedisLock(value="fastfood:foodmachineaddstockorder:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody List<FoodMachineAddStockOrderEntity> addStockOrderList){
        return foodMachineAddStockHeadService.createPickingOreder(adminId,addStockOrderList);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/update")
    @RedisLock(value="fastfood:foodmachineaddstockorder:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAddStockOrderEntity foodMachineAddStockOrder){
		foodMachineAddStockOrderService.updateById(foodMachineAddStockOrder);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/delete")
    @RedisLock(value="fastfood:foodmachineaddstockorder:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineAddStockOrderService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    /**
     * 补货员列表
     * @param userName
     * @param mobile
     * @param createTime
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/queryReplenishManList")
    ResponseMessage queryReplenishManList(@RequestParam(value = "userName",required = false)String userName,
                                                   @RequestParam(value = "mobile",required = false)String mobile,
                                                   @RequestParam(value = "createTime",required = false)String createTime){

        List<Map<String,Object>> ReplenishManList = foodMachineAddStockOrderService.queryReplenishManList(userName,mobile,createTime);
        int totalCount=ReplenishManList.size();
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(ReplenishManList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }

    /**
     * 配货员列表
     * @param userName
     * @param mobile
     * @param createTime
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/queryPickingManList")
    ResponseMessage queryPickingManList(@RequestParam(value = "userName",required = false)String userName,
                                          @RequestParam(value = "mobile",required = false)String mobile,
                                          @RequestParam(value = "createTime",required = false)String createTime){

        List<Map<String,Object>> ReplenishManList = foodMachineAddStockOrderService.queryPickingManList(userName,mobile,createTime);
        int totalCount=ReplenishManList.size();
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(ReplenishManList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }
    /**
     * 配货订单列表
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/queryPickingOrderList")
    ResponseMessage queryPickingOrderList(@RequestParam(value = "status",required = false)String status,
            @RequestParam(value = "macInfo",required = false)String macInfo,
                                        @RequestParam(value = "id",required = false)String id,
                                        @RequestParam(value = "sendOrderTime",required = false)String sendOrderTime){
        List<Map<String,Object>> ReplenishOrderList = foodMachineAddStockOrderService.queryPickingOrderList(status,macInfo,id,sendOrderTime);
        int totalCount=ReplenishOrderList.size();
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(ReplenishOrderList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }

    /**
     * 配货货道明细列表
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/queryPickingLineByHeadId")
    ResponseMessage queryPickingLineByHeadId(@RequestParam(value = "status",required = false)String status,
                                          @RequestParam(value = "headId",required = false)String headId){
        List<Map<String,Object>> pickingLinList = foodMachineAddStockOrderService.queryPickingLineByHeadId(status,headId);
        int totalCount=pickingLinList.size();
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(pickingLinList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }

    /**
     * 配货派单短信提醒
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/sendMsg")
    ResponseMessage sendMsg(@RequestParam(value = "macId")Integer macId){
        return foodMachineAddStockHeadService.sendMsg(macId);
    }

}
