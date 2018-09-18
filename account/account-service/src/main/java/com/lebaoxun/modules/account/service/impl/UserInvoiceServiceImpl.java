package com.lebaoxun.modules.account.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.modules.account.dao.UserInvoiceDao;
import com.lebaoxun.modules.account.entity.UserInvoiceEntity;
import com.lebaoxun.modules.account.service.UserInvoiceService;


@Service("userInvoiceService")
public class UserInvoiceServiceImpl extends ServiceImpl<UserInvoiceDao, UserInvoiceEntity> implements UserInvoiceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserInvoiceEntity> page = this.selectPage(
                new Query<UserInvoiceEntity>(params).getPage(),
                new EntityWrapper<UserInvoiceEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<UserInvoiceEntity> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return this.baseMapper.findByUserId(userId);
	}

}
