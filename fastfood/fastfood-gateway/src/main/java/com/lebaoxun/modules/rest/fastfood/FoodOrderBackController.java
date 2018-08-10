package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodOrderBackEntity;
import com.lebaoxun.modules.fastfood.service.IFoodOrderBackService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 订单退款表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodOrderBackController {
    @Autowired
    private IFoodOrderBackService foodOrderBackService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodorderback/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodOrderBackService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodorderback/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
        return foodOrderBackService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodorderback/save")
    ResponseMessage save(@RequestBody FoodOrderBackEntity foodOrderBack){
        return foodOrderBackService.save(oauth2SecuritySubject.getCurrentUser(),foodOrderBack);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodorderback/update")
    ResponseMessage update(@RequestBody FoodOrderBackEntity foodOrderBack){
        return foodOrderBackService.update(oauth2SecuritySubject.getCurrentUser(),foodOrderBack);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodorderback/delete")
    ResponseMessage delete(@RequestBody Long[] ids){
        return foodOrderBackService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
