package com.lebaoxun.modules.rest.operate;

import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.modules.operate.entity.OperateCouponRecordEntity;
import com.lebaoxun.modules.operate.service.IOperateCouponRecordService;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.security.oauth2.Oauth2SecuritySubject;



/**
 * 优惠券领取记录
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-09 16:01:11
 */
@RestController
public class OperateCouponRecordController {
    @Autowired
    private IOperateCouponRecordService operateCouponRecordService;
    
    @Resource
	private Oauth2SecuritySubject oauth2SecuritySubject;

    /**
     * 列表
     */
    @RequestMapping("/operate/operatecouponrecord/list")
    ResponseMessage list(@RequestParam Map<String, Object> params){
        return operateCouponRecordService.list(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/operate/operatecouponrecord/info/{id}")
    ResponseMessage info(@PathVariable("id") Integer id){
        return operateCouponRecordService.info(id);
    }

    /**
     * 保存
     */
    @RequestMapping("/operate/operatecouponrecord/save")
    ResponseMessage save(@RequestBody OperateCouponRecordEntity operateCouponRecord){
        return operateCouponRecordService.save(oauth2SecuritySubject.getCurrentUser(),operateCouponRecord);
    }

    /**
     * 修改
     */
    @RequestMapping("/operate/operatecouponrecord/update")
    ResponseMessage update(@RequestBody OperateCouponRecordEntity operateCouponRecord){
        return operateCouponRecordService.update(oauth2SecuritySubject.getCurrentUser(),operateCouponRecord);
    }

    /**
     * 删除
     */
    @RequestMapping("/operate/operatecouponrecord/delete")
    ResponseMessage delete(@RequestBody Integer[] ids){
        return operateCouponRecordService.delete(oauth2SecuritySubject.getCurrentUser(),ids);
    }

}
