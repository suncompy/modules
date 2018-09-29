package com.lebaoxun.modules.fastfood.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.FoodMachineAddStockOrderServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.List;
import java.util.Map;

/**
 * 取餐机进货派单
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */

@FeignClient(value="fastfood-service",fallback=FoodMachineAddStockOrderServiceHystrix.class)
public interface IFoodMachineAddStockOrderService {
	/**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody List<FoodMachineAddStockOrderEntity> addStockOrderList);

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAddStockOrderEntity foodMachineAddStockOrder);

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
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
                                          @RequestParam(value = "createTime",required = false)String createTime);
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
                                        @RequestParam(value = "createTime",required = false)String createTime);

    @RequestMapping("/fastfood/foodmachineaddstockorder/queryPickingOrderList")
    ResponseMessage queryPickingOrderList(@RequestParam(value = "status",required = false)String status,
                                          @RequestParam(value = "macInfo",required = false)String macInfo,
                                          @RequestParam(value = "id",required = false)String id,
                                          @RequestParam(value = "sendOrderTime",required = false)String sendOrderTime);

    @RequestMapping("/fastfood/foodmachineaddstockorder/queryPickingLineByHeadId")
    ResponseMessage queryPickingLineByHeadId(@RequestParam(value = "status",required = false)String status,
                                             @RequestParam(value = "headId",required = false)String headId);
    /**
     * 配货派单短信提醒
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/sendMsg")
    ResponseMessage sendMsg(@RequestParam(value = "macId")Integer macId);
}

