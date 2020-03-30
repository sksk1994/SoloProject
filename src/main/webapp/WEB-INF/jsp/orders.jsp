<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <%@ include file="header.jsp"%>
  <link rel="stylesheet" type="text/css" href="resources/MyStyle.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Orders</h1>
	
		<TABLE class ="table">
		<tr>
		<th>Order ID</th>
		<th>Cost</th>
		<th>Username</th>	
		<th>Date</th>
		</tr>	
		
		<c:forEach items="${ordersList}" var="orderList">
		
		<tr>
		<td>${orderList.orderID}</td>
		<td>${orderList.cost}</td>
		<td>${orderList.user.username }</td>
		<td>${orderList.orderDate }</td>
		
		</tr>
		
	</c:forEach>
		</TABLE>
		
		
		</form>
</body>
</html>