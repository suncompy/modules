package com.lebaoxun.modules.operate.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateCouponMacRcrtEntity;
import com.lebaoxun.modules.operate.service.OperateCouponMacRcrtService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


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
    private OperateCouponMacRcrtService operateCouponMacRcrtService;

    /**
     * 列表
     */
    @RequestMapping("/operate/operatecouponmacrcrt/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operateCouponMacRcrtService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecouponmacrcrt/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		OperateCouponMacRcrtEntity operateCouponMacRcrt = operateCouponMacRcrtService.selectById(id);
        return ResponseMessage.ok().put("operateCouponMacRcrt", operateCouponMacRcrt);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecouponmacrcrt/save")
    @RedisLock(value="operate:operatecouponmacrcrt:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponMacRcrtEntity operateCouponMacRcrt){
		operateCouponMacRcrtService.insert(operateCouponMacRcrt);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecouponmacrcrt/update")
    @RedisLock(value="operate:operatecouponmacrcrt:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateCouponMacRcrtEntity operateCouponMacRcrt){
		operateCouponMacRcrtService.updateById(operateCouponMacRcrt);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecouponmacrcrt/delete")
    @RedisLock(value="operate:operatecouponmacrcrt:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operateCouponMacRcrtService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
