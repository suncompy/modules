package com.lebaoxun.modules.fastfood.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.OperateActivityFirstOrderServiceHystrix;

/**
 * 首单活动表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:31
 */

@FeignClient(value="fastfood-service",fallback=OperateActivityFirstOrderServiceHystrix.class)
public interface IOperateActivityFirstOrderService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operateactivityfirstorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivityfirstorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivityfirstorder/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityFirstOrderEntity operateActivityFirstOrder);

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivityfirstorder/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityFirstOrderEntity operateActivityFirstOrder);

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivityfirstorder/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
    @RequestMapping("/operate/operateactivityfirstorder/findUnderwayActivity")
    OperateActivityFirstOrderEntity findUnderwayActivity();
}

