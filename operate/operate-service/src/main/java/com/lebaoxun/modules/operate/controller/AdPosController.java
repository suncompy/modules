package com.lebaoxun.modules.operate.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lebaoxun.modules.fastfood.service.IFoodMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.StringUtils;
import com.lebaoxun.modules.operate.entity.AdPosEntity;
import com.lebaoxun.modules.operate.service.AdPosService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;

import javax.annotation.Resource;


/**
 * 广告位
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-22 23:06:49
 */
@RestController
public class AdPosController {
    @Resource
    private IFoodMachineService foodMachineService;
    @Autowired
    private AdPosService adPosService;

    /**
     * 列表
     */
    @RequestMapping("/operate/adpos/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = adPosService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/adpos/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		AdPosEntity adPos = adPosService.selectById(id);
        return ResponseMessage.ok().put("adPos", adPos);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/adpos/save")
    @RedisLock(value="operate:adpos:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody AdPosEntity adPos){
    	adPos.setCreateBy(adminId);
    	adPos.setCreateTime(new Date());
		adPosService.insert(adPos);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/adpos/update")
    @RedisLock(value="operate:adpos:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody AdPosEntity adPos){
    	adPos.setUpdateBy(adminId);
		adPosService.updateById(adPos);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/adpos/delete")
    @RedisLock(value="operate:adpos:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		adPosService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    @RequestMapping("/operate/adpos/get_advert_info")
    public ResponseMessage getAdvertInfo(@RequestParam(value = "macCode",required=false) String macCode,
            @RequestParam(value = "advertId",required=false) String advertId,
                                         @RequestParam(value = "advertCode",required=false) String advertCode,
                                         @RequestParam(value = "advertType",required=false) String advertType){
        EntityWrapper<AdPosEntity> entityWrapper=new EntityWrapper();
        if (StringUtils.isNotEmpty(advertId))
            entityWrapper.eq("id",advertId);
        if (StringUtils.isNotEmpty(advertCode))
            entityWrapper.eq("pos_code",advertCode);
        if (StringUtils.isNotEmpty(advertType))
            entityWrapper.eq("pos_type",advertType);
        List<Map<String,Object>> adPosResult= Lists.newArrayList();
        List<AdPosEntity> adPosEntityList=adPosService.selectList(entityWrapper);
        if (adPosEntityList==null||adPosEntityList.size()==0)
            return ResponseMessage.error("0000","广告数据还在生产中!");
        //如果macCode不为空，就确认该请求是机器心跳，需要同步机器网络状态
        if (StringUtils.isNotEmpty(macCode)){
            new Thread(()->{
                foodMachineService.updateMacNetStatus(macCode);
            }).start();
        }
        adPosEntityList.forEach(e->{
            Map<String,Object> adPosMap= Maps.newHashMap();
            adPosMap.put("advertId",e.getId());
            adPosMap.put("advertCode",e.getPosCode());
            adPosMap.put("advertName",e.getPosName());
            adPosMap.put("advertType",e.getPosType());
            adPosMap.put("fileType",e.getFileType());
            adPosMap.put("url",e.getUrl());
            adPosResult.add(adPosMap);
        });
        return ResponseMessage.ok(adPosResult);
    }

}
