package com.jesse.controller.system;

import com.jesse.controller.index.BaseController;
import com.jesse.entity.LogFormMap;
import com.jesse.mapper.LogMapper;
import com.jesse.mapper.app.user.SmsLogInfoMapper;
import com.jesse.plugin.PageView;
import com.jesse.util.Common;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * 
 * @author jesse 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/smsLog/")
public class SmsLogController extends BaseController {
	@Inject
	private SmsLogInfoMapper smsLogInfoMapper;

	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("smsLogList",smsLogInfoMapper.findSmsLogInfoList(params));
		return Common.BACKGROUND_PATH + "/system/smsLog/list";
	}
}