package com.lebaoxun.modules.rest.account;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.account.entity.UserLevelPrivilegeEntity;
import com.lebaoxun.modules.account.service.IUserLevelPrivilegeService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



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
    private IUserLevelPrivilegeService userLevelPrivilegeService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/account/userlevelprivilege/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return userLevelPrivilegeService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/account/userlevelprivilege/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return userLevelPrivilegeService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/account/userlevelprivilege/save")
    ResponseMessage save(@RequestBody UserLevelPrivilegeEntity userLevelPrivilege){
        return userLevelPrivilegeService.save(oauth2SecuritySubject.getCurrentUser(),userLevelPrivilege);
    }

    /**
     * 修改
     */
    @RequestMapping("/account/userlevelprivilege/update")
    ResponseMessage update(@RequestBody UserLevelPrivilegeEntity userLevelPrivilege){
        return userLevelPrivilegeService.update(oauth2SecuritySubject.getCurrentUser(),userLevelPrivilege);
    }

    /**
     * 删除
     */
    @RequestMapping("/account/userlevelprivilege/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return userLevelPrivilegeService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
