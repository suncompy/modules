package com.lebaoxun.modules.rest.account;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.account.entity.UserScoreAchievementAwardEntity;
import com.lebaoxun.modules.account.service.IUserScoreAchievementAwardService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



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
    private IUserScoreAchievementAwardService userScoreAchievementAwardService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/account/userscoreachievementaward/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return userScoreAchievementAwardService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/account/userscoreachievementaward/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return userScoreAchievementAwardService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/account/userscoreachievementaward/save")
    ResponseMessage save(@RequestBody UserScoreAchievementAwardEntity userScoreAchievementAward){
        return userScoreAchievementAwardService.save(oauth2SecuritySubject.getCurrentUser(),userScoreAchievementAward);
    }

    /**
     * 修改
     */
    @RequestMapping("/account/userscoreachievementaward/update")
    ResponseMessage update(@RequestBody UserScoreAchievementAwardEntity userScoreAchievementAward){
        return userScoreAchievementAwardService.update(oauth2SecuritySubject.getCurrentUser(),userScoreAchievementAward);
    }

    /**
     * 删除
     */
    @RequestMapping("/account/userscoreachievementaward/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return userScoreAchievementAwardService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
