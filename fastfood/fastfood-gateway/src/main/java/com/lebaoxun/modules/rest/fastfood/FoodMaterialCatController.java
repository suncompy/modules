package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMaterialCatEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMaterialCatService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 餐品原料分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodMaterialCatController {
    @Autowired
    private IFoodMaterialCatService foodMaterialCatService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmaterialcat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMaterialCatService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmaterialcat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMaterialCatService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmaterialcat/save")
    ResponseMessage save(@RequestBody FoodMaterialCatEntity foodMaterialCat){
        return foodMaterialCatService.save(oauth2SecuritySubject.getCurrentUser(),foodMaterialCat);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmaterialcat/update")
    ResponseMessage update(@RequestBody FoodMaterialCatEntity foodMaterialCat){
        return foodMaterialCatService.update(oauth2SecuritySubject.getCurrentUser(),foodMaterialCat);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmaterialcat/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMaterialCatService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
