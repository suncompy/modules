package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAdvanceTimeEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineAdvanceTimeService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 取餐机预定时间配置
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineAdvanceTimeController {
    @Autowired
    private IFoodMachineAdvanceTimeService foodMachineAdvanceTimeService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMachineAdvanceTimeService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMachineAdvanceTimeService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/save")
    ResponseMessage save(@RequestBody FoodMachineAdvanceTimeEntity foodMachineAdvanceTime){
        return foodMachineAdvanceTimeService.save(oauth2SecuritySubject.getCurrentUser(),foodMachineAdvanceTime);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/update")
    ResponseMessage update(@RequestBody FoodMachineAdvanceTimeEntity foodMachineAdvanceTime){
        return foodMachineAdvanceTimeService.update(oauth2SecuritySubject.getCurrentUser(),foodMachineAdvanceTime);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineadvancetime/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMachineAdvanceTimeService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
