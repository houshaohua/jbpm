<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>填写申请表</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">


	</head>
	<s:debug></s:debug>
	<body>
		<fieldset style="width: 80%;">
			<legend>
				申请
			</legend>
			<form action="submit" method="post">
				<input type="hidden" name="applyForm.taskId" value="${param.id}">
				申请人：
				<input type="text" name="applyForm.owner" value="${userInfo.username}" />
				<br />
				请假时间：
				<input type="text" name="applyForm.day" value="" />
				<br />
				请假原因：
				<textarea name="applyForm.reason"></textarea>
				<br />
				<input type="submit" />
			</form>
		</fieldset>
	</body>
</html>
