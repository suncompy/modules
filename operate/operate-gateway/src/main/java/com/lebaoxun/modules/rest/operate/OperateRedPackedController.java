package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateRedPackedEntity;
import com.lebaoxun.modules.operate.service.IOperateRedPackedService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 现金红包
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:29
 */
@RestController
public class OperateRedPackedController {
    @Autowired
    private IOperateRedPackedService operateRedPackedService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateredpacked/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operateRedPackedService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateredpacked/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operateRedPackedService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateredpacked/save")
    ResponseMessage save(@RequestBody OperateRedPackedEntity operateRedPacked){
        return operateRedPackedService.save(oauth2SecuritySubject.getCurrentUser(),operateRedPacked);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateredpacked/update")
    ResponseMessage update(@RequestBody OperateRedPackedEntity operateRedPacked){
        return operateRedPackedService.update(oauth2SecuritySubject.getCurrentUser(),operateRedPacked);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateredpacked/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operateRedPackedService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
