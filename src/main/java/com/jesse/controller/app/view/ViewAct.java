package com.jesse.controller.app.view;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jesse.controller.app.AppAct;
import com.jesse.entity.app.view.ViewFormMap;
import com.jesse.mapper.app.view.ViewMapper;
import com.jesse.util.Common;

/**
 * app端观点接口控制器
 * @author lizhie
 * @date 2017年5月25日
 */
@RestController
@RequestMapping("/app/view")
public class ViewAct extends AppAct{
	
	private  static  final Logger logger = LoggerFactory.getLogger(ViewAct.class);
	
	@Inject
	private ViewMapper viewMapper;
	
	/**
	 * 写观点
	 * @author lizhie
	 * @param request
	 * @return
	 * @date 2017年5月27日
	 */
	@RequestMapping(value = "/writeView", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> writeView(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		if (Common.isEmpty(phone)) {
			setResult(0, "手机号码为空!", null, null);
			return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
		}

		String viewContent = request.getParameter("view");
		if (Common.isEmpty(viewContent)) {
			setResult(2, "观点内容为空!", null, null);
			return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
		}

		String noteId = request.getParameter("noteId");
		try {
			ViewFormMap view = new ViewFormMap();
			view.set("phone", phone);
			view.set("view_content", viewContent);
			view.set("note_id", noteId);
			viewMapper.addEntity(view);
			setResult(1, "保存观点成功！", null, null);
		} catch (Exception e) {
			setResult(-1, "保存观点失败！", null, null);
			logger.error(e.getMessage());
		}

		return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
	}
}
