package com.jesse.controller.system;

import com.jesse.controller.index.BaseController;
import com.jesse.entity.SmsLogInfoFormMap;
import com.jesse.mapper.SmsLogInfoMapper;
import com.jesse.plugin.PageView;
import com.jesse.util.Common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		// return Common.BACKGROUND_PATH + "/system/smsLog/list";
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/smsLog/list1";
	}

	@ResponseBody
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	// public ResponseEntity<Map<String, Object>> findByPage(String page,String
	// rows) throws Exception {
	public PageView findByPage(String pageNow, String pageSize, String column, String sort) throws Exception {
		SmsLogInfoFormMap formMap = getFormMap(SmsLogInfoFormMap.class);
		formMap = toFormMap(formMap, pageNow, pageSize, formMap.getStr("orderby"));
		formMap.put("column", column);
		formMap.put("sort", sort);
		pageView.setRecords(smsLogInfoMapper.findSmsLogInfoPage(formMap));
		// map.put("rows",pageView.getRecords());
		// map.put("total",pageView.getRowCount());
		// return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		return pageView;
	}
}