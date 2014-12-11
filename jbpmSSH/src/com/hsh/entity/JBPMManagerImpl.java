package com.hsh.entity;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;

public class JBPMManagerImpl implements JBPMManager {
	
	private ProcessEngine processEngine;
	private RepositoryService repositoryService;
	private ExecutionService executionService;
	private HistoryService historyService;
	private TaskService taskService;
	private IdentityService identityService;
	
	public List<ProcessDefinition> getProcessDefinitions() {
		return repositoryService.createProcessDefinitionQuery().list();
	}

	public void deployProcessDefinition() {
		ZipInputStream zis = new ZipInputStream(this.getClass()
				.getResourceAsStream("/leave.zip"));
		repositoryService.createDeployment()
				.addResourcesFromZipInputStream(zis).deploy();
	}

	public void deleteProcessDefinition(String processDefinitionId) {
		repositoryService.deleteDeploymentCascade(processDefinitionId);
	}

	public void start(String processDefinitionId, Map<String, Object> map) {
		executionService.startProcessInstanceById(processDefinitionId, map);
	}

	public List<ProcessInstance> getProcessInstances() {
		return executionService.createProcessInstanceQuery().list();
	}
	public List<Task> getAllTasks(){
		return taskService.createTaskQuery().list();
	}
	public List<Task> getTasks(String roleName) {
		return taskService.findPersonalTasks(roleName);
	}

	public void complate(String taskId, Map<String, Object> map) {
		taskService.setVariables(taskId, map);
		taskService.completeTask(taskId);
	}

	public Map<String, Object> getManagerParam(String id) {
		Task task = taskService.getTask(id);
		String taskId = task.getId();
		Set<String> strSet = new HashSet<String>();
		strSet.add("owner");
		strSet.add("day");
		strSet.add("reason");
		strSet.add("name");

		return taskService.getVariables(taskId, strSet);
	}

	public void complateByManager(String id, String result) {
		taskService.completeTask(id, result);
	}

	public Map<String, Object> getBossParam(String id) {
		Task task = taskService.getTask(id);
		String taskId = task.getId();
		Set<String> strSet = new HashSet<String>();
		strSet.add("owner");
		strSet.add("day");
		strSet.add("reason");
		strSet.add("name");
/*		strSet.add("age");
		strSet.add("address");*/
		return taskService.getVariables(taskId, strSet);
	}

	public void complateByBoss(String id) {
		taskService.completeTask(id);
	}

	public List<HistoryProcessInstance> getHistoryProcessInstances() {
		return historyService.createHistoryProcessInstanceQuery().list();
	}

	public ActivityCoordinates findActivityCoordinates(String id) {
		// 通过id查到流程实例
		ProcessInstance processInstance = executionService.findProcessInstanceById(id);
		Set<String> activityNames = processInstance.findActiveActivityNames();
		return repositoryService.getActivityCoordinates(processInstance
				.getProcessDefinitionId(), activityNames.iterator().next());
	}

	public InputStream findPicInputStream(String id) {
		// 通过流程实例id查找到流程实例
		ProcessInstance processInstance = executionService
				.findProcessInstanceById(id);
		// 通过流程实例查找流程定义id
		String processDefinitionId = processInstance.getProcessDefinitionId();
		// 通过流程id查找流程
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery().processDefinitionId(
						processDefinitionId).uniqueResult();
		return repositoryService.getResourceAsStream(processDefinition
				.getDeploymentId(), "leave.png");
	}

	public ProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public ExecutionService getExecutionService() {
		return executionService;
	}

	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

}
