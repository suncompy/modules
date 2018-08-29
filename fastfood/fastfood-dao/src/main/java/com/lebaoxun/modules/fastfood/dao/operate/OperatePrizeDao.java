package com.lebaoxun.modules.fastfood.dao.operate;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeEntity;

/**
 * 抽奖奖品配置
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-29 15:11:29
 */
@Mapper
public interface OperatePrizeDao extends BaseMapper<OperatePrizeEntity> {
	
	List<OperatePrizeEntity> findPrizeByGroup(@Param("group")String group);
}
