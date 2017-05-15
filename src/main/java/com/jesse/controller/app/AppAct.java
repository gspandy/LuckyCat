package com.jesse.controller.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 基础Controller
 * @author Administrator
 *
 */
public class AppAct {
	protected Integer code;//返回的状态值，服务器端自定义
	protected String message;//状态值描述
	protected Map<String, Object> mapData=new HashMap<String, Object>();//状态值描述
	protected Map<String,List<?>> listData=new HashMap<String, List<?>>();//用于存放列表数据（可多列表）
	protected Map<String, Object> result=new HashMap<String, Object>();//封装接口固定结构
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getMapData() {
		return mapData;
	}
	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}
	
	public Map<String, List<?>> getListData() {
		return listData;
	}
	public void setListData(Map<String, List<?>> listData) {
		this.listData = listData;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Integer code,String message,Map<String, Object> mapData,Map<String,Object> listData) {
		if(code!=null){
			this.result.put("code", code);
		}
		if(!StringUtils.isEmpty(message)){
			this.result.put("message", message);
		}
		if(mapData!=null){
			this.result.put("mapData", mapData);
		}
		if(listData!=null){
			this.result.put("listData", listData);
		}
	}
}
