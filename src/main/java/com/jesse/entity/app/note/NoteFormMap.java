package com.jesse.entity.app.note;

import com.jesse.annotation.TableSeg;
import com.jesse.util.FormMap;



/**
 * app用户笔记实体类
 * @author lizhie
 * @date 2017年5月31日
 */
@TableSeg(tableName = "note", id="id")
public class NoteFormMap extends FormMap<String,Object>{

	private static final long serialVersionUID = -6863339921365723497L;

}
