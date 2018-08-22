package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lebaoxun.modules.fastfood.service.FoodOrderChildsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.FoodOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodOrderController {
    @Autowired
    private FoodOrderService foodOrderService;

    @Autowired
    private FoodOrderChildsService foodOrderChildsService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodOrderService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
		FoodOrderEntity foodOrder = foodOrderService.selectById(id);
        return ResponseMessage.ok().put("foodOrder", foodOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorder/save")
    @RedisLock(value="fastfood:foodorder:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder){
		foodOrderService.insert(foodOrder);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorder/update")
    @RedisLock(value="fastfood:foodorder:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodOrderEntity foodOrder){
		if(foodOrderService.updateById(foodOrder)){
            return ResponseMessage.ok();
        }
        return ResponseMessage.error("6002","取餐失败！");
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorder/delete")
    @RedisLock(value="fastfood:foodorder:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids){
		foodOrderService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    /**
     * 扫码查询订单信息
     * @param orderId
     * @return
     */
    @RequestMapping("/fastfood/getSweeptCodeOrderInfo")
    ResponseMessage getSweeptCodeOrderInfo(@RequestParam("orderId") String orderId) {
        Map<String,Object> orderMap=foodOrderService.getSweeptCodeOrderInfo(orderId);
        if (orderMap==null||orderMap.size()==0)
            return ResponseMessage.error("00002","订单不存！");
        List<Map<String,Object>> orderChildList=foodOrderChildsService.getSweeptCodeOrderChildsInfo(orderId);
        orderMap.put("orderLines",orderChildList);
        return ResponseMessage.ok(orderMap);
    }

}
