package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMaterialEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMaterialService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 餐品原料表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodMaterialController {
    @Autowired
    private IFoodMaterialService foodMaterialService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmaterial/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMaterialService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmaterial/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMaterialService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmaterial/save")
    ResponseMessage save(@RequestBody FoodMaterialEntity foodMaterial){
        return foodMaterialService.save(oauth2SecuritySubject.getCurrentUser(),foodMaterial);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmaterial/update")
    ResponseMessage update(@RequestBody FoodMaterialEntity foodMaterial){
        return foodMaterialService.update(oauth2SecuritySubject.getCurrentUser(),foodMaterial);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmaterial/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMaterialService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
