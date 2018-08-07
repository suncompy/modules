package com.lebaoxun.modules.account.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.account.entity.UserFeedbackEntity;
import com.lebaoxun.modules.account.service.UserFeedbackService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 用户反馈表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */
@RestController
public class UserFeedbackController {
    @Autowired
    private UserFeedbackService userFeedbackService;

    /**
     * 列表
     */
    @RequestMapping("/account/userfeedback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = userFeedbackService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/account/userfeedback/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		UserFeedbackEntity userFeedback = userFeedbackService.selectById(id);
        return ResponseMessage.ok().put("userFeedback", userFeedback);
    }

    /**
     * 保存
     */
    @RequestMapping("/account/userfeedback/save")
    @RedisLock(value="account:userfeedback:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserFeedbackEntity userFeedback){
		userFeedbackService.insert(userFeedback);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/account/userfeedback/update")
    @RedisLock(value="account:userfeedback:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody UserFeedbackEntity userFeedback){
		userFeedbackService.updateById(userFeedback);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/account/userfeedback/delete")
    @RedisLock(value="account:userfeedback:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		userFeedbackService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
