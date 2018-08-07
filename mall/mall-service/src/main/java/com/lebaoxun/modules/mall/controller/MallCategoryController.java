package com.lebaoxun.modules.mall.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.mall.entity.MallCategoryEntity;
import com.lebaoxun.modules.mall.service.MallCategoryService;
import com.lebaoxun.soa.core.redis.lock.RedisLock;


/**
 * 分类表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-07-12 19:57:12
 */
@RestController
public class MallCategoryController {
    @Autowired
    private MallCategoryService mallCategoryService;
    
    @RequestMapping("/mall/mallcategory/release")
    List<MallCategoryEntity> release(){
    	return mallCategoryService.queryAllShowList();
    }

    /**
     * 列表
     */
    @RequestMapping("/mall/mallcategory/list")
    List<MallCategoryEntity> list(){
    	List<MallCategoryEntity> list = mallCategoryService.selectList(null);
    	for(MallCategoryEntity mallCategory : list){
    		MallCategoryEntity parentMallCategory = mallCategoryService.selectById(mallCategory.getParentId());
			if(parentMallCategory != null){
				mallCategory.setParentName(parentMallCategory.getName());
			}
		}

		return list;
    }
    
    @RequestMapping("/mall/mallcategory/select")
    ResponseMessage select(){
		return ResponseMessage.ok(mallCategoryService.queryAllList());
    }

    /**
     * 信息
     */
    @RequestMapping("/mall/mallcategory/info/{id}")
    ResponseMessage info(@PathVariable("id") Long id){
		MallCategoryEntity mallCategory = mallCategoryService.selectById(id);
        return ResponseMessage.ok().put("mallCategory", mallCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/mall/mallcategory/save")
    @RedisLock(value="mall:mallcategory:save:lock:#arg0")
    ResponseMessage save(@RequestParam("adminId")Long adminId,@RequestBody MallCategoryEntity mallCategory){
		mallCategoryService.insert(mallCategory);
        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/mall/mallcategory/update")
    @RedisLock(value="mall:mallcategory:update:lock:#arg0")
    ResponseMessage update(@RequestParam("adminId")Long adminId,@RequestBody MallCategoryEntity mallCategory){
		mallCategoryService.updateById(mallCategory);
        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/mall/mallcategory/delete")
    @RedisLock(value="mall:mallcategory:delete:lock:#arg0")
    ResponseMessage delete(@RequestParam("adminId")Long adminId,@RequestBody Long[] ids){
		mallCategoryService.deleteBatchIds(Arrays.asList(ids));
        return ResponseMessage.ok();
    }

}
