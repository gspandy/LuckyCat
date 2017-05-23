package com.jesse.mapper.app.user;

import com.jesse.entity.app.user.SmsLogInfoFormMap;
import com.jesse.mapper.base.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 短信验证码发送日志信息Mapper
 * @author lizhie
 * @date 2017年5月17日
 */
public interface SmsLogInfoMapper extends BaseMapper{
	public List<SmsLogInfoFormMap> findSmsLogInfoList(Map<String,Object> map);
}
