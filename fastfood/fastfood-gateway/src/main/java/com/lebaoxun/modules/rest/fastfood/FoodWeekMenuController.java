package com.lebaoxun.modules.rest.fastfood;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.fastfood.entity.FoodWeekMenuEntity;
import com.lebaoxun.modules.fastfood.service.IFoodWeekMenuService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 每周菜谱
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 15:30:01
 */
@RestController
public class FoodWeekMenuController {
    @Autowired
    private IFoodWeekMenuService foodWeekMenuService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/fastfood/foodweekmenu/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return foodWeekMenuService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/fastfood/foodweekmenu/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return foodWeekMenuService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/fastfood/foodweekmenu/save")
    ResponseMessage save(@RequestBody FoodWeekMenuEntity foodWeekMenu){
        return foodWeekMenuService.save(oauth2SecuritySubject.getCurrentUser(),foodWeekMenu);
    }

    /**
     * 修改
     */
    @RequestMapping("/fastfood/foodweekmenu/update")
    ResponseMessage update(@RequestBody FoodWeekMenuEntity foodWeekMenu){
        return foodWeekMenuService.update(oauth2SecuritySubject.getCurrentUser(),foodWeekMenu);
    }

    /**
     * 删除
     */
    @RequestMapping("/fastfood/foodweekmenu/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return foodWeekMenuService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
