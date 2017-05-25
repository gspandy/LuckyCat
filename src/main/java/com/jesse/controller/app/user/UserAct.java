package com.jesse.controller.app.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
import com.jesse.util.HttpUtil;
import com.jesse.util.JsonUtils;
import com.jesse.util.RandomNumberUtil;


/**
 * app用户接口控制器
 * @author lizhie
 * @date 2017年5月17日
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
		mapData.clear();
		String ipAddress = SecurityUtils.getSubject().getSession().getHost();
		System.out.println("IP地址："+ipAddress);
		return null;
	}
			
	/**
	 * 获取注册验证码 
	 * @author lizhie
	 * @param request
	 * @return
	 * @throws Exception
	 * @date 2017年5月17日
	 */
	@RequestMapping(value = "/getRegisterVerifCode", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> getRegisterVerifCode(HttpServletRequest request) throws Exception {
		mapData.clear();
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
			saveOrUpdateUserInfo(request);
			setResult(1,"发送验证码成功！", mapData, null);
		} catch (ServiceException se) {
			sendFlag = 0;
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
	 * 用户登录
	 * @author lizhie
	 * @param request
	 * @return
	 * @throws Exception
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/login", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> login(HttpServletRequest request) {
		mapData.clear();
		try {
			AppUserFormMap user = saveOrUpdateUserInfo(request);
			String json = JsonUtils.beanToJson(user);
			mapData.put("userInfo", json);
			setResult(1, "登录成功！", mapData, null);
		} catch (Exception e) {
			setResult(-1, "登录失败！", mapData, null);
			logger.error(e.getMessage());
		}

		return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
	}

	/**
	 * 保存或者更新app用户信息
	 * @author lizhie
	 * @param phone 手机号码
	 * @param ipAddress IP地址
	 * @return
	 * @throws Exception
	 * @date 2017年5月18日
	 */
	private AppUserFormMap saveOrUpdateUserInfo(HttpServletRequest request) throws Exception {
		String phone = request.getParameter("phone");
		String deviceId = request.getParameter("deviceId");
		String isWeixinLogin = request.getParameter("isWeixinLogin");
		String ipAddress = Common.getIpAddr(request);
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
			user.set("device_id", deviceId);
			user = isWeixinLogin.equals("yes") ? saveOrUpdateWeixinInfo(user, request, date) : user;
			userMapper.editEntity(user);
			return user;
		}
		
		newUser.set("login_times", 1);
		newUser.set("last_login_time", date);
		newUser.set("last_login_ip", ipAddress);
		newUser.set("created_at", date);
		newUser.set("device_id", deviceId);
		newUser = isWeixinLogin.equals("yes") ? saveOrUpdateWeixinInfo(newUser, request, date) : newUser;
		userMapper.addEntity(newUser);
		return newUser;
	}
	
	/**
	 * 保存或更新用户微信相关信息
	 * @author lizhie
	 * @param user
	 * @param request
	 * @param date 
	 * @return
	 * @date 2017年5月23日
	 */
	private AppUserFormMap saveOrUpdateWeixinInfo(AppUserFormMap user, HttpServletRequest request, String date) {
		//授权用户唯一标识
        String nickname = request.getParameter("nickname");
        String headImgUrl = request.getParameter("headImgUrl");
        String openId = request.getParameter("openid");
        String unionid = request.getParameter("unionid");
        String accessToken = request.getParameter("accessToken");
        String subscribe = request.getParameter("subscribe");
        String lastSubscribeAt = request.getParameter("lastSubscribeAt");
        String weixinRemark = request.getParameter("weixinRemark");
        user.set("weixin_nickname", nickname);
        user.set("weixin_headImgUrl", headImgUrl);
        user.set("weixin_openid", openId);
        user.set("weixin_unionid", unionid);
        user.set("weixin_access_token", accessToken);
        user.set("weixin_subscribed", subscribe);
        user.set("weixin_subscribed_at", lastSubscribeAt);
        user.set("weixin_remark", weixinRemark);
        user.set("last_weixin_visited_at", date);
		return user;
	}

	/**
	 * 微信登录验证
	 * @author lizhie
	 * @param request
	 * @return
	 * @throws Exception
	 * @date 2017年5月18日
	 */
	@RequestMapping(value = "/weixinAuth", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> weixinAuth(HttpServletRequest request) throws Exception {
		mapData.clear();
		String code = request.getParameter("code");
		StringBuffer loginUrl = new StringBuffer(Common.WX_AUTH_LOGIN_URL);  
        loginUrl.append("?appid=").append(Common.WX_APP_ID).append("&secret=").append(Common.WX_APP_KEY);
        loginUrl.append("&code=").append(code).append("&grant_type=authorization_code");
        
        String loginResult = HttpUtil.doGet(loginUrl.toString());
        JSONObject jsonObj = JSONObject.fromObject(loginResult);
        
        if(null == jsonObj)
        {
        	setResult(-1, "微信登录失败！", null, null);
            logger.error("login result info is null! result:" + loginResult);
            return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
        }
        //ERR_OK = 0(用户同意),ERR_AUTH_DENIED = -4(用户拒绝授权),ERR_USER_CANCEL = -2(用户取消)
        String errcode = jsonObj.optString("errcode");
        if (!Common.isEmpty(errcode))
        {  
        	setResult(-1, "微信登录验证失败！", null, null);
            logger.error("login weixin error! result:" + loginResult);
            return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
        }
        //授权用户唯一标识
        String openId = jsonObj.optString("openid");
        if (Common.isEmpty(openId)) 
        {  
        	setResult(-1, "微信登录验证失败！", null, null);
            logger.error("login weixin getOpenId error! result:"+loginResult);
            return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
        }  
        
        //接口调用凭证
        String accessToken = jsonObj.optString("access_token");
        //access_token接口调用凭证超时时间，单位(秒)
        String expiresIn = jsonObj.optString("expires_in");
        //用户刷新access_token
        String refreshToken = jsonObj.optString("refresh_token");
        //用户授权的作用域，使用逗号(,)分隔
        String scope = jsonObj.optString("scope");
          
        //获取用户信息  
        StringBuffer userInfoUrl = new StringBuffer(Common.WX_USERINFO_URL);
        userInfoUrl.append("?access_token=").append(accessToken).append("&openid=").append(openId); 
        String userResult = HttpUtil.doGet(userInfoUrl.toString());
        JSONObject userInfoJson = JSONObject.fromObject(userResult);
        //用户是否订阅该公众号标识：0 否,1 是
        String subscribe = userInfoJson.optString("subscribe");
        //用户的昵称
        String nickname = userInfoJson.optString("nickname");
        //性别:1 男,2 女,0 未知
        String sex = userInfoJson.optString("sex");
        //用户头像，最后一个数值代表正方形头像大小
        String headImgUrl = userInfoJson.optString("headimgurl");
        //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
        String unionid = userInfoJson.optString("unionid");
        //用户关注时间，为时间戳。若曾多次关注，则取最后关注时间
        String lastSubscribeAt = userInfoJson.optString("subscribe_time");
        //公众号运营者对粉丝的备注
        String weixinRemark = userInfoJson.optString("remark");
        
        mapData.put("isWeixinLogin", "yes");
        mapData.put("errcode", errcode);
        mapData.put("openId", openId);
        mapData.put("accessToken", accessToken);
        mapData.put("expiresIn", expiresIn);
        mapData.put("refreshToken", refreshToken);
        mapData.put("scope", scope);
        mapData.put("subscribe", subscribe);
        mapData.put("lastSubscribeAt", lastSubscribeAt);
        mapData.put("nickname", nickname);
        mapData.put("sex", sex);
        mapData.put("headImgUrl", headImgUrl);
        mapData.put("unionid", unionid);
        mapData.put("weixinRemark", weixinRemark);

        setResult(1, "微信登录验证成功！", mapData, null);
		return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
	}
}