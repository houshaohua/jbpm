<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>首页</title>
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
  <s:debug></s:debug>
  	<s:property value="#session.userInfo.username"/>,欢迎您！
	<hr>
	<table align="center">
		<tr>
			<td align="center" colspan="7">
				<h4>流程启动表单</h4>
			</td>
		</tr>
		<tr>
			<td>流程id</td>
			<td>发布id</td>
			<td>流程key</td>
			<td>流程name</td>
			<td>版本号</td>
			<td>启动流程</td>
		</tr>
		<s:iterator value="processDefinitions" var="dfinition">
			<tr>
				<td><s:property value="#dfinition.id" /></td>
				<td><s:property value="#dfinition.deploymentId" /></td>
				<td><s:property value="#dfinition.key" /></td>
				<td><s:property value="#dfinition.name" /></td>
				<td><s:property value="#dfinition.version" /></td>
				<td><a href="startProcessDefinition?id=<s:property value='#dfinition.id'/>">启动流程</a></td>
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
			<td>办理</td>
		</tr>
		<s:iterator value="tasks" var="task">
			<tr>
				<td><s:property value="#task.id" /></td>
				<td><s:property value="#task.key" /></td>
				<td><s:property value="#task.name" /></td>
				<td><s:property value="#task.activityName"/></td>
				<td><s:property value="#task.assignee" /></td>
				<td><a href="<s:property value='#task.formResourceName'/>?id=<s:property value='#task.id'/>">办理</a></td>
			</tr>
		</s:iterator>
	</table>
  </body>
</html>
