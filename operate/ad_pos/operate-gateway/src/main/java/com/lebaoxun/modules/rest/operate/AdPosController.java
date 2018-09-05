package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.AdPosEntity;
import com.lebaoxun.modules.operate.service.IAdPosService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 广告位
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-09-05 14:15:42
 */
@RestController
public class AdPosController {
    @Autowired
    private IAdPosService adPosService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/adpos/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return adPosService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/adpos/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return adPosService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/adpos/save")
    ResponseMessage save(@RequestBody AdPosEntity adPos){
        return adPosService.save(oauth2SecuritySubject.getCurrentUser(),adPos);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/adpos/update")
    ResponseMessage update(@RequestBody AdPosEntity adPos){
        return adPosService.update(oauth2SecuritySubject.getCurrentUser(),adPos);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/adpos/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return adPosService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
