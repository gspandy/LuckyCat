package com.jesse.controller.system;

import com.jesse.controller.index.BaseController;
import com.jesse.entity.SmsLogInfoFormMap;
import com.jesse.mapper.SmsLogInfoMapper;
import com.jesse.util.Common;
import com.jesse.util.JsonUtils;
import com.jesse.util.POIUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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

	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		 return Common.BACKGROUND_PATH + "/system/smsLog/list";
	}

	@ResponseBody
	@RequestMapping(value = "findByPage", method = RequestMethod.POST)
	 public ResponseEntity<Map<String, Object>> findByPage(String page, String
	 rows) throws Exception {
		SmsLogInfoFormMap formMap = getFormMap(SmsLogInfoFormMap.class);
		formMap = toFormMap(formMap, page, rows, formMap.getStr("orderby"));
		pageView.setRecords(smsLogInfoMapper.findSmsLogInfoPage(formMap));
		map.put("rows",pageView.getRecords());
		map.put("total",pageView.getRowCount());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping("/export")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "短信验证记录";
		SmsLogInfoFormMap smsLogInfo = findHasHMap(SmsLogInfoFormMap.class);
		String exportData = smsLogInfo.getStr("exportData");// 列表头的json字符串
		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);
		List<SmsLogInfoFormMap> list = smsLogInfoMapper.findSmsLogInfoPage(smsLogInfo);
		POIUtils.exportToExcel(response, listMap, list, fileName);
	}
}