<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>流程管理页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		table {
			width: 80%;
			border: 1px solid blue;
			border-collapse: collapse;
		}
		
		table tr td {
			border: 1px solid blue;
			border-collapse: collapse;
		}
	</style>
  </head>
  
  <body>
  <h1 align="center">流程管理界面</h1>
  <hr>
  <br>
	<table align="center">
		<tr>
			<td align="center" colspan="7">
				<h4>流程发布表单</h4>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				如果想发布流程请点击右边的链接
			</td>
			<td colspan="4">
				<a href="deployProcessDefinition.action">点击发布新流程</a>
			</td>
		</tr>
		<tr>
			<td>流程id</td>
			<td>发布id</td>
			<td>流程key</td>
			<td>流程name</td>
			<td>版本号</td>
			<td>移除</td>
			<td>启动流程</td>
		</tr>
		<s:iterator value="processDefinitions" var="dfinition">
			<tr>
				<td><s:property value="#dfinition.id" /></td>
				<td><s:property value="#dfinition.deploymentId" /></td>
				<td><s:property value="#dfinition.key" /></td>
				<td><s:property value="#dfinition.name" /></td>
				<td><s:property value="#dfinition.version" /></td>
				<td><a href="deleteProcessDefinition?id=<s:property value='#dfinition.deploymentId'/>">删除流程</a></td>
				<td>暂停使用<%-- <a href="startProcessDefinition?id=<s:property value='#dfinition.id'/>">启动流程</a> --%></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<table align="center">
		<tr>
			<td align="center" colspan="7">
				<h4>所有流程实例表单表单</h4>
			</td>
		</tr>
		<tr>
			<td>实例id</td>
			<td>实例key</td>
			<td>实例name</td>
			<td>实例状态</td>
			<td>activityName</td>				
			<td>查看流程图</td>
		</tr>
		<s:iterator value="processInstances" var="instance">
			<tr>
				<td><s:property value="#instance.id" /></td>
				<td><s:property value="#instance.key" /></td>
				<td><s:property value="#instance.name" /></td>
				<td><s:property value="#instance.state" /></td>
				<td><s:property value="findActiveActivityNames()"/></td>			
				<td><a href="viewProcessInstances?id=<s:property value='#instance.id'/>">查看流程图</a></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<table align="center">
		<tr>
			<td align="center" colspan="7">
				<h4>用户待办任务</h4>
			</td>
		</tr>
		<tr>
			<td>任务id</td>
			<td>任务key</td>
			<td>任务name</td>
			<td>activityName</td>
			<td>指派人</td>
		</tr>
		<s:iterator value="tasks" var="task">
			<tr>
				<td><s:property value="#task.id" /></td>
				<td><s:property value="#task.key" /></td>
				<td><s:property value="#task.name" /></td>
				<td><s:property value="#task.activityName"/></td>
				<td><s:property value="#task.assignee" /></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<table align="center">
		<tr>
			<td align="center" colspan="7">
				<h4>历史流程实例</h4>
			</td>
		</tr>
		<tr>
			<td>流程实例id</td>
			<td>历史流程实例key</td>
			<td>开始时间</td>
			<td>结束时间</td>
			<td>结束节点名称</td>
		</tr>
		<s:iterator value="historyProcessInstances" var="history">
			<tr>
				<td><s:property value="#history.processDefinitionId" /></td>
				<td><s:property value="#history.key" /></td>
				<td><s:property value="#history.startTime" /></td>
				<td><s:property value="#history.endTime" /></td>
				<td><s:property value="#history.endActivityName"/></td>
			</tr>
		</s:iterator>
	</table>
  </body>
</html>
