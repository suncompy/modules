package com.lebaoxun.modules.operate.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;
import com.lebaoxun.modules.operate.service.OperateCouponRecordService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 优惠券领取记录
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:32
 */
@RestController
public class OperateCouponRecordController {
    @Autowired
    private OperateCouponRecordService operateCouponRecordService;

    /**
     * 列表
     */
    @RequestMapping("/operate/operatecouponrecord/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operateCouponRecordService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecouponrecord/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		OperateCouponRecordEntity operateCouponRecord = operateCouponRecordService.selectById(id);
        return ResponseMessage.ok().put("operateCouponRecord", operateCouponRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecouponrecord/save")
    @RedisLock(value="operate:operatecouponrecord:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponRecordEntity operateCouponRecord){
		operateCouponRecordService.insert(operateCouponRecord);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecouponrecord/update")
    @RedisLock(value="operate:operatecouponrecord:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponRecordEntity operateCouponRecord){
		operateCouponRecordService.updateById(operateCouponRecord);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecouponrecord/delete")
    @RedisLock(value="operate:operatecouponrecord:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operateCouponRecordService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    /**
	 * 查询用户未过期，未使用的优惠券
	 * @param userId
	 * @return
	 */
    @RequestMapping("/operate/operatecouponrecord/findByUserId")
    ResponseMessage findByUserId(@RequestParam("userId")Long userId,
    		@RequestParam(value="use",required=false)Integer use,
    		@RequestParam(value="flag",required=false)Integer flag,
    		@RequestParam(value="size",required=false)Integer size,
    		@RequestParam(value="offset",required=false)Integer offset){
    	return ResponseMessage.ok(operateCouponRecordService.findByUserId(userId, use, flag,size, offset));
	}
}
