package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
        if (addStockOrderList!=null&&addStockOrderList.size()>0){
            for (FoodMachineAddStockOrderEntity e:addStockOrderList){
                //首先判断补货、配货单是否在进行中
                EntityWrapper<FoodMachineAddStockOrderEntity> entityWrapper=new EntityWrapper<FoodMachineAddStockOrderEntity>();
                entityWrapper.eq("mac_id",e.getMacId());
                entityWrapper.eq("x",e.getX());
                entityWrapper.eq("y",e.getY());
                entityWrapper.eq("type",e.getType());
                entityWrapper.eq("status",0);
                List<FoodMachineAddStockOrderEntity> cList=foodMachineAddStockOrderService.selectList(entityWrapper);
                if (cList!=null&&cList.size()>0){
                    return ResponseMessage.error("600002","机器["+e.getMacId()
                            +"],货道["+e.getX()+"-"+e.getY()+"已有补货单在进行中！]!");
                }
                e.setStatus(0);
                e.setCreateBy(adminId);
                e.setCreateTime(new Date());
                e.setUpdateTime(new Date());
                foodMachineAddStockOrderService.insert(e);
            }
        }else {
            return ResponseMessage.error("600001","未收到补货数据!");
        }
        return ResponseMessage.ok();
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

}
