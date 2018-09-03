package com.lebaoxun.modules.fastfood.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineActivityRefEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCouponRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineActivityRefService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:23
 */
@RestController
public class FoodMachineActivityRefController {
    @Autowired
    private FoodMachineActivityRefService foodMachineActivityRefService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineactivityref/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineActivityRefService.queryPage(params);
        return ResponseMessage.ok(page);
    }

    /**
     * 查询关联机器活动
     */
    @RequestMapping("/fastfood/foodmachineactivityref/findMacActivityListByMacId")
    ResponseMessage findMacActivityListByMacId(@RequestParam("macId")Integer macId){
        List<FoodMachineActivityRefEntity> foodMachineActivityRefEntities = foodMachineActivityRefService.findMacActivityListByMacId(macId);
        int totalCount=foodMachineActivityRefEntities.size();
        if (totalCount>0){
            for (int i=0;i<totalCount;i++){
                foodMachineActivityRefEntities.get(i).setRowId(i+1);
            }
        }
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(foodMachineActivityRefEntities,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }
    /**
     * 关联机器活动
     */
    @RequestMapping("/fastfood/foodmachineactivityref/refMacActivity")
    ResponseMessage refMacActivity(@RequestParam("adminId")Long adminId,
                                   @RequestBody List<FoodMachineActivityRefEntity>foodMachineActivityRefEntityList){
        if (foodMachineActivityRefEntityList==null)return ResponseMessage.error("00002","数据异常");
        foodMachineActivityRefEntityList.forEach(e->{
            if ((e.getId()==null||e.getId()==0)&&e.getIsRef()==1){
                EntityWrapper<FoodMachineActivityRefEntity> actRefWrapper=new EntityWrapper();
                actRefWrapper.eq("mac_id",e.getMacId());
                actRefWrapper.eq("activity_type",e.getActivityType());
                FoodMachineActivityRefEntity actnRef=foodMachineActivityRefService.selectOne(actRefWrapper);
                if (actnRef==null||actnRef.getId()==0) {
                    e.setCreateBy((int)adminId.longValue());
                    e.setCreateTime(new Date());
                    foodMachineActivityRefService.insert(e);
                }else{//更新
                    foodMachineActivityRefService.updateById(e);
                }
            }else if (e.getId()>0&&e.getIsRef()==2){//如果已经关联，但前端又设置了不关联，则删除
                foodMachineActivityRefService.deleteById(e.getId());
            }
        });
        return ResponseMessage.ok();
    }
    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineactivityref/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineActivityRefEntity foodMachineActivityRef = foodMachineActivityRefService.selectById(id);
        return ResponseMessage.ok().put("foodMachineActivityRef", foodMachineActivityRef);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineactivityref/save")
    @RedisLock(value="fastfood:foodmachineactivityref:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineActivityRefEntity foodMachineActivityRef){
		foodMachineActivityRefService.insert(foodMachineActivityRef);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineactivityref/update")
    @RedisLock(value="fastfood:foodmachineactivityref:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineActivityRefEntity foodMachineActivityRef){
		foodMachineActivityRefService.updateById(foodMachineActivityRef);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineactivityref/delete")
    @RedisLock(value="fastfood:foodmachineactivityref:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineActivityRefService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
