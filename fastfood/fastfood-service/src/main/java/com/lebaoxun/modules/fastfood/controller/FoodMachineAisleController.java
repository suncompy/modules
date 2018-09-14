package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;
import com.lebaoxun.commons.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineAisleController {
    @Autowired
    private FoodMachineAisleService foodMachineAisleService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaisle/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineAisleService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaisle/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineAisleEntity foodMachineAisle = foodMachineAisleService.selectById(id);
        return ResponseMessage.ok().put("foodMachineAisle", foodMachineAisle);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaisle/save")
    @RedisLock(value="fastfood:foodmachineaisle:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAisleEntity foodMachineAisle){
		foodMachineAisleService.insert(foodMachineAisle);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaisle/update")
    @RedisLock(value="fastfood:foodmachineaisle:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineAisleEntity foodMachineAisle){
		foodMachineAisleService.updateById(foodMachineAisle);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaisle/delete")
    @RedisLock(value="fastfood:foodmachineaisle:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineAisleService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    /**
     * 关联产品列表查询,包括产品、名称产品价格
     */
    @RequestMapping("/fastfood/foodmachineaisle/findMachineAisleListByMacId")
    ResponseMessage findMachineAisleListByMacId(@RequestParam("macId")Integer macId){
        List<FoodMachineRefAisleEntity> foodMachineAisleEntityList = foodMachineAisleService.findMachineAisleListByMacId(macId);
        int totalCount=foodMachineAisleEntityList.size();
        int pageSize=100;
        int currPage=0;
        if (foodMachineAisleEntityList!=null){
            for (int i=0;i<foodMachineAisleEntityList.size();i++){
                foodMachineAisleEntityList.get(i).setRowId(i+1);
            }
        }
        PageUtils page=new PageUtils(foodMachineAisleEntityList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }
    /**
     * ids 机器渠道关联表主键集，以逗号拼接
     * 关联产品、分类
     */
    @RequestMapping("/fastfood/foodmachineaisle/refProductAndType")
    @RedisLock(value="fastfood:foodmachineaisle:refProductAndType:lock:#arg0")
    ResponseMessage refProductAndType(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineRefAisleEntity foodMachineAisle){
        foodMachineAisleService.refProductAndType(adminId,foodMachineAisle);
        return ResponseMessage.ok();
    }
    
    /**
     * 根据机器查询产品
     * @param macId
     * @param productCatId
     * @return
     */
    @RequestMapping("/fastfood/foodmachineaisle/findProductByMacIdAndProductCatId")
    ResponseMessage findProductByMacIdAndProductCatId(
    		@RequestParam("macId")Integer macId, 
    		@RequestParam(value="productCatId",required=false)Integer productCatId){
    	return ResponseMessage.ok(foodMachineAisleService.findProductByMacIdAndProductCatId(macId, productCatId));
    }
    
    @RequestMapping("/fastfood/foodmachineaisle/findProductByMacIdAndWeek")
    ResponseMessage findProductByMacIdAndWeek(@RequestParam("macId") Integer macId, 
    		@RequestParam("week")Integer week){
    	return ResponseMessage.ok(foodMachineAisleService.findProductByMacIdAndWeek(macId, week));
    }
    
    @RequestMapping("/fastfood/foodmachineaisle/findProductByMacIdAndAdTime")
    ResponseMessage findProductByMacIdAndAdTime(@RequestParam("macId") Integer macId, 
    		@RequestParam("time") String time, 
    		@RequestParam(value="productCatId",required=false) Integer productCatId){
    	return ResponseMessage.ok(foodMachineAisleService.findProductByMacIdAndAdTime(macId, time, productCatId));
    }
}
