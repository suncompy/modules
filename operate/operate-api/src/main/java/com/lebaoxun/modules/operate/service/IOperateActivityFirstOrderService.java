package com.lebaoxun.modules.operate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.operate.entity.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.operate.service.hystrix.OperateActivityFirstOrderServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 首单活动表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */

@FeignClient(value="operate-service",fallback=OperateActivityFirstOrderServiceHystrix.class)
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
    
}

