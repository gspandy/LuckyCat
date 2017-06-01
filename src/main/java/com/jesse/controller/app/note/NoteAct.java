package com.jesse.controller.app.note;

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
import com.jesse.entity.app.note.NoteFormMap;
import com.jesse.mapper.app.note.NoteMapper;
import com.jesse.util.Common;

/**
 * app端笔记控制器
 * @author lizhie
 * @date 2017年5月31日
 */
@RestController
@RequestMapping("/app/note")
public class NoteAct extends AppAct{
	
	private  static  final Logger logger = LoggerFactory.getLogger(NoteAct.class);
	
	@Inject
	private NoteMapper noteMapper;
	
	/**
	 * 写笔记
	 * @author lizhie
	 * @param request
	 * @return
	 * @date 2017年5月27日
	 */
	@RequestMapping(value = "/writeNote", produces = { "application/json;charset=UTF-8" })
	public ResponseEntity<Map<String, Object>> writeNote(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		if (Common.isEmpty(phone)) {
			setResult(0, "手机号码为空!", null, null);
			return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
		}
		
		//股票代码
		String stockCode = request.getParameter("stockCode");
		if (Common.isEmpty(stockCode)) {
			setResult(2, "股票代码为空!", null, null);
			return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
		}

		String expectDays = request.getParameter("expectDays");
		String rise = request.getParameter("rise");
		String stopLoss = request.getParameter("stopLoss");
		String noteTitle = request.getParameter("noteTitle");
		String logic = request.getParameter("logic");
		String label = request.getParameter("label");
		String markedPrice = request.getParameter("markedPrice");
		String limitTime = request.getParameter("limitTime");
		
		try {
			NoteFormMap note = new NoteFormMap();
			note.set("phone", phone);
			note.set("stock_code", stockCode);
			note.set("expect_days", expectDays);
			note.set("rise", rise);
			note.set("stop_loss", stopLoss);
			note.set("note_title", noteTitle);
			note.set("logic", logic);
			note.set("label", label);
			note.set("marked_price", markedPrice);
			note.set("limit_time", limitTime);
			noteMapper.addEntity(note);
			setResult(1, "保存笔记成功！", null, null);
		} catch (Exception e) {
			setResult(-1, "保存笔记失败！", null, null);
			logger.error(e.getMessage());
		}

		return new ResponseEntity<Map<String, Object>>(getResult(), HttpStatus.OK);
	}
}
