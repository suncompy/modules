package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeGetLogEntity;
import com.lebaoxun.modules.fastfood.service.OperatePrizeGetLogService;
import com.lebaoxun.soa.amqp.core.sender.IRabbitmqSender;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 抽奖记录表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
@RestController
public class OperatePrizeGetLogController {
    @Autowired
    private OperatePrizeGetLogService operatePrizeGetLogService;
    
    @Resource
	private IRabbitmqSender rabbitmqSender;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateprizegetlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operatePrizeGetLogService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateprizegetlog/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		OperatePrizeGetLogEntity operatePrizeGetLog = operatePrizeGetLogService.selectById(id);
        return ResponseMessage.ok().put("operatePrizeGetLog", operatePrizeGetLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateprizegetlog/save")
    @RedisLock(value="operate:operateprizegetlog:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperatePrizeGetLogEntity operatePrizeGetLog){
		operatePrizeGetLogService.insert(operatePrizeGetLog);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateprizegetlog/update")
    @RedisLock(value="operate:operateprizegetlog:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperatePrizeGetLogEntity operatePrizeGetLog){
		operatePrizeGetLogService.updateById(operatePrizeGetLog);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateprizegetlog/delete")
    @RedisLock(value="operate:operateprizegetlog:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operatePrizeGetLogService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
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
    		@RequestParam(value="offset",required=false) Integer offset){
    	return ResponseMessage.ok(operatePrizeGetLogService.findLogByUserId(userId, status, size, offset));
    }
    
    /**
     * 抽奖接口
     * @param userId
     * @param group
     * @return
     */
    @RequestMapping("/operate/operateprizegetlog/draw")
    @RedisLock(value="operate:operateprizegetlog:draw:lock:#arg0")
    ResponseMessage draw(@RequestParam("userId") Long userId, 
    		@RequestParam("group") String group){
    	ResponseMessage rm = operatePrizeGetLogService.draw(userId, group);
    	if("0".equals(rm.getErrcode())){
    		OperatePrizeGetLogEntity log = (OperatePrizeGetLogEntity) rm.getData();
    		if(log != null && log.getAisle() != null){
    			Map<String,String> message = new HashMap<String,String>();
    			message.put("userId", userId+"");
    			message.put("prizeLogId", log.getId()+"");
    			rabbitmqSender.sendContractDirect("order.prize.exchange.queue",
	    				new Gson().toJson(message));
    		}
    	}
    	return rm;
    }

}
