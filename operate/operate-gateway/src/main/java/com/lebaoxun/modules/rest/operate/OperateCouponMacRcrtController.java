package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateCouponMacRcrtEntity;
import com.lebaoxun.modules.operate.service.IOperateCouponMacRcrtService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 取餐机关联表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
 */
@RestController
public class OperateCouponMacRcrtController {
    @Autowired
    private IOperateCouponMacRcrtService operateCouponMacRcrtService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operatecouponmacrcrt/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operateCouponMacRcrtService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecouponmacrcrt/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operateCouponMacRcrtService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecouponmacrcrt/save")
    ResponseMessage save(@RequestBody OperateCouponMacRcrtEntity operateCouponMacRcrt){
        return operateCouponMacRcrtService.save(oauth2SecuritySubject.getCurrentUser(),operateCouponMacRcrt);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecouponmacrcrt/update")
    ResponseMessage update(@RequestBody OperateCouponMacRcrtEntity operateCouponMacRcrt){
        return operateCouponMacRcrtService.update(oauth2SecuritySubject.getCurrentUser(),operateCouponMacRcrt);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecouponmacrcrt/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operateCouponMacRcrtService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
