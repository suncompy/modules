package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodProductCatEntity;
import com.lebaoxun.modules.fastfood.service.IFoodProductCatService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 餐品分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodProductCatController {
    @Autowired
    private IFoodProductCatService foodProductCatService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodproductcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodProductCatService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodproductcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodProductCatService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodproductcat/save")
    ResponseMessage save(@RequestBody FoodProductCatEntity foodProductCat){
        return foodProductCatService.save(oauth2SecuritySubject.getCurrentUser(),foodProductCat);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodproductcat/update")
    ResponseMessage update(@RequestBody FoodProductCatEntity foodProductCat){
        return foodProductCatService.update(oauth2SecuritySubject.getCurrentUser(),foodProductCat);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodproductcat/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodProductCatService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
