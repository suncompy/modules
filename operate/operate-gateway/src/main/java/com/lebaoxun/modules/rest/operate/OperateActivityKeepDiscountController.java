package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateActivityKeepDiscountEntity;
import com.lebaoxun.modules.operate.service.IOperateActivityKeepDiscountService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 连续折扣
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:36:29
 */
@RestController
public class OperateActivityKeepDiscountController {
    @Autowired
    private IOperateActivityKeepDiscountService operateActivityKeepDiscountService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operateActivityKeepDiscountService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operateActivityKeepDiscountService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/save")
    ResponseMessage save(@RequestBody OperateActivityKeepDiscountEntity operateActivityKeepDiscount){
        return operateActivityKeepDiscountService.save(oauth2SecuritySubject.getCurrentUser(),operateActivityKeepDiscount);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/update")
    ResponseMessage update(@RequestBody OperateActivityKeepDiscountEntity operateActivityKeepDiscount){
        return operateActivityKeepDiscountService.update(oauth2SecuritySubject.getCurrentUser(),operateActivityKeepDiscount);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operateactivitykeepdiscount/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operateActivityKeepDiscountService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
