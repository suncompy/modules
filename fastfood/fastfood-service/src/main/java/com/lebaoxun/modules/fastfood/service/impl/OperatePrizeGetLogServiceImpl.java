package com.lebaoxun.modules.fastfood.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;
import com.lebaoxun.commons.utils.PageUtils;
import com.lebaoxun.commons.utils.Query;
import com.lebaoxun.commons.utils.StringUtils;
import com.lebaoxun.modules.account.service.IUserService;
import com.lebaoxun.modules.fastfood.dao.operate.OperatePrizeDao;
import com.lebaoxun.modules.fastfood.dao.operate.OperatePrizeGetLogDao;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeEntity;
import com.lebaoxun.modules.fastfood.entity.operate.OperatePrizeGetLogEntity;
import com.lebaoxun.modules.fastfood.service.OperatePrizeGetLogService;

@Service("operatePrizeGetLogService")
public class OperatePrizeGetLogServiceImpl extends
		ServiceImpl<OperatePrizeGetLogDao, OperatePrizeGetLogEntity> implements
		OperatePrizeGetLogService {
	
	private static Logger logger = LoggerFactory.getLogger(OperatePrizeGetLogServiceImpl.class);

	@Resource
	private OperatePrizeDao operatePrizeDao;
	
	@Resource
	private IUserService userService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String userId = (String)params.get("userId"),
		prizeId = (String)params.get("prizeId"),
		name = (String)params.get("name"),
		group = (String)params.get("group"),
		status = (String)params.get("status"),
		orderNo = (String)params.get("orderNo"),
		createTime = (String)params.get("createTime");
			
		Page<OperatePrizeGetLogEntity> page = this.selectPage(
				new Query<OperatePrizeGetLogEntity>(params).getPage(),
				new EntityWrapper<OperatePrizeGetLogEntity>()
				.eq(StringUtils.isNotBlank(userId) && StringUtils.isInteger(userId), "user_id", userId)
				.eq(StringUtils.isNotBlank(prizeId) && StringUtils.isInteger(prizeId), "prize_id", prizeId)
				.eq(StringUtils.isNotBlank(status) && StringUtils.isInteger(status), "status", status)
				.eq(StringUtils.isNotBlank(group), "group", group)
				.eq(StringUtils.isNotBlank(orderNo), "order_no", orderNo)
				.eq(StringUtils.isNotBlank(createTime), "date_format(create_time,'%Y-%m-%d')", createTime)
				.like(StringUtils.isNotBlank(name), "name", name)
				);

		return new PageUtils(page);
	}

	@Override
	public List<OperatePrizeGetLogEntity> findLogByUserId(Long userId,
			Integer status, Integer size, Integer offset) {
		// TODO Auto-generated method stub
		return this.baseMapper.findLogByUserId(userId, status, size, offset);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ResponseMessage draw(Long userId, String group) {
		// TODO Auto-generated method stub
		int count = this.baseMapper
				.selectCount(new EntityWrapper<OperatePrizeGetLogEntity>().eq(
						"user_id", userId).eq(
						"DATE_FORMAT(create_time,'%Y-%m-%d')",
						DateFormatUtils.format(new Date(),
								"yyyy-MM-dd")));
		
		if(count > 0){
			throw new I18nMessageException("60022","积分抽奖，每日仅限一次");
		}
		
		List<OperatePrizeEntity> prizes = operatePrizeDao
				.findPrizeByGroup(group);
		Integer ide = getPrizeIndex(prizes);
		OperatePrizeEntity prize = prizes.get(ide);
		
		OperatePrizeGetLogEntity log = new OperatePrizeGetLogEntity();
		log.setAisle(prize.getAisle());
		log.setCreateTime(new Date());
		log.setGroup(prize.getGroup());
		log.setIcon(prize.getIcon());
		log.setName(prize.getName());
		log.setPrizeId(prize.getId());
		log.setStatus(0);
		log.setUserId(userId);
		log.setWeight(prize.getWeight());
		this.baseMapper.insert(log);
		
		logger.debug("prize={}",new Gson().toJson(prize));
		ResponseMessage rm = userService.scorePay(userId, prize.getScore(), null, "prizeId_"+prize.getId()+",aisle_"+prize.getId()+"", "积分抽奖扣除");

		if("0".equals(rm.getErrcode())){
			rm.setData(log);
			return rm;
		}
		throw new I18nMessageException(rm.getErrcode(),rm.getErrmsg());
	}

	/**
	 * 根据Math.random()产生一个double型的随机数，判断每个奖品出现的概率
	 * 
	 * @param prizes
	 * @return random：奖品列表prizes中的序列（prizes中的第random个就是抽中的奖品）
	 */
	private static int getPrizeIndex(List<OperatePrizeEntity> prizes) {
		int random = -1;
		try {
			// 计算总权重
			double sumWeight = 0;
			for (OperatePrizeEntity p : prizes) {
				sumWeight += p.getWeight();
			}

			// 产生随机数
			double randomNumber;
			randomNumber = Math.random();

			// 根据随机数在所有奖品分布的区域并确定所抽奖品
			double d1 = 0;
			double d2 = 0;
			for (int i = 0; i < prizes.size(); i++) {
				d2 += Double.parseDouble(String.valueOf(prizes.get(i)
						.getWeight())) / sumWeight;
				if (i == 0) {
					d1 = 0;
				} else {
					d1 += Double.parseDouble(String.valueOf(prizes.get(i - 1)
							.getWeight())) / sumWeight;
				}
				if (randomNumber >= d1 && randomNumber <= d2) {
					random = i;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("message={}",e.getMessage());
		}
		return random;
	}
	
	public static void main(String[] args) {
		List<OperatePrizeEntity> prizes = new ArrayList<OperatePrizeEntity>();
		Map<String,Integer> count = new HashMap<String,Integer>();
		Integer weights[] = new Integer[]{8000,1500,479,20,1};
		for(int i=0;i<weights.length;i++){
			Integer weight = weights[i];
			OperatePrizeEntity p1 = new OperatePrizeEntity();
			p1.setWeight(weight);
			p1.setName("A_"+i);
			prizes.add(p1);
			
			count.put("A_"+i, 0);
		}
		for(int i=0;i<10000;i++){
			Integer inde = getPrizeIndex(prizes);
			count.put("A_"+inde, count.get("A_"+inde) + 1);
		}
		for(String key : count.keySet()){
			System.out.println(key+":"+count.get(key));
		}
	}

	
}
