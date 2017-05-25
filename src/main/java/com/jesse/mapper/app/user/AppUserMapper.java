package com.jesse.mapper.app.user;

import java.util.List;

import com.jesse.entity.app.user.AppUserFormMap;
import com.jesse.mapper.base.BaseMapper;

/**
 * app用户Mapper
 * @author lizhie
 * @date 2017年5月17日
 */
public interface AppUserMapper extends BaseMapper{

	/**
	 * 根据用户信息查找用户
	 * @author lizhie
	 * @param appUserFormMap
	 * @return
	 * @date 2017年5月18日
	 */
	public List<AppUserFormMap> findUser(AppUserFormMap appUserFormMap);

	/**
	 * 分页查询app用户列表
	 * @author lizhie
	 * @param appUserFormMap
	 * @return
	 * @date 2017年5月24日
	 */
	public List<AppUserFormMap> findUserPage(AppUserFormMap appUserFormMap);
}
