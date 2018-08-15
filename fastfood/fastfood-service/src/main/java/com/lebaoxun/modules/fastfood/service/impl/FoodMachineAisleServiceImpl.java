package com.lebaoxun.modules.fastfood.service.impl;

import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;

import com.lebaoxun.modules.fastfood.dao.FoodMachineAisleDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("foodMachineAisleService")
public class FoodMachineAisleServiceImpl extends ServiceImpl<FoodMachineAisleDao, FoodMachineAisleEntity> implements FoodMachineAisleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineAisleEntity> page = this.selectPage(
                new Query<FoodMachineAisleEntity>(params).getPage(),
                new EntityWrapper<FoodMachineAisleEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void refProductAndType(Long adminId, FoodMachineAisleEntity foodMachineAisle){
        String _ids=foodMachineAisle.getIds();
        if (StringUtils.isEmpty(_ids))
            throw new I18nMessageException("100002", "机器渠道关联ID不能为空!");
        String[] _arrayIds=_ids.split(",");
        //遍历批量集，更新产品、分类id到关联表中
        for (String id:_arrayIds){
            FoodMachineAisleEntity _aisleObj=new FoodMachineAisleEntity();
            _aisleObj.setId(Integer.parseInt(id));
            _aisleObj.setUpdateBy(adminId);
            _aisleObj.setUpdateTime(new Date());
            _aisleObj.setProductId(foodMachineAisle.getProductId());
            _aisleObj.setProductCatId(foodMachineAisle.getProductCatId());
            updateById(_aisleObj);
        }
    }

    @Override
    public List<FoodMachineRefAisleEntity> findMachineAisleListByMacId(Integer macId) {
        return this.baseMapper.findMachineAisleListByMacId(macId);
    }
}
