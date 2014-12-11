package com.hsh.struts;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.task.Task;

import com.hsh.entity.JBPMManager;
import com.hsh.entity.UserInfoEntity;
import com.hsh.po.UserInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserInfoAction extends ActionSupport {
	public UserInfo userInfo;
	public UserInfoEntity userInfoEntity;
	// 流程定义集合
	private List<ProcessDefinition> processDefinitions;
	// 当前用户任务集合
	private List<Task> tasks;
	//JBPM管理器
	private JBPMManager jbpmManager;
	//前台传过来的参数
	private String id;
	public String login(){
		if(null!=userInfo){
			boolean flag=userInfoEntity.loginCheck(userInfo.getUsername(), userInfo.getPassword());
			if(flag){
				ServletActionContext.getContext().getSession().put("userInfo",userInfo);
				processDefinitions = jbpmManager.getProcessDefinitions();
				UserInfo userInfo2=(UserInfo) ActionContext.getContext().getSession().get("userInfo");
				tasks = jbpmManager.getTasks(userInfo2.getUsername());
				return SUCCESS;
			}
		}
		return INPUT;
	}
	public String addUser(){
		try{
			userInfoEntity.addUser(userInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public UserInfoEntity getUserInfoEntity() {
		return userInfoEntity;
	}
	public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
		this.userInfoEntity = userInfoEntity;
	}
	public List<ProcessDefinition> getProcessDefinitions() {
		return processDefinitions;
	}
	public void setProcessDefinitions(List<ProcessDefinition> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public JBPMManager getJbpmManager() {
		return jbpmManager;
	}
	public void setJbpmManager(JBPMManager jbpmManager) {
		this.jbpmManager = jbpmManager;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
