<%@page import="com.libo.tools.XLog"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.libo.model.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0" />
<title>Insert title here</title>
</head>

<body>

	<%
		session.setAttribute("sign", "123456");
		XLog.logger.info("访客ip:" + request.getRemoteAddr());
	%>
	
	欢迎光临&nbsp;&nbsp;(<%
				UserBean user = (UserBean)session.getAttribute("loginUser");
				if(user != null) {
					out.print(user.getUsername() + ", " + user.getUserId());
				}else {
					out.println("未登录");
				}
			%>
			)<br/><br/>
	
	<h3>无需登录</h3>
	<a href="insertuser.jsp">注册</a> &nbsp;&nbsp;&nbsp;
	<a href="login.jsp">登录</a> &nbsp;&nbsp;&nbsp;
	
	<br/><br/>
	<h3>需要登录</h3>
	<a href="user/queryuser.jsp">查询用户信息</a> &nbsp;&nbsp;&nbsp; 
	<a href="user/updateuser.jsp">修改用户信息</a> &nbsp;&nbsp;&nbsp;
	<a href="user/logout.jsp">注销用户</a> &nbsp;&nbsp;&nbsp;
	
	<br/><br/>
	<h3>移动界面</h3>
	<a href="mobile/index.html">test.html</a>
	
</body>
</html>