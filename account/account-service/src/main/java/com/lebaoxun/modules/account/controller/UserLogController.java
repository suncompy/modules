package com.lebaoxun.modules.account.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.account.entity.UserLogEntity;
import com.lebaoxun.modules.account.service.UserLogService;



/**
 * 
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-06-19 20:01:34
 */
@RestController
public class UserLogController {
    @Autowired
    private UserLogService userLogService;

    /**
     * 列表
     */
    @RequestMapping("/account/userlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = userLogService.queryPage(params);
        return ResponseMessage.ok(page);
    }
    
    @RequestMapping("/account/userlog/allLogType")
    ResponseMessage allLogType(){
    	return ResponseMessage.ok(userLogService.queryAllLogType());
    }
    
    /**
     * 查询账户交易记录
     * @param userId
     * @param size
     * @param offset
     * @return
     */
    @RequestMapping("/account/userlog/findAccountLogByUserId")
    ResponseMessage findAccountLogByUserId(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="flag",required=false) String flag,
    		@RequestParam(value="logType",required=false) String logType,
    		@RequestParam(value="size",required=false) Integer size, 
    		@RequestParam(value="offset",required=false) Integer offset){
    	return ResponseMessage.ok(userLogService.queryAccountLogByUserId(userId, flag, logType, size, offset));
    }

    /**
     * 信息
     */
    @RequestMapping("/account/userlog/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		UserLogEntity userLog = userLogService.selectById(id);
        return ResponseMessage.ok().put("userLog", userLog);
    }
    
    @RequestMapping("/account/userlog/zRange")
    ResponseMessage zRange(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="logType") String logType,
    		@RequestParam(value="time") String time){
    	userLogService.zRange(userId, logType, time);
    	return ResponseMessage.ok();
    }
    
    @RequestMapping("/account/userlog/zRank")
    ResponseMessage zRank(@RequestParam(value="userId") Long userId,
    		@RequestParam(value="logType") String logType,
    		@RequestParam(value="time") String time){
    	return ResponseMessage.ok(userLogService.zRank(userId, logType, time));
    }

    @RequestMapping("/account/userlog/countLogByUser")
    Integer countLogByUser(@RequestParam(value="userId") Long userId, 
    		@RequestParam(value="logType") String logType,
    		@RequestParam(value="adjunctInfo",required=false) String adjunctInfo,
    		@RequestParam(value="month",required=false) String month){
    	return userLogService.countLogByUser(userId, month, logType, adjunctInfo);
    }
	
    @RequestMapping("/account/userlog/sumScoreLogByUser")
    Integer sumScoreLogByUser(@RequestParam(value="userId") Long userId, 
    		@RequestParam(value="logType") String logType,
    		@RequestParam(value="adjunctInfo",required=false) String adjunctInfo,
    		@RequestParam(value="month",required=false) String month){
    	return userLogService.sumScoreLogByUser(userId, month, logType, adjunctInfo);
	}
}
