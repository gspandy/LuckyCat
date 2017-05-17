package com.jesse.entity.app.user;

import com.jesse.annotation.TableSeg;
import com.jesse.util.FormMap;



/**
 * 
 * @ClassName:  SmsLogInfoFormMap   
 * @Description:短信验证码发送日志信息表实体
 * @author: lizhie
 * @date:   2017年5月17日 下午2:47:34
 */
@TableSeg(tableName = "sms_log_info", id="id")
public class SmsLogInfoFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = -2830902186328908128L;
	
}
