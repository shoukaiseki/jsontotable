<%@ page language="java" import="java.util.*,java.net.URL" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page contentType="text/html;charset=UTF-8" autoFlush="true"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>答题练习</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
  </head>
  
  <body>
	host=${host}
  </body>
</html>
