package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.modules.fastfood.entity.FoodMachineActivityRefEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineProActivityRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineProActivityRefService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:24
 */
@RestController
public class FoodMachineProActivityRefController {
    @Autowired
    private FoodMachineProActivityRefService foodMachineProActivityRefService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineProActivityRefService.queryPage(params);
        return ResponseMessage.ok(page);
    }

    /**
     * 查询关联机器产品活动
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/findProActivityListByMacId")
    ResponseMessage findProActivityListByMacId(@RequestParam("macId")Integer macId){
        List<FoodMachineProActivityRefEntity> foodMachineProActList = foodMachineProActivityRefService.foodMachineProActListByMacId(macId);
        int totalCount=foodMachineProActList.size();
        if (totalCount>0){
            for (int i=0;i<totalCount;i++){
                foodMachineProActList.get(i).setRowId(i+1);
            }
        }
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(foodMachineProActList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }

    /**
     * 关联机器活动
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/refMacActivity")
    ResponseMessage refMacProActivity(@RequestParam("adminId")Long adminId,
                                      @RequestBody List<FoodMachineProActivityRefEntity>foodMachineProActList){
        if (foodMachineProActList==null)return ResponseMessage.error("00002","数据异常");
        foodMachineProActList.forEach(e->{
            if ((e.getId()==null||e.getId()==0)||e.getIsRef()==1){
                EntityWrapper<FoodMachineProActivityRefEntity> actProRefWrapper=new EntityWrapper();
                actProRefWrapper.eq("mac_id",e.getMacId());
                actProRefWrapper.eq("aisle_id",e.getAisleId());
                FoodMachineProActivityRefEntity actProRef=foodMachineProActivityRefService.selectOne(actProRefWrapper);
                if (actProRef==null||actProRef.getId()==0) {
                    e.setCreateBy((int)adminId.longValue());
                    e.setCreateTime(new Date());
                    foodMachineProActivityRefService.insert(e);
                }else{//更新
                    foodMachineProActivityRefService.updateById(e);
                }
            }else if (e.getId()>0&&e.getIsRef()==2){//如果已经关联，但前端又设置了不关联，则删除
                foodMachineProActivityRefService.deleteById(e.getId());
            }
        });
        return ResponseMessage.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineProActivityRefEntity foodMachineProActivityRef = foodMachineProActivityRefService.selectById(id);
        return ResponseMessage.ok().put("foodMachineProActivityRef", foodMachineProActivityRef);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/save")
    @RedisLock(value="fastfood:foodmachineproactivityref:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineProActivityRefEntity foodMachineProActivityRef){
		foodMachineProActivityRefService.insert(foodMachineProActivityRef);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/update")
    @RedisLock(value="fastfood:foodmachineproactivityref:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineProActivityRefEntity foodMachineProActivityRef){
		foodMachineProActivityRefService.updateById(foodMachineProActivityRef);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineproactivityref/delete")
    @RedisLock(value="fastfood:foodmachineproactivityref:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineProActivityRefService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
