package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.OperatePrizeGetLogEntity;
import com.lebaoxun.modules.fastfood.service.IOperatePrizeGetLogService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 抽奖奖品配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-06 10:54:54
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
    @RequestMapping("/fastfood/operateprizegetlog/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operatePrizeGetLogService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/operateprizegetlog/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operatePrizeGetLogService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/operateprizegetlog/save")
    ResponseMessage save(@RequestBody OperatePrizeGetLogEntity operatePrizeGetLog){
        return operatePrizeGetLogService.save(oauth2SecuritySubject.getCurrentUser(),operatePrizeGetLog);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/operateprizegetlog/update")
    ResponseMessage update(@RequestBody OperatePrizeGetLogEntity operatePrizeGetLog){
        return operatePrizeGetLogService.update(oauth2SecuritySubject.getCurrentUser(),operatePrizeGetLog);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/operateprizegetlog/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operatePrizeGetLogService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
