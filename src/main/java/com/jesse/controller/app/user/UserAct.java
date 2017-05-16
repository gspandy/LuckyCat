package com.jesse.controller.app.user;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.mns.common.ServiceException;
import com.jesse.controller.app.AppAct;
import com.jesse.util.AliyunBatchPublishSMSMessage;
import com.jesse.util.Common;
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
	
	@RequestMapping(value = "/test", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> getRegisterVerifCode(HttpServletRequest request)
	{
		String ipAddress = Common.getIpAddr(request);
		System.out.println("IP地址："+ipAddress);
		return null;
	}
			
	/**
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
	public ResponseEntity<Map<String, Object>> getRegisterVerifCode(HttpServletRequest request,@RequestParam("phone") String phone) {
		
		String pp = request.getParameter("phone");
		System.out.println(pp);
		if (Common.isEmpty(phone)) 
		{
			setResult(0, "请填写您的手机号码!", null, null);
			return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
		}
		
		String code = RandomNumberUtil.getSixNumber();
		try 
		{
//			AliyunBatchPublishSMSMessage.sendMessage("SMS_61045004", code, "股先森", phone);
			mapData.put("verifyCode",code);
			mapData.put("phone",phone);
			setResult(1,"发送验证码成功！", mapData, null);
		} catch (ServiceException se) {
			String errorMsg = se.getErrorCode() + se.getRequestId();
			mapData.put("errorMsg",errorMsg);
			setResult(-1, "发送验证码失败！", mapData, null);
			logger.error(errorMsg);
			logger.error(se.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		String ipAddress = Common.getIpAddr(request);
		
		return new ResponseEntity<Map<String,Object>>(getResult(), HttpStatus.OK); 
	}
}