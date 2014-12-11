package com.hsh.entity;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;

/**
 * JBPM管理器接口
 * @author Administrator
 *
 */
public interface JBPMManager {
	/**
	 * 获取流程定义列表
	 * @return
	 */
	public List<ProcessDefinition> getProcessDefinitions();
	/**
	 * 发布流程
	 */
	public void deployProcessDefinition();
	/**
	 * 删除流程
	 * @param processDefinitionId
	 */
	public void deleteProcessDefinition(String processDefinitionId);
	/**
	 * 启动流程
	 * @param processDefinitionId
	 * @param map
	 */
	public void start(String processDefinitionId, Map<String, Object> map);
	/**
	 * 获取流程实例列表
	 * @return
	 */
	public List<ProcessInstance> getProcessInstances();
	/**
	 * 获取待办任务列表
	 * @return
	 */
	public List<Task> getAllTasks();
	/**
	 * 根据roleName获取待办任务
	 * @param roleName
	 * @return
	 */
	public List<Task> getTasks(String roleName);
	/**
	 * 处理任务
	 * @param taskId
	 * @param map
	 */
	public void complate(String taskId, Map<String, Object> map);
	/**
	 * 经理审批参数查询
	 * @param id
	 * @return
	 */
	public Map<String, Object> getManagerParam(String id);
	/**
	 * 经理处理任务
	 * @param id
	 * @param result
	 */
	public void complateByManager(String id, String result);
	/**
	 * 老板审批参数查询
	 * @param id
	 * @return
	 */
	public Map<String, Object> getBossParam(String id);
	/**
	 * 老板处理任务
	 * @param id
	 */
	public void complateByBoss(String id);
	/**
	 * 获取历史流程实例
	 * 
	 * @return
	 */
	public List<HistoryProcessInstance> getHistoryProcessInstances();

	/**
	 * 查找流程图的活动坐标
	 * 
	 * @param id
	 * @return
	 */
	public ActivityCoordinates findActivityCoordinates(String id);

	/**
	 * 查找图片输入流
	 * 
	 * @param id
	 * @return
	 */
	public InputStream findPicInputStream(String id);
}
