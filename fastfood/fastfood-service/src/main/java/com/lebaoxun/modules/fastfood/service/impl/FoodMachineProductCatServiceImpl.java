package com.lebaoxun.modules.fastfood.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.DateUtil;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.fastfood.dao.FoodMachineProductCatDao;
import com.lebaoxun.modules.fastfood.entity.FoodMachineProductCatEntity;
import com.lebaoxun.modules.fastfood.service.FoodMachineProductCatService;


@Service("foodMachineProductCatService")
public class FoodMachineProductCatServiceImpl extends ServiceImpl<FoodMachineProductCatDao, FoodMachineProductCatEntity> implements FoodMachineProductCatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FoodMachineProductCatEntity> page = this.selectPage(
                new Query<FoodMachineProductCatEntity>(params).getPage(),
                new EntityWrapper<FoodMachineProductCatEntity>()
                .orderBy("create_time", false)
        );
        List<FoodMachineProductCatEntity> records = page.getRecords();
        if (records != null) {
            for (FoodMachineProductCatEntity fpc : records) {
                fpc.setCreateTimeStr(DateUtil.formatDatetime(fpc.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                fpc.setUpdateTimeStr(DateUtil.formatDatetime(fpc.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        return new PageUtils(page);
    }

	@Override
	public List<FoodMachineProductCatEntity> findCatByMacId(Integer macId) {
		// TODO Auto-generated method stub
		return this.baseMapper.findCatByMacId(macId);
	}
}
