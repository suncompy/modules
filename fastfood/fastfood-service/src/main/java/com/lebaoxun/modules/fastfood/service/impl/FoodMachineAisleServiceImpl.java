package com.lebaoxun.modules.fastfood.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMachineAisleDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineAisleService;


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
    public void refProductAndType(Long adminId, FoodMachineRefAisleEntity foodMachineAisle){
        List<String> _ids=foodMachineAisle.getAisleIds();
        if (_ids==null||_ids.size()==0)
            throw new I18nMessageException("100002", "机器渠道关联ID不能为空!");
        //遍历批量集，更新产品、分类id到关联表中
        for (String id:_ids){
            FoodMachineAisleEntity _aisleObj=new FoodMachineAisleEntity();
            _aisleObj.setId(Integer.parseInt(id));
            _aisleObj.setUpdateBy(adminId);
            _aisleObj.setUpdateTime(new Date());
            _aisleObj.setPrice(foodMachineAisle.getPrice());
            _aisleObj.setProductId(foodMachineAisle.getProductId());
            _aisleObj.setProductCatId(foodMachineAisle.getProductCatId());
            updateById(_aisleObj);
        }
    }

    @Override
    public List<FoodMachineRefAisleEntity> findMachineAisleListByMacId(Integer macId) {
        return this.baseMapper.findMachineAisleListByMacId(macId);
    }
    
    @Override
    public List<Map<String, Object>> findProductByMacIdAndProductCatId(
    		Integer macId, Integer productCatId) {
    	// TODO Auto-generated method stub
    	return this.baseMapper.findProductByMacIdAndProductCatId(macId, productCatId);
    }
}
