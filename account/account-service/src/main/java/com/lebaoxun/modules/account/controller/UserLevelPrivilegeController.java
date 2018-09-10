package com.lebaoxun.modules.account.controller;

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
import com.lebaoxun.modules.account.entity.UserLevelPrivilegeEntity;
import com.lebaoxun.modules.account.service.UserLevelPrivilegeService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 等级特权表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
@RestController
public class UserLevelPrivilegeController {
    @Autowired
    private UserLevelPrivilegeService userLevelPrivilegeService;

    /**
     * 列表
     */
    @RequestMapping("/account/userlevelprivilege/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = userLevelPrivilegeService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/account/userlevelprivilege/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		UserLevelPrivilegeEntity userLevelPrivilege = userLevelPrivilegeService.selectById(id);
        return ResponseMessage.ok().put("userLevelPrivilege", userLevelPrivilege);
    }

    /**
     * 保存
     */
    @RequestMapping("/account/userlevelprivilege/save")
    @RedisLock(value="account:userlevelprivilege:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserLevelPrivilegeEntity userLevelPrivilege){
    	userLevelPrivilege.setCreateBy(adminId);
    	userLevelPrivilege.setCreateTime(new Date());
		userLevelPrivilegeService.insert(userLevelPrivilege);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/account/userlevelprivilege/update")
    @RedisLock(value="account:userlevelprivilege:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody UserLevelPrivilegeEntity userLevelPrivilege){
		userLevelPrivilegeService.updateById(userLevelPrivilege);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/account/userlevelprivilege/delete")
    @RedisLock(value="account:userlevelprivilege:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		userLevelPrivilegeService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
    @RequestMapping("/account/userlevelprivilege/findLevelByUserId")
    UserLevelPrivilegeEntity findLevelByUserId(@RequestParam("userId")Long userId,
    		@RequestParam("payLogType")String payLogType){
    	return userLevelPrivilegeService.findLevelByUserId(userId, payLogType);
    }

}
