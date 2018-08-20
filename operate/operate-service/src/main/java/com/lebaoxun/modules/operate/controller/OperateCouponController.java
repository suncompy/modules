package com.lebaoxun.modules.operate.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateCouponEntity;
import com.lebaoxun.modules.operate.service.OperateCouponService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


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
    private OperateCouponService operateCouponService;

    /**
     * 列表
     */
    @RequestMapping("/operate/operatecoupon/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operateCouponService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecoupon/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		OperateCouponEntity operateCoupon = operateCouponService.selectById(id);
        return ResponseMessage.ok().put("operateCoupon", operateCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecoupon/save")
    @RedisLock(value="operate:operatecoupon:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponEntity operateCoupon){
        operateCoupon.setCreateTime(new Date());
        operateCoupon.setCreateBy(adminId);
        operateCoupon.setUpdateBy(adminId);
        operateCoupon.setUpdateTime(new Date());
		operateCouponService.insert(operateCoupon);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecoupon/update")
    @RedisLock(value="operate:operatecoupon:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponEntity operateCoupon){
        operateCoupon.setUpdateBy(adminId);
        operateCoupon.setUpdateTime(new Date());
		operateCouponService.updateById(operateCoupon);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecoupon/delete")
    @RedisLock(value="operate:operatecoupon:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operateCouponService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
    /**
     * 获取可领取优惠券列表
     * @param macId
     * @param userId
     * @return
     */
    @RequestMapping("/operate/operatecoupon/findByMacId")
    ResponseMessage findByMacId(@RequestParam("macId")Integer macId,
    		@RequestParam("userId")Long userId){
    	return ResponseMessage.ok(operateCouponService.findByMacId(macId, userId));
    }

}
