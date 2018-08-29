package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperatePrizeEntity;
import com.lebaoxun.modules.operate.service.IOperatePrizeService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 抽奖奖品配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
@RestController
public class OperatePrizeController {
    @Autowired
    private IOperatePrizeService operatePrizeService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateprize/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operatePrizeService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateprize/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operatePrizeService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateprize/save")
    ResponseMessage save(@RequestBody OperatePrizeEntity operatePrize){
        return operatePrizeService.save(oauth2SecuritySubject.getCurrentUser(),operatePrize);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateprize/update")
    ResponseMessage update(@RequestBody OperatePrizeEntity operatePrize){
        return operatePrizeService.update(oauth2SecuritySubject.getCurrentUser(),operatePrize);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateprize/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operatePrizeService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
