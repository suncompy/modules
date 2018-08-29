package com.lebaoxun.modules.fastfood.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeGetLogEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.OperatePrizeGetLogServiceHystrix;

/**
 * 抽奖记录表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */

@FeignClient(value="operate-service",fallback=OperatePrizeGetLogServiceHystrix.class)
public interface IOperatePrizeGetLogService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operateprizegetlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operateprizegetlog/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operateprizegetlog/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperatePrizeGetLogEntity operatePrizeGetLog);

    /**
     * 修改
     */
    @RequestMapping("/operate/operateprizegetlog/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperatePrizeGetLogEntity operatePrizeGetLog);

    /**
     * 删除
     */
    @RequestMapping("/operate/operateprizegetlog/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
    /**
     * 查询我的抽奖记录
     * @param userId
     * @param status
     * @param size
     * @param offset
     * @return
     */
    @RequestMapping("/operate/operateprizegetlog/findLogByUserId")
    ResponseMessage findLogByUserId(@RequestParam("userId") Long userId,
    		@RequestParam(value="status",required=false) Integer status,
    		@RequestParam(value="size",required=false) Integer size,
    		@RequestParam(value="offset",required=false) Integer offset);
    
    /**
     * 抽奖接口
     * @param userId
     * @param group
     * @return
     */
    @RequestMapping("/operate/operateprizegetlog/draw")
    ResponseMessage draw(@RequestParam("userId") Long userId, 
    		@RequestParam("group") String group);
    
}

