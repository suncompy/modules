package com.lebaoxun.modules.fastfood.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.ValidatorUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatService;
import com.lebaoxun.modules.fastfood.service.FoodMachineService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 取餐机分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineCatController {
    @Autowired
    private FoodMachineService foodMachineService;
    @Autowired
    private FoodMachineCatService foodMachineCatService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinecat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineCatService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinecat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineCatEntity foodMachineCat = foodMachineCatService.selectById(id);
        return ResponseMessage.ok().put("foodMachineCat", foodMachineCat);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecat/save")
    @RedisLock(value="fastfood:foodmachinecat:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatEntity foodMachineCat){
        foodMachineCat.setCreateBy(adminId);
        foodMachineCat.setCreateTime(new Date());
        foodMachineCat.setUpdateBy(adminId);
        foodMachineCat.setUpdateTime(new Date());
        ValidatorUtils.validateEntity(foodMachineCat);
		foodMachineCatService.insert(foodMachineCat);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinecat/update")
    @RedisLock(value="fastfood:foodmachinecat:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatEntity foodMachineCat){
        //如果机器分类有使用，如其它机器有用到，则不能进行此操作
        EntityWrapper<FoodMachineEntity> entityWrapper=new EntityWrapper<FoodMachineEntity>();
        entityWrapper.eq("cat_id",foodMachineCat.getId());
        List<FoodMachineEntity> foodMachineEntitis=foodMachineService.selectList(entityWrapper);
        if (foodMachineEntitis!=null&&foodMachineEntitis.size()>0){
            return ResponseMessage.error("60002","该机器分类已经有机器关联，不能进行删除！");
        }
		foodMachineCatService.updateById(foodMachineCat);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinecat/delete")
    @RedisLock(value="fastfood:foodmachinecat:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
        //如果机器分类有使用，如其它机器有用到，则不能进行此操作
        for (int catId:ids){
            EntityWrapper<FoodMachineEntity> entityWrapper=new EntityWrapper<FoodMachineEntity>();
            entityWrapper.eq("cat_id",catId);
            List<FoodMachineEntity> foodMachineEntitis=foodMachineService.selectList(entityWrapper);
            if (foodMachineEntitis!=null&&foodMachineEntitis.size()>0){
                return ResponseMessage.error("60002","该机器分类已经有机器关联，不能进行删除！");
            }
        }
		foodMachineCatService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
