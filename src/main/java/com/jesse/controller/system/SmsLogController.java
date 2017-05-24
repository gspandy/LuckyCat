package com.jesse.controller.system;

import com.jesse.controller.index.BaseController;
import com.jesse.entity.app.user.SmsLogInfoFormMap;
import com.jesse.mapper.app.user.SmsLogInfoMapper;
import com.jesse.plugin.PageView;
import com.jesse.util.Common;
import com.jesse.util.page.DataSourceRequest;
import com.jesse.util.page.MybatisPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
		model.addAttribute("smsLogList",smsLogInfoMapper.findSmsLogInfoList(params));
		return Common.BACKGROUND_PATH + "/system/smsLog/list";
	}
	@ResponseBody
	@RequestMapping(value="findByPage",method = RequestMethod.POST)
	public MybatisPage findByPage(@RequestBody DataSourceRequest dataSourceRequest) throws Exception {
		SmsLogInfoFormMap formMap = getFormMap(SmsLogInfoFormMap.class);
		formMap=toFormMap1(formMap, dataSourceRequest.getPage(), dataSourceRequest.getPageSize(),formMap.getStr("orderby"));
		pageView.setRecords(smsLogInfoMapper.findByPage(formMap));
		MybatisPage page=new MybatisPage();
		page.setContent(pageView.getRecords());
		page.setTotalElements(pageView.getRowCount());
		return page;
	}
}