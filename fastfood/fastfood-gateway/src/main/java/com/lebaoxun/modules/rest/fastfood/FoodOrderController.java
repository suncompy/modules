package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodOrderEntity;
import com.lebaoxun.modules.fastfood.service.IFoodOrderService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 订单表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodOrderController {
    @Autowired
    private IFoodOrderService foodOrderService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodorder/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodOrderService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorder/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
        return foodOrderService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorder/save")
    ResponseMessage save(@RequestBody FoodOrderEntity foodOrder){
        return foodOrderService.save(oauth2SecuritySubject.getCurrentUser(),foodOrder);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorder/update")
    ResponseMessage update(@RequestBody FoodOrderEntity foodOrder){
        return foodOrderService.update(oauth2SecuritySubject.getCurrentUser(),foodOrder);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorder/delete")
    ResponseMessage delete(@RequestBody Long[] ids){
        return foodOrderService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
