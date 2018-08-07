package com.lebaoxun.modules.account.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.account.entity.UserScoreAchievementAwardEntity;
import com.lebaoxun.modules.account.service.UserScoreAchievementAwardService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 用户任务表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
@RestController
public class UserScoreAchievementAwardController {
    @Autowired
    private UserScoreAchievementAwardService userScoreAchievementAwardService;

    /**
     * 列表
     */
    @RequestMapping("/account/userscoreachievementaward/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = userScoreAchievementAwardService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/account/userscoreachievementaward/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		UserScoreAchievementAwardEntity userScoreAchievementAward = userScoreAchievementAwardService.selectById(id);
        return ResponseMessage.ok().put("userScoreAchievementAward", userScoreAchievementAward);
    }

    /**
     * 保存
     */
    @RequestMapping("/account/userscoreachievementaward/save")
    @RedisLock(value="account:userscoreachievementaward:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserScoreAchievementAwardEntity userScoreAchievementAward){
		userScoreAchievementAwardService.insert(userScoreAchievementAward);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/account/userscoreachievementaward/update")
    @RedisLock(value="account:userscoreachievementaward:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody UserScoreAchievementAwardEntity userScoreAchievementAward){
		userScoreAchievementAwardService.updateById(userScoreAchievementAward);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/account/userscoreachievementaward/delete")
    @RedisLock(value="account:userscoreachievementaward:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		userScoreAchievementAwardService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
