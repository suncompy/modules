package com.lebaoxun.modules.operate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.operate.entity.AdPosEntity;
import com.lebaoxun.modules.operate.service.hystrix.AdPosServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 广告位
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-05 14:15:42
 */

@FeignClient(value="operate-service",fallback=AdPosServiceHystrix.class)
public interface IAdPosService {
	/**
     * 列表
     */
    @RequestMapping("/operate/adpos/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/adpos/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/adpos/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody AdPosEntity adPos);

    /**
     * 修改
     */
    @RequestMapping("/operate/adpos/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody AdPosEntity adPos);

    /**
     * 删除
     */
    @RequestMapping("/operate/adpos/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

