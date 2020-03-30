<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/header.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<header class="header-fixed">

	<div class="header-limiter">

		<h1>Aux<span><img src="${pageContext.request.contextPath}/resources/logo.PNG" class="headerlogo" alt=""/></span></a></h1>

		<nav>
		
			<a href="${pageContext.request.contextPath}/homeScreen">Home</a>
			<a href="${pageContext.request.contextPath}/accountDetails">Account Details</a>
			<a href="${pageContext.request.contextPath}/displayBasketItems">Basket</a>
			<a href="${pageContext.request.contextPath}/listOrders">Orders</a>
			
			<a href="${pageContext.request.contextPath}/signOut">Log Out</a>
		</nav>

	</div>
	


</header>

<footer class="footer">
	<div >
  		<p>Footer</p>
	</div>
</footer>
</html>

