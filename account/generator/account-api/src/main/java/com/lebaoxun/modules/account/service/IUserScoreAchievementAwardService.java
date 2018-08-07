package com.lebaoxun.modules.account.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.account.entity.UserScoreAchievementAwardEntity;
import com.lebaoxun.modules.account.service.hystrix.UserScoreAchievementAwardServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 用户任务表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */

@FeignClient(value="account-service",fallback=UserScoreAchievementAwardServiceHystrix.class)
public interface IUserScoreAchievementAwardService {
	/**
     * 列表
     */
    @RequestMapping("/account/userscoreachievementaward/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/account/userscoreachievementaward/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/account/userscoreachievementaward/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserScoreAchievementAwardEntity userScoreAchievementAward);

    /**
     * 修改
     */
    @RequestMapping("/account/userscoreachievementaward/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody UserScoreAchievementAwardEntity userScoreAchievementAward);

    /**
     * 删除
     */
    @RequestMapping("/account/userscoreachievementaward/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

