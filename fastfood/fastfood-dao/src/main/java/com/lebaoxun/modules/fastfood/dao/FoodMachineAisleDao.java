package com.lebaoxun.modules.fastfood.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lebaoxun.modules.fastfood.entity.FoodMachineAisleEntity;
import com.lebaoxun.modules.fastfood.entity.FoodMachineRefAisleEntity;

/**
 * 取餐机通道
 * 
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-08-10 16:34:01
 */
@Mapper
public interface FoodMachineAisleDao extends BaseMapper<FoodMachineAisleEntity> {
	
	List<FoodMachineRefAisleEntity> findMachineAisleListByMacId(
			@Param("macId") Integer macId);

	List<Map<String, Object>> findProductByMacIdAndProductCatId(
			@Param("macId") Integer macId,
			@Param("productCatId") Integer productCatId);
	
	/**
	 * 每周菜谱关联 机器产品列表
	 * @param macId
	 * @param week
	 * @return
	 */
	List<Map<String, Object>> findProductByMacIdAndWeek(
			@Param("macId") Integer macId,
			@Param("week") Integer week);
	
	Map<String, Object> findProductByAisle(
			@Param("macId") Integer macId,
			@Param("productId") Integer productId,
			@Param("productCatId") Integer productCatId,
			@Param("aisleId") Integer aisleId);
}
