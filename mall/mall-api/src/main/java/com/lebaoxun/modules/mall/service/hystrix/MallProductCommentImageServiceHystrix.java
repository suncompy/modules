package com.lebaoxun.modules.mall.service.hystrix;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.lebaoxun.modules.mall.entity.MallProductCommentImageEntity;
import com.lebaoxun.modules.mall.service.IMallProductCommentImageService;
import com.lebaoxun.commons.exception.I18nMessageException;
import com.lebaoxun.commons.exception.ResponseMessage;

/**
 * 评价图片表
 *
 * @author caiqianyi
 * @email 270852221@qq.com
 * @date 2018-07-12 19:57:11
 */

@Component
public class MallProductCommentImageServiceHystrix implements IMallProductCommentImageService {

	@Override
	public ResponseMessage list(Map<String, Object> params) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage info(Long id) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage save(Long adminId,MallProductCommentImageEntity mallProductCommentImage) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage update(Long adminId,MallProductCommentImageEntity mallProductCommentImage) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}

	@Override
	public ResponseMessage delete(Long adminId,Long[] ids) {
		throw new I18nMessageException("502","服务器异常，请稍后重试");
	}
    
}

