<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=0" />
<title>Insert title here</title>
</head>
<body>
	插入一条用户记录<br/>
	
	<form action="insertuser" method="POST">
		username:<input type="text" name="username"> <br/>
		password:<input type="text" name="password"> <br/>
		platform:<input type="text" name="platform"> <br/>
		userNick:<input type="text" name="userNick"> <br/>
		userIcon:<input type="text" name="userIcon"> <br/>
		userEmail:<input type="text" name="userEmail"> <br/>
		userPhone:<input type="text" name="userPhone"> <br/>
		userSex:<input type="text" name="userSex"> <br/>
		<input type="submit" value="提交"> <br/>
	</form>
	
</body>
</html>