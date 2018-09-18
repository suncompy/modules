package com.lebaoxun.modules.account.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.modules.account.entity.UserInvoiceEntity;
import com.lebaoxun.modules.account.service.UserInvoiceService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 发票信息
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-18 14:13:24
 */
@RestController
public class UserInvoiceController {
    @Autowired
    private UserInvoiceService userInvoiceService;

    /**
     * 列表
     */
    @RequestMapping("/account/userinvoice/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = userInvoiceService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/account/userinvoice/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		UserInvoiceEntity userInvoice = userInvoiceService.selectById(id);
        return ResponseMessage.ok().put("userInvoice", userInvoice);
    }

    /**
     * 保存
     */
    @RequestMapping("/account/userinvoice/save")
    @RedisLock(value="account:userinvoice:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody UserInvoiceEntity userInvoice){
    	userInvoice.setCreateTime(new Date());
		userInvoiceService.insert(userInvoice);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/account/userinvoice/update")
    @RedisLock(value="account:userinvoice:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody UserInvoiceEntity userInvoice){
		userInvoiceService.updateById(userInvoice);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/account/userinvoice/delete")
    @RedisLock(value="account:userinvoice:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		userInvoiceService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }
    
    /**
     * 删除
     */
    @RequestMapping("/account/userinvoice/deleteByUserId")
    @RedisLock(value="account:userinvoice:deleteByUserId:lock:#arg0")
    ResponseMessage deleteByUserId(@RequestParam("userId")Long userId,@RequestParam("id") Integer id){
		userInvoiceService.delete(new EntityWrapper<UserInvoiceEntity>().eq("user_id", userId).eq("id", id));
        return ResponseMessage.ok();
    }
    
    @RequestMapping("/account/userinvoice/findByUserId")
    ResponseMessage findByUserId(@RequestParam("userId")Long userId){
    	return ResponseMessage.ok(userInvoiceService.findByUserId(userId));
    }

}
