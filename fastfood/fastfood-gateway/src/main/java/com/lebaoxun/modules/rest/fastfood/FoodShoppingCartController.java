package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodShoppingCartEntity;
import com.lebaoxun.modules.fastfood.service.IFoodShoppingCartService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 购物车表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodShoppingCartController {
    @Autowired
    private IFoodShoppingCartService foodShoppingCartService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodshoppingcart/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodShoppingCartService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodshoppingcart/info/{cartId}")
    ResponseMessage info(@PathVariable("cartId") Long cartId){
        return foodShoppingCartService.info(cartId);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodshoppingcart/save")
    ResponseMessage save(@RequestBody FoodShoppingCartEntity foodShoppingCart){
        return foodShoppingCartService.save(oauth2SecuritySubject.getCurrentUser(),foodShoppingCart);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodshoppingcart/update")
    ResponseMessage update(@RequestBody FoodShoppingCartEntity foodShoppingCart){
        return foodShoppingCartService.update(oauth2SecuritySubject.getCurrentUser(),foodShoppingCart);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodshoppingcart/delete")
    ResponseMessage delete(@RequestBody Long[] cartIds){
        return foodShoppingCartService.delete(oauth2SecuritySubject.getCurrentUser(),cartIds);
    }

}
