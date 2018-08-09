package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateActivityPayCashBackEntity;
import com.lebaoxun.modules.operate.service.IOperateActivityPayCashBackService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 充值返现表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */
@RestController
public class OperateActivityPayCashBackController {
    @Autowired
    private IOperateActivityPayCashBackService operateActivityPayCashBackService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateactivitypaycashback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operateActivityPayCashBackService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivitypaycashback/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operateActivityPayCashBackService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivitypaycashback/save")
    ResponseMessage save(@RequestBody OperateActivityPayCashBackEntity operateActivityPayCashBack){
        return operateActivityPayCashBackService.save(oauth2SecuritySubject.getCurrentUser(),operateActivityPayCashBack);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivitypaycashback/update")
    ResponseMessage update(@RequestBody OperateActivityPayCashBackEntity operateActivityPayCashBack){
        return operateActivityPayCashBackService.update(oauth2SecuritySubject.getCurrentUser(),operateActivityPayCashBack);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivitypaycashback/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operateActivityPayCashBackService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
