package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodOrderChildsEntity;
import com.lebaoxun.modules.fastfood.service.IFoodOrderChildsService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 订单明细表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodOrderChildsController {
    @Autowired
    private IFoodOrderChildsService foodOrderChildsService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodorderchilds/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodOrderChildsService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorderchilds/info/{orderProductId}")
    ResponseMessage info(@PathVariable("orderProductId") Long orderProductId){
        return foodOrderChildsService.info(orderProductId);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorderchilds/save")
    ResponseMessage save(@RequestBody FoodOrderChildsEntity foodOrderChilds){
        return foodOrderChildsService.save(oauth2SecuritySubject.getCurrentUser(),foodOrderChilds);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorderchilds/update")
    ResponseMessage update(@RequestBody FoodOrderChildsEntity foodOrderChilds){
        return foodOrderChildsService.update(oauth2SecuritySubject.getCurrentUser(),foodOrderChilds);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorderchilds/delete")
    ResponseMessage delete(@RequestBody Long[] orderProductIds){
        return foodOrderChildsService.delete(oauth2SecuritySubject.getCurrentUser(),orderProductIds);
    }

}
