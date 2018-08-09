package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodMachineEntity;
import com.lebaoxun.modules.fastfood.service.IFoodMachineService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 取餐机
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:11
 */
@RestController
public class FoodMachineController {
    @Autowired
    private IFoodMachineService foodMachineService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodmachine/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodMachineService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodmachine/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodMachineService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodmachine/save")
    ResponseMessage save(@RequestBody FoodMachineEntity foodMachine){
        return foodMachineService.save(oauth2SecuritySubject.getCurrentUser(),foodMachine);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodmachine/update")
    ResponseMessage update(@RequestBody FoodMachineEntity foodMachine){
        return foodMachineService.update(oauth2SecuritySubject.getCurrentUser(),foodMachine);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodmachine/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodMachineService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
