package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;
import com.lebaoxun.modules.fastfood.service.FoodMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatAisleService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机分类通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineCatAisleController {
    @Autowired
    private FoodMachineService foodMachineService;
    @Autowired
    private FoodMachineCatAisleService foodMachineCatAisleService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinecataisle/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = foodMachineCatAisleService.queryPage(params);
        return ResponseMessage.ok(page);
    }

    @RequestMapping("/fastfood/foodmachinecataisle/findAisleInfoByCatId")
    ResponseMessage findAisleInfoByCatId(@RequestParam("catId")Integer catId){
        List<Map<String,Object>> aisleList=foodMachineCatAisleService.findAisleInfoByCatId(catId);
        int totalCount=aisleList.size();
        int pageSize=100;
        int currPage=0;
        PageUtils page=new PageUtils(aisleList,totalCount,pageSize,0);
        return ResponseMessage.ok(page);
    }
    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecataisle/refMacCatByAisle")
    @RedisLock(value="fastfood:refMacCatByAisle:save:lock:#arg0")
    ResponseMessage refMacCatByAisle(@RequestParam("adminId")Long adminId,
                                     @RequestBody List<FoodMachineCatAisleEntity> catAisleEntityList){
        if (catAisleEntityList==null||catAisleEntityList.size()==0)
            return ResponseMessage.error("60001","货道数据不能为空！");
        //如果机器分类有使用，如其它机器有用到，则不能进行此操作
        for (int i=0;i<catAisleEntityList.size();i++){
            FoodMachineCatAisleEntity e=catAisleEntityList.get(i);
            if (e.getId() != null && e.getId() > 0) {
                EntityWrapper<FoodMachineEntity> entityWrapper=new EntityWrapper<FoodMachineEntity>();
                entityWrapper.eq("cat_id",e.getCatId());
                List<FoodMachineEntity> foodMachineEntitis=foodMachineService.selectList(entityWrapper);
                if (foodMachineEntitis!=null&&foodMachineEntitis.size()>0){
                   return ResponseMessage.error("60002","该机器分类已经有机器关联，不能进行货道修改！");
                }
                break;
            }
        }
        catAisleEntityList.forEach(e->{
            if (e.getId()!=null&&e.getId()>0){//修改
                foodMachineCatAisleService.updateById(e);
            }else {
                if (e.getId()==null)
                    e.setId(0);
                e.setCreateBy(adminId);
                e.setCreateTime(new Date());
                foodMachineCatAisleService.saveFoodMachineCatAisle(e);
            }
        });
        return ResponseMessage.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinecataisle/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineCatAisleEntity foodMachineCatAisle = foodMachineCatAisleService.selectById(id);
        return ResponseMessage.ok().put("foodMachineCatAisle", foodMachineCatAisle);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecataisle/save")
    @RedisLock(value="fastfood:foodmachinecataisle:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatAisleEntity foodMachineCatAisle){
        //数据校验
        if(foodMachineCatAisle.getCatId()==null||foodMachineCatAisle.getCatId()==0)
            return ResponseMessage.error("0004","机器分类ID为空");
        //货道位置不能重复
        EntityWrapper<FoodMachineCatAisleEntity> macCatWrapper=new EntityWrapper<FoodMachineCatAisleEntity>();
        macCatWrapper.eq("x",foodMachineCatAisle.getX()).eq("y",foodMachineCatAisle.getY());
        FoodMachineCatAisleEntity macCat=foodMachineCatAisleService.selectOne(macCatWrapper);
        if(macCat!=null)
            return ResponseMessage.error("0005","机器货道位置重复");
		foodMachineCatAisleService.insert(foodMachineCatAisle);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinecataisle/update")
    @RedisLock(value="fastfood:foodmachinecataisle:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineCatAisleEntity foodMachineCatAisle){
		foodMachineCatAisleService.updateById(foodMachineCatAisle);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinecataisle/delete")
    @RedisLock(value="fastfood:foodmachinecataisle:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineCatAisleService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
