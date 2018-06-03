<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="seller" class="com.edu.beans.SellerVo" scope="request" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fruitSellerInformation.jsp</title>
</head>
<body>
	ApplePrice: <%= seller.getApple_price()%><br>
	Money: <%= seller.getMoney() %><br><br>
</body>
</html>