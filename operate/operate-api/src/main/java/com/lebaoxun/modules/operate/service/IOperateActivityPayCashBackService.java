package com.lebaoxun.modules.operate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.operate.entity.OperateActivityPayCashBackEntity;
import com.lebaoxun.modules.operate.service.hystrix.OperateActivityPayCashBackServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 充值返现表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */

@FeignClient(value="operate-service",fallback=OperateActivityPayCashBackServiceHystrix.class)
public interface IOperateActivityPayCashBackService {
	/**
     * 列表
     */
    @RequestMapping("/operate/operateactivitypaycashback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivitypaycashback/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivitypaycashback/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityPayCashBackEntity operateActivityPayCashBack);

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivitypaycashback/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityPayCashBackEntity operateActivityPayCashBack);

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivitypaycashback/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

