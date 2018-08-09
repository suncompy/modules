package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAddStockOrderEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineAddStockOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 取餐机进货派单
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodMachineAddStockOrderController {
    @Autowired
    private IFoodMachineAddStockOrderService foodMachineAddStockOrderService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMachineAddStockOrderService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMachineAddStockOrderService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/save")
    ResponseMessage save(@RequestBody FoodMachineAddStockOrderEntity foodMachineAddStockOrder){
        return foodMachineAddStockOrderService.save(oauth2SecuritySubject.getCurrentUser(),foodMachineAddStockOrder);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/update")
    ResponseMessage update(@RequestBody FoodMachineAddStockOrderEntity foodMachineAddStockOrder){
        return foodMachineAddStockOrderService.update(oauth2SecuritySubject.getCurrentUser(),foodMachineAddStockOrder);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaddstockorder/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMachineAddStockOrderService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
