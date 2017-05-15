package com.jesse.mapper;

import java.util.List;

import com.jesse.entity.RoleFormMap;
import com.jesse.mapper.base.BaseMapper;

public interface RoleMapper extends BaseMapper{
	public List<RoleFormMap> seletUserRole(RoleFormMap map);
	
	public void deleteById(RoleFormMap map);
}
