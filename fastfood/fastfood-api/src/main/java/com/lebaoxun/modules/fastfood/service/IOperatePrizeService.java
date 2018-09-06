package com.lebaoxun.modules.fastfood.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeEntity;
import com.lebaoxun.modules.fastfood.service.hystrix.OperatePrizeServiceHystrix;

/**
 * 抽奖奖品配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */

@FeignClient(value="fastfood-service",fallback=OperatePrizeServiceHystrix.class)
public interface IOperatePrizeService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operateprize/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operateprize/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operateprize/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperatePrizeEntity operatePrize);

    /**
     * 修改
     */
    @RequestMapping("/operate/operateprize/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperatePrizeEntity operatePrize);

    /**
     * 删除
     */
    @RequestMapping("/operate/operateprize/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
    /**
     * 查询奖品
     * @param group
     * @return
     */
    @RequestMapping("/operate/operateprize/findPrizeByGroup")
    ResponseMessage findPrizeByGroup(@RequestParam("group") String group);
    
}

