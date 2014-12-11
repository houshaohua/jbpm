package com.hsh.entity;

import java.util.List;

import com.hsh.dao.UserInfoDao;
import com.hsh.po.UserInfo;
/**
 * 用户管理entity层实现类
 * @author Administrator
 *
 */
public class UserInfoEntityImpl implements UserInfoEntity {
	
	private UserInfoDao userInfoDao;
	
	public void addUser(UserInfo userInfo) {
		userInfoDao.add(userInfo);
	}

	public void updateUser(UserInfo userInfo) {
		userInfoDao.update(userInfo);
	}

	public void deleteUser(String userId) {
		UserInfo userInfo=userInfoDao.getUserInfoById(userId);
		userInfoDao.delete(userInfo);
	}

	public UserInfo getUserById(String userId) {
		return userInfoDao.getUserInfoById(userId);
	}

	public List<UserInfo> findAllUsers() {
		return userInfoDao.findAllUserInfo();
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
	
	public boolean loginCheck(String username,String password){
		
		UserInfo userInfo=userInfoDao.getUserInfoByUsername(username);
		if(null!=userInfo&&null!=userInfo.getPassword()&&userInfo.getPassword().equals(password)){
			return true;
		}
		return false;
	}
}
