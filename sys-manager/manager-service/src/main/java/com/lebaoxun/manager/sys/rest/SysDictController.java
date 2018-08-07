/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lebaoxun.manager.sys.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.ValidatorUtils;
import com.lebaoxun.manager.sys.entity.SysDictEntity;
import com.lebaoxun.manager.sys.service.SysDictService;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.1.0 2018-01-27
 */
@RestController
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/sys/dict/list")
    public ResponseMessage list(@RequestParam Map<String, Object> params){
    	
        PageUtils page = sysDictService.queryPage(params);
        return ResponseMessage.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/sys/dict/info/{id}")
    public ResponseMessage info(@PathVariable("id") Long id){
        SysDictEntity dict = sysDictService.selectById(id);
        Map<String,Object> dataModel = new HashMap<String,Object>();
		dataModel.put("dict", dict);
        return ResponseMessage.ok(dataModel);
    }

    /**
     * 保存
     */
    @RequestMapping("/sys/dict/save")
    public ResponseMessage save(@RequestBody SysDictEntity dict){
        //校验类型
        ValidatorUtils.validateEntity(dict);

        sysDictService.insert(dict);

        return ResponseMessage.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/sys/dict/update")
    public ResponseMessage update(@RequestBody SysDictEntity dict){
        //校验类型
        ValidatorUtils.validateEntity(dict);

        sysDictService.updateById(dict);

        return ResponseMessage.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/sys/dict/delete")
    public ResponseMessage delete(@RequestBody Long[] ids){
        sysDictService.deleteBatchIds(Arrays.asList(ids));

        return ResponseMessage.ok();
    }

}
