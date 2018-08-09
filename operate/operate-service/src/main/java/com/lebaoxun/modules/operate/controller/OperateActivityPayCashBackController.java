package com.lebaoxun.modules.operate.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateActivityPayCashBackEntity;
import com.lebaoxun.modules.operate.service.OperateActivityPayCashBackService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


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
    private OperateActivityPayCashBackService operateActivityPayCashBackService;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateactivitypaycashback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operateActivityPayCashBackService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivitypaycashback/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		OperateActivityPayCashBackEntity operateActivityPayCashBack = operateActivityPayCashBackService.selectById(id);
        return ResponseMessage.ok().put("operateActivityPayCashBack", operateActivityPayCashBack);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivitypaycashback/save")
    @RedisLock(value="operate:operateactivitypaycashback:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityPayCashBackEntity operateActivityPayCashBack){
		operateActivityPayCashBackService.insert(operateActivityPayCashBack);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivitypaycashback/update")
    @RedisLock(value="operate:operateactivitypaycashback:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateActivityPayCashBackEntity operateActivityPayCashBack){
		operateActivityPayCashBackService.updateById(operateActivityPayCashBack);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivitypaycashback/delete")
    @RedisLock(value="operate:operateactivitypaycashback:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operateActivityPayCashBackService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
