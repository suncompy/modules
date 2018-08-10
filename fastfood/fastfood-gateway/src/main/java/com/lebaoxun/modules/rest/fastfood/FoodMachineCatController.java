package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineCatEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineCatService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 取餐机分类
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@RestController
public class FoodMachineCatController {
    @Autowired
    private IFoodMachineCatService foodMachineCatService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachinecat/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMachineCatService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachinecat/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMachineCatService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachinecat/save")
    ResponseMessage save(@RequestBody FoodMachineCatEntity foodMachineCat){
        return foodMachineCatService.save(oauth2SecuritySubject.getCurrentUser(),foodMachineCat);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachinecat/update")
    ResponseMessage update(@RequestBody FoodMachineCatEntity foodMachineCat){
        return foodMachineCatService.update(oauth2SecuritySubject.getCurrentUser(),foodMachineCat);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachinecat/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMachineCatService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
