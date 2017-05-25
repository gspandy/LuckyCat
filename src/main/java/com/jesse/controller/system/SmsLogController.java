package com.jesse.controller.system;

import com.jesse.controller.index.BaseController;
import com.jesse.entity.app.user.SmsLogInfoFormMap;
import com.jesse.mapper.app.user.SmsLogInfoMapper;
import com.jesse.util.Common;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Map;

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

	@SuppressWarnings("unchecked")
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("smsLogList",smsLogInfoMapper.findSmsLogInfoList(params));
		return Common.BACKGROUND_PATH + "/system/smsLog/list";
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="findByPage",method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> findByPage(String page,String rows) throws Exception {
		SmsLogInfoFormMap formMap = getFormMap(SmsLogInfoFormMap.class);
		formMap=toFormMap(formMap, page,rows,formMap.getStr("orderby"));
		pageView.setRecords(smsLogInfoMapper.findByPage(formMap));
		map.put("rows",pageView.getRecords());
		map.put("total",pageView.getRowCount());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}