package com.jesse.controller.app.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.mns.common.ServiceException;
import com.jesse.controller.app.AppAct;
import com.jesse.entity.app.user.AppUserFormMap;
import com.jesse.entity.app.user.SmsLogInfoFormMap;
import com.jesse.mapper.app.user.AppUserMapper;
import com.jesse.mapper.app.user.SmsLogInfoMapper;
import com.jesse.util.Common;
import com.jesse.util.DateUtils;
import com.jesse.util.RandomNumberUtil;

/**
 * 
 * @ClassName: UserAct
 * @Description:app用户接口控制器
 * @author: lizhie
 * @date: 2017年5月15日 下午2:30:18
 */
@RestController
@RequestMapping("/app/")
public class UserAct extends AppAct{
	
	private  static  final Logger logger = LoggerFactory.getLogger(UserAct.class);
	
	@Inject
	private AppUserMapper userMapper;
	
	@Inject
	private SmsLogInfoMapper smsLogInfoMapper;
	
	@RequestMapping(value = "/test", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> test(HttpServletRequest request)
	{
		String ipAddress = SecurityUtils.getSubject().getSession().getHost();
		System.out.println("IP地址："+ipAddress);
		return null;
	}
			
	/**
	 * @throws Exception 
	 * 
	 * @Title: getRegisterVerifCode   
	 * @Description: 获取注册验证码 
	 * @author: lizhie  
	 * @param: @param phone
	 * @param: @return      
	 * @return: ResponseEntity<Map<String,Object>>      
	 * @throws
	 */
	@RequestMapping(value = "/getRegisterVerifCode", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> getRegisterVerifCode(HttpServletRequest request) throws Exception {
		
		String phone = request.getParameter("phone");
		if (Common.isEmpty(phone)) 
		{
			setResult(0, "请填写您的手机号码!", null, null);
			return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
		}
		
		//短信发送状态：0 失败;1 成功. 默认为0
		int sendFlag = 0;
		String code = RandomNumberUtil.getSixNumber();
		String errorMsg = "";
		String ipAddress = Common.getIpAddr(request);
		try 
		{
			//发送短信验证码
//			AliyunBatchPublishSMSMessage.sendMessage("SMS_61045004", code, "股先森", phone);
			sendFlag = 1;
			mapData.put("verifyCode",code);
			mapData.put("phone",phone);
			//保存或更新app用户信息
			saveOrUpdateUserInfo(phone, ipAddress);
			setResult(1,"发送验证码成功！", mapData, null);
		} catch (ServiceException se) {
			errorMsg = se.getErrorCode() + se.getRequestId();
			mapData.put("errorMsg",errorMsg);
			setResult(-1, "发送验证码失败！", mapData, null);
			logger.error(errorMsg);
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		//保存短信发送日志信息
		SmsLogInfoFormMap smsLogInfo = new SmsLogInfoFormMap();
		smsLogInfo.set("phone", phone);
		smsLogInfo.set("verify_code", code);
		smsLogInfo.set("ip_address", ipAddress);
		smsLogInfo.set("status", sendFlag);
		smsLogInfo.set("error_msg", errorMsg);
		smsLogInfoMapper.addEntity(smsLogInfo);
		return new ResponseEntity<Map<String,Object>>(getResult(), HttpStatus.OK); 
	}

	/**
	 * @param ipAddress 
	 * 
	 * @Title: saveOrUpdateUserInfo   
	 * @Description: 保存或者更新app用户信息
	 * @author: lizhie  
	 * @param: @param phone
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	private void saveOrUpdateUserInfo(String phone, String ipAddress)
			throws Exception {
		AppUserFormMap newUser = new AppUserFormMap();
		newUser.set("phone", phone);
		List<AppUserFormMap> list = userMapper.findUser(newUser);

		String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		// 若存在则更新app用户信息，否则新增app用户
		if (list != null && list.size() > 0) {
			AppUserFormMap user = list.get(0);
			user.set("login_times", Integer.valueOf(user.get("login_times").toString()) + 1);
			user.set("last_login_time", user.get("updated_at"));
			user.set("updated_at", date);
			user.set("last_login_ip", ipAddress);
			userMapper.editEntity(user);
		} else {
			newUser.set("login_times", 1);
			newUser.set("last_login_time", date);
			newUser.set("last_login_ip", ipAddress);
			newUser.set("created_at", date);
			userMapper.addEntity(newUser);
		}
	}
}