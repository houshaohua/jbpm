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

  </head>
  
  <body>
  	<img src="showPic?id=${id }"
			style="position: absolute; left: 0px; top: 0px;">
		<div
			style="position:absolute;border:1px solid red;left:${activityCoordinates.x }px;top:${activityCoordinates.y }px;width:${activityCoordinates.width }px;height:${activityCoordinates.height}px;"></div>
  </body>
</html>
