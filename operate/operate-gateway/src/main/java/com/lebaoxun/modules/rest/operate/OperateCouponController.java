package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateCouponEntity;
import com.lebaoxun.modules.operate.service.IOperateCouponService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 优惠券
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:33
 */
@RestController
public class OperateCouponController {
    @Autowired
    private IOperateCouponService operateCouponService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operatecoupon/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operateCouponService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecoupon/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operateCouponService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecoupon/save")
    ResponseMessage save(@RequestBody OperateCouponEntity operateCoupon){
        return operateCouponService.save(oauth2SecuritySubject.getCurrentUser(),operateCoupon);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecoupon/update")
    ResponseMessage update(@RequestBody OperateCouponEntity operateCoupon){
        return operateCouponService.update(oauth2SecuritySubject.getCurrentUser(),operateCoupon);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecoupon/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operateCouponService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
