package com.hsh.dao;

import java.util.List;

import com.hsh.po.UserInfo;
/**
 * 用户信息管理
 * @author Administrator
 *
 */
public interface UserInfoDao {
	/**
	 * 增加
	 * @param userInfo
	 */
	public void add(UserInfo userInfo);
	/**
	 * 修改
	 * @param userInfo
	 */
	public void update(UserInfo userInfo);
	/**
	 * 删除
	 * @param userInfo
	 */
	public void delete(UserInfo userInfo);
	/**
	 * 根据Id查询
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoById(String userId);
	/**
	 * 根据username查询
	 * @param username
	 * @return
	 */
	public UserInfo getUserInfoByUsername(String username);
	/**
	 * 查询全部
	 * @return
	 */
	public List<UserInfo> findAllUserInfo();
}
