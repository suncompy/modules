package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineProductCatEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineProductCatService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 取餐机餐品分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodMachineProductCatController {
    @Autowired
    private IFoodMachineProductCatService foodMachineProductCatService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineproductcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMachineProductCatService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineproductcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMachineProductCatService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineproductcat/save")
    ResponseMessage save(@RequestBody FoodMachineProductCatEntity foodMachineProductCat){
        return foodMachineProductCatService.save(oauth2SecuritySubject.getCurrentUser(),foodMachineProductCat);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineproductcat/update")
    ResponseMessage update(@RequestBody FoodMachineProductCatEntity foodMachineProductCat){
        return foodMachineProductCatService.update(oauth2SecuritySubject.getCurrentUser(),foodMachineProductCat);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineproductcat/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMachineProductCatService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
