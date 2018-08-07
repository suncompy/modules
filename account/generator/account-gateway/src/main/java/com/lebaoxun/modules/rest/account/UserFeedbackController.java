package com.lebaoxun.modules.rest.account;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.account.entity.UserFeedbackEntity;
import com.lebaoxun.modules.account.service.IUserFeedbackService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



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
    private IUserFeedbackService userFeedbackService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/account/userfeedback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return userFeedbackService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/account/userfeedback/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return userFeedbackService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/account/userfeedback/save")
    ResponseMessage save(@RequestBody UserFeedbackEntity userFeedback){
        return userFeedbackService.save(oauth2SecuritySubject.getCurrentUser(),userFeedback);
    }

    /**
     * 修改
     */
    @RequestMapping("/account/userfeedback/update")
    ResponseMessage update(@RequestBody UserFeedbackEntity userFeedback){
        return userFeedbackService.update(oauth2SecuritySubject.getCurrentUser(),userFeedback);
    }

    /**
     * 删除
     */
    @RequestMapping("/account/userfeedback/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return userFeedbackService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
