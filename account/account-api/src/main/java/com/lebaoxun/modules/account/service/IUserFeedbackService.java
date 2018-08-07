package com.lebaoxun.modules.account.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lebaoxun.modules.account.entity.UserFeedbackEntity;
import com.lebaoxun.modules.account.service.hystrix.UserFeedbackServiceHystrix;
import com.lebaoxun.commons.exception.ResponseMessage;

import java.util.Map;

/**
 * 用户反馈表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-07 15:31:12
 */

@FeignClient(value="account-service",fallback=UserFeedbackServiceHystrix.class)
public interface IUserFeedbackService {
	/**
     * 列表
     */
    @RequestMapping("/account/userfeedback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params);


    /**
     * 信息
     */
    @RequestMapping("/account/userfeedback/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id);

    /**
     * 保存
     */
    @RequestMapping("/account/userfeedback/save")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserFeedbackEntity userFeedback);

    /**
     * 修改
     */
    @RequestMapping("/account/userfeedback/update")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody UserFeedbackEntity userFeedback);

    /**
     * 删除
     */
    @RequestMapping("/account/userfeedback/delete")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids);
    
}

