package com.jesse.mapper;

import java.util.List;

import com.jesse.entity.UserFormMap;
import com.jesse.mapper.base.BaseMapper;


public interface UserMapper extends BaseMapper{

	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
}
