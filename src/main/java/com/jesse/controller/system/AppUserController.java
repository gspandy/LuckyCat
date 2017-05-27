package com.jesse.controller.system;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jesse.mapper.app.user.AppUserMapper;
import com.jesse.controller.index.BaseController;
import com.jesse.entity.app.user.AppUserFormMap;
import com.jesse.plugin.PageView;
import com.jesse.util.Common;
import com.jesse.util.JsonUtils;
import com.jesse.util.POIUtils;

/**
 * app用户管理Controller
 * @author lizhie
 * @date 2017年5月23日
 */
@Controller
@RequestMapping("/appuser/")
public class AppUserController extends BaseController {
	@Inject
	private AppUserMapper appUserMapper;
	
	/**
	 * 查询app用户列表
	 * @author lizhie
	 * @param model
	 * @return
	 * @throws Exception
	 * @date 2017年5月23日
	 */
	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/appuser/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize,String column,String sort) throws Exception {
		AppUserFormMap appUserFormMap = getFormMap(AppUserFormMap.class);
		appUserFormMap = toFormMap(appUserFormMap, pageNow, pageSize,appUserFormMap.getStr("orderby"));
		appUserFormMap.put("column", column);
		appUserFormMap.put("sort", sort);
		//不调用默认分页,调用自已的mapper中findUserPage
        pageView.setRecords(appUserMapper.findUserPage(appUserFormMap));
        return pageView;
	}
	
	/**
	 * 导出app用户列表
	 * @author lizhie
	 * @param request
	 * @param response
	 * @throws IOException
	 * @date 2017年5月23日
	 */
	@RequestMapping("/export")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = "app用户列表";
		AppUserFormMap appUserFormMap = findHasHMap(AppUserFormMap.class);
		String exportData = appUserFormMap.getStr("exportData");// 列表头的json字符串
		List<Map<String, Object>> listMap = JsonUtils.parseJSONList(exportData);
		List<AppUserFormMap> list = appUserMapper.findUserPage(appUserFormMap);
		POIUtils.exportToExcel(response, listMap, list, fileName);
	}
}