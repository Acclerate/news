<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="auth" method="post">
	<input type="hidden" name="redirect" value="/">
	<input type="number" name="uid" placeholder="用户名id"></input>
	<br>
	<input type="password" name="pwd" placeholder="密码"></input>
	<br>
	<input type="submit" value="登录">
</form>
</body>
</html>