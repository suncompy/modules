package com.lebaoxun.modules.fastfood.controller;

import java.util.ArrayList;
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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;
import com.lebaoxun.modules.fastfood.entity.FoodProductCatEntity;
import com.lebaoxun.modules.fastfood.entity.FoodProductCatMaterialCatRcrtEntity;
import com.lebaoxun.modules.fastfood.service.FoodProductCatService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 餐品分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodProductCatController {
    @Autowired
    private FoodProductCatService foodProductCatService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodproductcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodProductCatService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodproductcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodProductCatEntity foodProductCat = foodProductCatService.selectById(id);
        return ResponseMessage.ok().put("foodProductCat", foodProductCat);
    }
    
    /**
     * 查询所有分类
     */
    @RequestMapping("/fastfood/foodproductcat/queryAll")
    ResponseMessage queryAll(){
    	return ResponseMessage.ok(foodProductCatService.selectList(new EntityWrapper<FoodProductCatEntity>().eq("status", 1)));
    }
    
    /**
     * 查询餐品分类下所有原料
     */
    @RequestMapping("/fastfood/foodproductcat/queryFoodMaterialById")
    ResponseMessage queryFoodMaterialById(@RequestParam("id")Integer id){
    	return ResponseMessage.ok(foodProductCatService.queryFoodMaterialById(id));
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodproductcat/save")
    @RedisLock(value="fastfood:foodproductcat:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodProductCatEntity foodProductCat){
    	foodProductCat.setCreateBy(adminId);
    	foodProductCat.setCreateTime(new Date());
		foodProductCatService.insert(foodProductCat);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodproductcat/update")
    @RedisLock(value="fastfood:foodproductcat:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodProductCatEntity foodProductCat){
    	foodProductCat.setUpdateBy(adminId);
    	foodProductCat.setUpdateTime(new Date());
		foodProductCatService.updateById(foodProductCat);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodproductcat/delete")
    @RedisLock(value="fastfood:foodproductcat:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodProductCatService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
