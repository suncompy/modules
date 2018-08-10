package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineRepairOrderEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineRepairOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 维修派单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineRepairOrderController {
    @Autowired
    private IFoodMachineRepairOrderService foodMachineRepairOrderService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMachineRepairOrderService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMachineRepairOrderService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/save")
    ResponseMessage save(@RequestBody FoodMachineRepairOrderEntity foodMachineRepairOrder){
        return foodMachineRepairOrderService.save(oauth2SecuritySubject.getCurrentUser(),foodMachineRepairOrder);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/update")
    ResponseMessage update(@RequestBody FoodMachineRepairOrderEntity foodMachineRepairOrder){
        return foodMachineRepairOrderService.update(oauth2SecuritySubject.getCurrentUser(),foodMachineRepairOrder);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinerepairorder/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMachineRepairOrderService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
