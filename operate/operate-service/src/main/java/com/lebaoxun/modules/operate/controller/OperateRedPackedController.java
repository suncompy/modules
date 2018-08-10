package com.lebaoxun.modules.operate.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateRedPackedEntity;
import com.lebaoxun.modules.operate.service.OperateRedPackedService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


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
    private OperateRedPackedService operateRedPackedService;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateredpacked/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        PageUtils page = operateRedPackedService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateredpacked/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
		OperateRedPackedEntity operateRedPacked = operateRedPackedService.selectById(id);
        return ResponseMessage.ok().put("operateRedPacked", operateRedPacked);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateredpacked/save")
    @RedisLock(value="operate:operateredpacked:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody OperateRedPackedEntity operateRedPacked){
		operateRedPackedService.insert(operateRedPacked);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateredpacked/update")
    @RedisLock(value="operate:operateredpacked:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody OperateRedPackedEntity operateRedPacked){
		operateRedPackedService.updateById(operateRedPacked);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateredpacked/delete")
    @RedisLock(value="operate:operateredpacked:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Integer[] ids){
		operateRedPackedService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
