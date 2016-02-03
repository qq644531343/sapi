<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
		UserBean user = (UserBean) session.getAttribute("loginUser");
	%>

	请更改用户信息:
	<br />
	<form action="updateUser" method="POST">
		用户Id:<input type="text" name="userId" readonly="readonly"
			value=<%=user.getUserId()%>><br /> 用户名:<input type="text"
			name="username" readonly="readonly" value=<%=user.getUsername()%>><br />
		用户昵称:<input type="text" name="userNick" value=<%=user.getUserNick()%>><br />
		密码:<input type="text" name="password" value=<%=user.getPassword()%>><br />
		登录平台:<input type="text" name="platform" value=<%=user.getPlatform()%>><br />
		Email:<input type="text" name="userEmail"
			value=<%=user.getUserEmail()%>><br /> 头像:<input type="text"
			name="userIcon" style="width: 300px" width="250px"
			value=<%=user.getUserIcon()%>><br /> 手机号:<input type="text"
			name="userPhone" value=<%=user.getUserPhone()%>><br /> 性别:<input
			type="text" name="userSex" value=<%=user.getUserSex()%>><br />
		<input type="submit" value="提交">
	</form>

</body>
</html>