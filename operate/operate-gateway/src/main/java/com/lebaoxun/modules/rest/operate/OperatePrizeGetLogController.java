package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperatePrizeGetLogEntity;
import com.lebaoxun.modules.operate.service.IOperatePrizeGetLogService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 抽奖记录表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
@RestController
public class OperatePrizeGetLogController {
    @Autowired
    private IOperatePrizeGetLogService operatePrizeGetLogService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateprizegetlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operatePrizeGetLogService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateprizegetlog/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operatePrizeGetLogService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateprizegetlog/save")
    ResponseMessage save(@RequestBody OperatePrizeGetLogEntity operatePrizeGetLog){
        return operatePrizeGetLogService.save(oauth2SecuritySubject.getCurrentUser(),operatePrizeGetLog);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateprizegetlog/update")
    ResponseMessage update(@RequestBody OperatePrizeGetLogEntity operatePrizeGetLog){
        return operatePrizeGetLogService.update(oauth2SecuritySubject.getCurrentUser(),operatePrizeGetLog);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateprizegetlog/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operatePrizeGetLogService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
