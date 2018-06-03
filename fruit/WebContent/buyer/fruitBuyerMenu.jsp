<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fruitBuyerMenu.jsp</title>
</head>
<body>
	<%
		if(session != null && session.getAttribute("buyerId") != null) {
			out.print(session.getAttribute("buyerId") + "님 환영합니다.<br>");
		}
		else {
			response.sendRedirect("fruitBuyer.jsp");
			//RequestDispatcher rd = request.getRequestDispatcher("fruitBuyer.jsp");
			//rd.forward(request, response);
		}
	%>	
	<a href="fruitBuyerRegisterMoney.jsp">RegisterMoney</a><br>
	<a href="fruitBuyerBuyFruit.jsp">BuyFruit</a><br>
	<a href="buyerInfo.wj">Information</a><br><br>
	<a href="buyerLogout.wj">LogOut</a>
</body>
</html>