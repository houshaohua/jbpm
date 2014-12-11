package com.hsh.struts;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;

import com.hsh.entity.JBPMManager;
import com.hsh.po.ApplyForm;
import com.hsh.po.UserInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 流程管理Action
 * @author Administrator
 *
 */
public class ProcessAction extends ActionSupport {
	// 流程定义集合
	private List<ProcessDefinition> processDefinitions;
	// 流程实例集合
	private List<ProcessInstance> processInstances;
	// 历史流程
	private List<HistoryProcessInstance> historyProcessInstances;
	// 当前用户任务集合
	private List<Task> tasks;
	//JBPM管理器
	private JBPMManager jbpmManager;
	//前台传过来的参数
	private String id;
	// 流程参数集合
	private Map<String, Object> map;
	// 流程图活动坐标
	private ActivityCoordinates activityCoordinates;
	//图片流
	private InputStream imageStream;
	//申请单
	private ApplyForm applyForm;
	
	// 用于接收经理审批结果的字符串，批注和驳回
	private String result;
	
	/**
	 * 初始化流程管理页面数据
	 * @return
	 */
	public String processManager(){
		processDefinitions = jbpmManager.getProcessDefinitions();
		processInstances = jbpmManager.getProcessInstances();
		historyProcessInstances = jbpmManager.getHistoryProcessInstances();
		tasks = jbpmManager.getAllTasks();
		return SUCCESS;
	}
	/**
	 * 发布流程
	 * @return
	 */
	public String deploy(){
		jbpmManager.deployProcessDefinition();
		return processManager();
	}
	/**
	 * 删除流程
	 * @return
	 */
	public String delete(){
		jbpmManager.deleteProcessDefinition(id);
		return processManager();
	}
	/**
	 * 启动流程
	 * @return
	 */
	public String start(){
		map = new HashMap<String, Object>();
		//map.put("owner",(String) ActionContext.getContext().getSession().get("userInfo"));//(String) ActionContext.getContext().getSession().get("role")
		UserInfo userInfo2=(UserInfo) ActionContext.getContext().getSession().get("userInfo");
		map.put("owner",userInfo2.getUsername());
		jbpmManager.start(id, map);
		return processManager();
	}
	/**
	 * 显示流程图片
	 * @return
	 */
	public String view() {
		activityCoordinates = jbpmManager.findActivityCoordinates(id);
		return SUCCESS;
	}
	/**
	 * 获取流程图片
	 * 
	 * @return
	 */
	public String pic() {
		imageStream = jbpmManager.findPicInputStream(id);
		return SUCCESS;
	}
	/**
	 * 申请请求
	 * 
	 * @return
	 */
	public String request() {
		return SUCCESS;
	}
	/**
	 * 处理用户申请请求
	 * 
	 * @return
	 */
	public String submit() {
		map = new HashMap<String, Object>();
		map.put("owner", applyForm.getOwner());
		map.put("day", Integer.parseInt(applyForm.getDay()));
		map.put("reason", applyForm.getReason());
		map.put("name", applyForm.getOwner());
		jbpmManager.complate(applyForm.getTaskId(), map);
		
		processDefinitions = jbpmManager.getProcessDefinitions();
		UserInfo userInfo2=(UserInfo) ActionContext.getContext().getSession().get("userInfo");
		tasks = jbpmManager.getTasks(userInfo2.getUsername());
		return SUCCESS;
	}
	/**
	 * 经理审批请求
	 * 
	 * @return
	 */
	public String managerRequerst() {
		map = jbpmManager.getManagerParam(id);
		return SUCCESS;
	}

	/**
	 * 经理审批
	 * 
	 * @return
	 */
	public String submitManager() {
		jbpmManager.complateByManager(id, result);
		
		processDefinitions = jbpmManager.getProcessDefinitions();
		UserInfo userInfo2=(UserInfo) ActionContext.getContext().getSession().get("userInfo");
		tasks = jbpmManager.getTasks(userInfo2.getUsername());
		return SUCCESS;
	}

	/**
	 * 老板请求
	 * 
	 * @return
	 */
	public String bossRequest() {
		map = jbpmManager.getBossParam(id);
		return SUCCESS;
	}

	/**
	 * 老板处理
	 * 
	 * @return
	 */
	public String submitBoss() {
		jbpmManager.complateByBoss(id);
		
		processDefinitions = jbpmManager.getProcessDefinitions();
		UserInfo userInfo2=(UserInfo) ActionContext.getContext().getSession().get("userInfo");
		tasks = jbpmManager.getTasks(userInfo2.getUsername());
		return SUCCESS;
	}
	public List<ProcessDefinition> getProcessDefinitions() {
		return processDefinitions;
	}
	public void setProcessDefinitions(List<ProcessDefinition> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}
	public List<ProcessInstance> getProcessInstances() {
		return processInstances;
	}
	public void setProcessInstances(List<ProcessInstance> processInstances) {
		this.processInstances = processInstances;
	}
	public List<HistoryProcessInstance> getHistoryProcessInstances() {
		return historyProcessInstances;
	}
	public void setHistoryProcessInstances(
			List<HistoryProcessInstance> historyProcessInstances) {
		this.historyProcessInstances = historyProcessInstances;
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
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public ActivityCoordinates getActivityCoordinates() {
		return activityCoordinates;
	}
	public void setActivityCoordinates(ActivityCoordinates activityCoordinates) {
		this.activityCoordinates = activityCoordinates;
	}
	public InputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	public ApplyForm getApplyForm() {
		return applyForm;
	}
	public void setApplyForm(ApplyForm applyForm) {
		this.applyForm = applyForm;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
