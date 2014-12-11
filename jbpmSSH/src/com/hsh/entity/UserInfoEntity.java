package com.hsh.entity;

import java.util.List;

import com.hsh.po.UserInfo;
/**
 * 用户管理entity层
 * @author Administrator
 *
 */
public interface UserInfoEntity {
	/**
	 * 添加
	 * @param userInfo
	 */
	public void addUser(UserInfo userInfo);
	/**
	 * 修改
	 * @param UserInfo
	 */
	public void updateUser(UserInfo userInfo);
	/**
	 * 删除
	 * @param userId
	 */
	public void deleteUser(String userId);
	/**
	 * 根据Id查询
	 * @param userId
	 * @return
	 */
	public UserInfo getUserById(String userId);
	/**
	 * 查询全部
	 * @return
	 */
	public List<UserInfo> findAllUsers();
	/**
	 * 登陆验证
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean loginCheck(String username,String password);
	
}
