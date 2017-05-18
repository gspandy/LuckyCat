package com.jesse.entity.app.user;

import com.jesse.annotation.TableSeg;
import com.jesse.util.FormMap;



/**
 * app用户实体表
 * @author lizhie
 * @date 2017年5月17日
 */
@TableSeg(tableName = "app_user", id="id")
public class AppUserFormMap extends FormMap<String,Object>{
	
	private static final long serialVersionUID = 265231129536758878L;
	
}
