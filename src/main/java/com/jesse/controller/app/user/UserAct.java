package com.jesse.controller.app.user;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @ClassName:  UserAct   
 * @Description:app用户接口控制器 
 * @author: lizhie
 * @date:   2017年5月15日 下午2:30:18
 */
@RestController
@RequestMapping("/app/")
public class UserAct {
	@RequestMapping(value = "/getVerificationCode", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> getVerificationCode(
			@RequestParam("phone") String phone) {
		return null;
	}
}