package com.jesse.mapper.app.user;

import java.util.List;

import com.jesse.entity.app.user.AppUserFormMap;
import com.jesse.mapper.base.BaseMapper;


public interface AppUserMapper extends BaseMapper{

	public List<AppUserFormMap> findUser(AppUserFormMap appUserFormMap);
	
}
