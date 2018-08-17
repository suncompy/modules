package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCouponRefEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCouponRefService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 
 *
 * @author F.Bin
 * @email 270852221@qq.com
 * @date 2018-08-17 15:32:23
 */
@RestController
public class FoodMachineCouponRefController {
    @Autowired
    private FoodMachineCouponRefService foodMachineCouponRefService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinecouponref/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineCouponRefService.queryPage(params);
        return ResponseMessage.ok(page);
    }

    /**
     * 关联机器优惠券
     */
    @RequestMapping("/fastfood/foodmachinecouponref/findMacCouponListByMacId")
    ResponseMessage findMacCouponListByMacId(@RequestParam("macId")Integer macId){
        List<FoodMachineCouponRefEntity> foodMachineCouponRefEntities = foodMachineCouponRefService.findMacCouponListByMacId(macId);
        int totalCount=foodMachineCouponRefEntities.size();
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(foodMachineCouponRefEntities,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }

    /**
     * 关联机器优惠券
     */
    @RequestMapping("/fastfood/foodmachinecouponref/refCouponByMacId")
    ResponseMessage refCouponByMacId(
            @RequestParam("adminId")Long adminId,
            @RequestParam("foodMachineCouponRefList")List<FoodMachineCouponRefEntity>foodMachineCouponRefList){
        if(foodMachineCouponRefList==null)return ResponseMessage.error("00002","数据异常");
        // 关联机器前，先删除，再关联
        foodMachineCouponRefList.forEach(e->{
             //如果id为空，说明该产品还没关联
            if ((e.getId()==null||e.getId()==0)&&e.getIsRef()==1){
                e.setCreateBy((int)adminId.longValue());
                e.setCreateTime(new Date());
                foodMachineCouponRefService.insert(e);
            }else if (e.getId()>0&&e.getIsRef()==2){//如果已经关联，但前端又设置了不关联，则删除
                foodMachineCouponRefService.deleteById(e.getId());
            }
        });
        return ResponseMessage.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinecouponref/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineCouponRefEntity foodMachineCouponRef = foodMachineCouponRefService.selectById(id);
        return ResponseMessage.ok().put("foodMachineCouponRef", foodMachineCouponRef);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecouponref/save")
    @RedisLock(value="fastfood:foodmachinecouponref:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCouponRefEntity foodMachineCouponRef){
		foodMachineCouponRefService.insert(foodMachineCouponRef);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinecouponref/update")
    @RedisLock(value="fastfood:foodmachinecouponref:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCouponRefEntity foodMachineCouponRef){
		foodMachineCouponRefService.updateById(foodMachineCouponRef);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinecouponref/delete")
    @RedisLock(value="fastfood:foodmachinecouponref:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineCouponRefService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
