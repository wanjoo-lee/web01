<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fruitBuyer.jsp</title>
</head>
<body>
	${error}
	<form action="buyerLogin.wj" method="post">
		LogInId: <input type="text" name="id"><br>
		Password: <input type="password" name="passwd"><br>
		<br>
		<input name="button" type="submit" value="LogIn"><br>
		<input name="button" type="submit" value="Join">
	</form>
</body>
</html>