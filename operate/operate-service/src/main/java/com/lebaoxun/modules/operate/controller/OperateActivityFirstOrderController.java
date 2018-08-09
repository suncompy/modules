package com.lebaoxun.modules.operate.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.operate.service.OperateActivityFirstOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 首单活动表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */
@RestController
public class OperateActivityFirstOrderController {
    @Autowired
    private OperateActivityFirstOrderService operateActivityFirstOrderService;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateactivityfirstorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operateActivityFirstOrderService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivityfirstorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		OperateActivityFirstOrderEntity operateActivityFirstOrder = operateActivityFirstOrderService.selectById(id);
        return ResponseMessage.ok().put("operateActivityFirstOrder", operateActivityFirstOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivityfirstorder/save")
    @RedisLock(value="operate:operateactivityfirstorder:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityFirstOrderEntity operateActivityFirstOrder){
		operateActivityFirstOrderService.insert(operateActivityFirstOrder);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivityfirstorder/update")
    @RedisLock(value="operate:operateactivityfirstorder:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityFirstOrderEntity operateActivityFirstOrder){
		operateActivityFirstOrderService.updateById(operateActivityFirstOrder);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivityfirstorder/delete")
    @RedisLock(value="operate:operateactivityfirstorder:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operateActivityFirstOrderService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
