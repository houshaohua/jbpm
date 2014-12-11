package com.hsh.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hsh.po.UserInfo;
/**
 * 用户管理dao层实现类
 * @author Administrator
 *
 */
public class UserInfoDaoImpl extends HibernateDaoSupport implements UserInfoDao {

	public void add(UserInfo userInfo) {
		Serializable s=getHibernateTemplate().save(userInfo);
		System.out.println(s.toString());
	}

	public void update(UserInfo userInfo) {
		getHibernateTemplate().merge(userInfo);
	}

	public void delete(UserInfo userInfo) {
		getHibernateTemplate().delete(userInfo);
	}

	public UserInfo getUserInfoById(String userId) {
		return (UserInfo) getHibernateTemplate().get(UserInfo.class, userId);
	}
	public UserInfo getUserInfoByUsername(String username){
		List resultList = null;
		String hql = "from UserInfo where username = ?";
		resultList = getHibernateTemplate().find(hql, username);
		if (null != resultList && resultList.size() > 0) {
			return (UserInfo) resultList.get(0);
		} else {
			return null;
		}
	}
	public List<UserInfo> findAllUserInfo() {	
		String hql = "from UserInfo order by id";
		List resultList = getHibernateTemplate().find(hql);
		if (null != resultList) {
			return resultList;
		} else {
			return null;
		}
	}

}
