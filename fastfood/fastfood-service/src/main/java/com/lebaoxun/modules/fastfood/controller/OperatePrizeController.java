package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeEntity;
import com.lebaoxun.modules.fastfood.service.OperatePrizeService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 抽奖奖品配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
@RestController
public class OperatePrizeController {
    @Autowired
    private OperatePrizeService operatePrizeService;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateprize/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operatePrizeService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateprize/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		OperatePrizeEntity operatePrize = operatePrizeService.selectById(id);
        return ResponseMessage.ok().put("operatePrize", operatePrize);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateprize/save")
    @RedisLock(value="operate:operateprize:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperatePrizeEntity operatePrize){
    	operatePrize.setCreateBy(adminId);
    	operatePrize.setCreateTime(new Date());
		operatePrizeService.insert(operatePrize);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateprize/update")
    @RedisLock(value="operate:operateprize:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperatePrizeEntity operatePrize){
		operatePrizeService.updateById(operatePrize);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateprize/delete")
    @RedisLock(value="operate:operateprize:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operatePrizeService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    /**
     * 查询奖品
     * @param group
     * @return
     */
    @RequestMapping("/operate/operateprize/findPrizeByGroup")
    ResponseMessage findPrizeByGroup(@RequestParam("group") String group){
    	return ResponseMessage.ok(operatePrizeService.findPrizeByGroup(group));
    }
}
