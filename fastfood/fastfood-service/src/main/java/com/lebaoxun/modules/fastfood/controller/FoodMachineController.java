package com.lebaoxun.modules.fastfood.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.FoodMachineCatEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatAisleService;
import com.lebaoxun.modules.fastfood.service.FoodMachineCatService;
import com.lebaoxun.modules.fastfood.service.FoodMachineService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 取餐机
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineController {
    @Autowired
    private FoodMachineService foodMachineService;
    @Autowired
    private FoodMachineCatService foodMachineCatService;
    @Autowired
    private FoodMachineCatAisleService foodMachineCatAisleService;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachine/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        Map<Integer,String> catMap=new HashedMap();
        List<FoodMachineCatEntity> foodMachineCatEntities=foodMachineCatService.selectList(new EntityWrapper<FoodMachineCatEntity>());
        if (foodMachineCatEntities!=null){
            foodMachineCatEntities.forEach(e->catMap.put((int)e.getId(),e.getName()));
        }
        PageUtils page = foodMachineService.queryPage(params);
        List<FoodMachineEntity> foodMachineEntities=(List<FoodMachineEntity>)page.getList();
        foodMachineEntities.forEach(e->{
            e.setCatName(catMap.get(e.getCatId()));
        });
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachine/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		FoodMachineEntity foodMachine = foodMachineService.selectById(id);
        return ResponseMessage.ok().put("foodMachine", foodMachine);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachine/save")
    @RedisLock(value="fastfood:foodmachine:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineEntity foodMachine){
        //查询机器编码是否存在
        EntityWrapper<FoodMachineEntity> macWrapper=new EntityWrapper<FoodMachineEntity>();
        macWrapper.eq("IMEI",foodMachine.getImei());
        List<FoodMachineEntity> foodMachineEntities=foodMachineService.selectList(macWrapper);
        if (foodMachineEntities!=null&&foodMachineEntities.size()>0){
            return ResponseMessage.error("60001","机器编码["+foodMachine.getImei()+"]已存在，请修正！");
        }
        foodMachineService.save(adminId,foodMachine);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachine/update")
    @RedisLock(value="fastfood:foodmachine:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody FoodMachineEntity foodMachine){
        //查询机器编码是否存在
        EntityWrapper<FoodMachineEntity> macWrapper=new EntityWrapper<FoodMachineEntity>();
        macWrapper.eq("IMEI",foodMachine.getImei());
        List<FoodMachineEntity> foodMachineEntities=foodMachineService.selectList(macWrapper);
        if (foodMachineEntities!=null&&foodMachineEntities.size()>0){
            return ResponseMessage.error("60001","机器编码["+foodMachine.getImei()+"]已存在，请修正！");
        }
		if (foodMachineService.updateById(foodMachine)){
            return ResponseMessage.ok();
        }else{
            return ResponseMessage.error("0002","数据更新失败！");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachine/delete")
    @RedisLock(value="fastfood:foodmachine:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		foodMachineService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
    /**
     * 根据省市区搜索机器列表
     * @param areaCode
     * @return
     */
    @RequestMapping("/fastfood/foodmachine/findByAreaCode")
    ResponseMessage findByAreaCode(@RequestParam(value="areaCode",required=false)String areaCode,
    		@RequestParam(value="lat",required=false) Double lat,
    		@RequestParam(value="lng",required=false) Double lng){
    	return ResponseMessage.ok(foodMachineService.findByAreaCode(areaCode,lat,lng));
    }
    
    /**
     * 查询机器详情
     * @param macId
     * @return
     */
    @RequestMapping("/fastfood/foodmachine/findByMacId")
    ResponseMessage findByMacId(@RequestParam("macId") Integer macId){
    	return ResponseMessage.ok(foodMachineService.findByMacId(macId));
    }

    /**
     * 查询机器关联产品
     * @param macId
     * @param catId 产品分类ID
     * @return
     */
    @RequestMapping("/fastfood/foodmachine/findByMacRefProductById")
    ResponseMessage findByMacRefProductById(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("pageNo") Integer pageNo,
            @RequestParam("macId") Integer macId,
            @RequestParam("catId") Integer catId){
    	Map<String,Object> foodMachineMap = new HashMap<String,Object>();
        FoodMachineEntity mac = foodMachineService.findByMacOpenApiById(macId);
        if (mac==null)
            return ResponseMessage.error("00002","机器不存在！");
        foodMachineMap.put("mac", mac);
        foodMachineMap.put("itemList",foodMachineService.findByMacRefProductById(pageSize,pageNo,macId,catId));
        return ResponseMessage.ok(foodMachineMap);
    }
    /**
     * 搜索
     * @param keyword
     * @return
     */
    @RequestMapping("/fastfood/foodmachine/search")
    ResponseMessage search(@RequestParam("keyword") String keyword,
    		@RequestParam(value="lat",required=false) Double lat,
    		@RequestParam(value="lng",required=false) Double lng){
    	return ResponseMessage.ok(foodMachineService.search(keyword,lat,lng));
    }
    /**
     * 更新机器网络状态
     */
    @RequestMapping("/fastfood/foodmachine/updateMacNetStatus")
    ResponseMessage updateMacNetStatus(@RequestParam("macCode")String macCode){
        FoodMachineEntity machineEntity=new FoodMachineEntity();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        machineEntity.setNetStatus(sdf.format(date));
        EntityWrapper<FoodMachineEntity> macWrapper=new EntityWrapper<FoodMachineEntity>();
        macWrapper.eq("IMEI",macCode);
        foodMachineService.update(machineEntity,macWrapper);
        return ResponseMessage.ok();
    }

}
