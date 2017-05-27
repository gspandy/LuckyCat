package com.jesse.mapper;

import com.jesse.entity.SmsLogInfoFormMap;
import com.jesse.mapper.base.BaseMapper;

import java.util.List;

/**
 * 短信验证码发送日志信息Mapper
 * @author lizhie
 * @date 2017年5月17日
 */
public interface SmsLogInfoMapper extends BaseMapper{
	/**分页查询短信记录
	 * @param formMap
	 * @return
	 */
	public List<SmsLogInfoFormMap> findSmsLogInfoPage(SmsLogInfoFormMap formMap);
}
