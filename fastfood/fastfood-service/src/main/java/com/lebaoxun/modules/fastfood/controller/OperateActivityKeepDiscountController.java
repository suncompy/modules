package com.lebaoxun.modules.fastfood.controller;

import java.util.Arrays;
import java.util.List;
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
import com.lebaoxun.modules.fastfood.entity.operate.OperateActivityKeepDiscountEntity;
import com.lebaoxun.modules.fastfood.service.OperateActivityKeepDiscountService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 连续折扣
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:29
 */
@RestController
public class OperateActivityKeepDiscountController {
    @Autowired
    private OperateActivityKeepDiscountService operateActivityKeepDiscountService;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operateActivityKeepDiscountService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        List<OperateActivityKeepDiscountEntity> operateActivityKeepDiscountEntities=operateActivityKeepDiscountService.selectList(new EntityWrapper<OperateActivityKeepDiscountEntity>());
        OperateActivityKeepDiscountEntity operateActivityKeepDiscountEntity=null;
        if (operateActivityKeepDiscountEntities==null||operateActivityKeepDiscountEntities.size()==0)
            operateActivityKeepDiscountEntity=new OperateActivityKeepDiscountEntity();
        else
            operateActivityKeepDiscountEntity=operateActivityKeepDiscountEntities.get(0);
        return ResponseMessage.ok().put("operateActivityKeepDiscount", operateActivityKeepDiscountEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/save")
    @RedisLock(value="operate:operateactivitykeepdiscount:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityKeepDiscountEntity operateActivityKeepDiscount){
		operateActivityKeepDiscountService.insert(operateActivityKeepDiscount);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/update")
    @RedisLock(value="operate:operateactivitykeepdiscount:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityKeepDiscountEntity operateActivityKeepDiscount){
		operateActivityKeepDiscountService.updateById(operateActivityKeepDiscount);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/delete")
    @RedisLock(value="operate:operateactivitykeepdiscount:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operateActivityKeepDiscountService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

    @RequestMapping("/operate/operateactivitykeepdiscount/findUnderwayActivity")
    OperateActivityKeepDiscountEntity findUnderwayActivity(){
    	return operateActivityKeepDiscountService.findUnderwayActivity();
    }
}
