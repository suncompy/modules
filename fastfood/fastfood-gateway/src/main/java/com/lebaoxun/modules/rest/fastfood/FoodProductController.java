package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodProductEntity;
import com.lebaoxun.modules.fastfood.service.IFoodProductService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 餐品表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodProductController {
    @Autowired
    private IFoodProductService foodProductService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodproduct/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodProductService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodproduct/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodProductService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodproduct/save")
    ResponseMessage save(@RequestBody FoodProductEntity foodProduct){
        return foodProductService.save(oauth2SecuritySubject.getCurrentUser(),foodProduct);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodproduct/update")
    ResponseMessage update(@RequestBody FoodProductEntity foodProduct){
        return foodProductService.update(oauth2SecuritySubject.getCurrentUser(),foodProduct);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodproduct/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodProductService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
