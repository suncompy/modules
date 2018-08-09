package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateActivityFirstOrderEntity;
import com.lebaoxun.modules.operate.service.IOperateActivityFirstOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 首单活动表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */
@RestController
public class OperateActivityFirstOrderController {
    @Autowired
    private IOperateActivityFirstOrderService operateActivityFirstOrderService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateactivityfirstorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operateActivityFirstOrderService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivityfirstorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operateActivityFirstOrderService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivityfirstorder/save")
    ResponseMessage save(@RequestBody OperateActivityFirstOrderEntity operateActivityFirstOrder){
        return operateActivityFirstOrderService.save(oauth2SecuritySubject.getCurrentUser(),operateActivityFirstOrder);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivityfirstorder/update")
    ResponseMessage update(@RequestBody OperateActivityFirstOrderEntity operateActivityFirstOrder){
        return operateActivityFirstOrderService.update(oauth2SecuritySubject.getCurrentUser(),operateActivityFirstOrder);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivityfirstorder/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operateActivityFirstOrderService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
