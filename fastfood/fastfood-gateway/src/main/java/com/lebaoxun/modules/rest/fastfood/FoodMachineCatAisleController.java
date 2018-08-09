package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCatAisleEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineCatAisleService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 取餐机分类通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:11
 */
@RestController
public class FoodMachineCatAisleController {
    @Autowired
    private IFoodMachineCatAisleService foodMachineCatAisleService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinecataisle/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMachineCatAisleService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinecataisle/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMachineCatAisleService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecataisle/save")
    ResponseMessage save(@RequestBody FoodMachineCatAisleEntity foodMachineCatAisle){
        return foodMachineCatAisleService.save(oauth2SecuritySubject.getCurrentUser(),foodMachineCatAisle);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinecataisle/update")
    ResponseMessage update(@RequestBody FoodMachineCatAisleEntity foodMachineCatAisle){
        return foodMachineCatAisleService.update(oauth2SecuritySubject.getCurrentUser(),foodMachineCatAisle);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinecataisle/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMachineCatAisleService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
