package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineAisleService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 取餐机通道
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodMachineAisleController {
    @Autowired
    private IFoodMachineAisleService foodMachineAisleService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachineaisle/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMachineAisleService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachineaisle/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMachineAisleService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachineaisle/save")
    ResponseMessage save(@RequestBody FoodMachineAisleEntity foodMachineAisle){
        return foodMachineAisleService.save(oauth2SecuritySubject.getCurrentUser(),foodMachineAisle);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachineaisle/update")
    ResponseMessage update(@RequestBody FoodMachineAisleEntity foodMachineAisle){
        return foodMachineAisleService.update(oauth2SecuritySubject.getCurrentUser(),foodMachineAisle);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachineaisle/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMachineAisleService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
