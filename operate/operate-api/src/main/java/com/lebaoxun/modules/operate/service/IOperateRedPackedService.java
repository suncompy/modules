package com.lebaoxun.modules.operate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.operate.entity.OperateRedPackedEntity;
import com.lebaoxun.modules.operate.service.hystrix.OperateRedPackedServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 现金红包
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:29
 */

@FeignClient(value="operate-service",fallback=OperateRedPackedServiceHystrix.class)
public interface IOperateRedPackedService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operateredpacked/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operateredpacked/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operateredpacked/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateRedPackedEntity operateRedPacked);

    /**
     * 修改
     */
    @RequestMapping("/operate/operateredpacked/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateRedPackedEntity operateRedPacked);

    /**
     * 删除
     */
    @RequestMapping("/operate/operateredpacked/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

