<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
 <%@ include file="header.jsp"%>
  <link rel="stylesheet" type="text/css" href="resources/MyStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 
 <h1>Basket</h1>
  

  <form action="displayBasketItems" method="get">
 		<table class="table">
 		<tr>
		<th>  Product Name </th>
		<th>  Quantity     </th> 
		</tr>
 
  		<c:forEach var="basketitems" items="${basket}">
  </form>
 
  
			<tr>
			<td> <c:out value="${basketitems.key.name}" /> </td>
			<td> <c:out value="${basketitems.value}" /> </td>
			
			</tr>

  		</c:forEach>
			</table>
  			
 	
  			
			</br>
		
    <form action="basketCost" method="get">
  	 Total price: ${price }
    </form>

  
  <form action="orderRedirect" method="post">
  <input type="submit" name="purchase" value="Purchase"> 
  </form>
		
 
		
	


	


</body>
</html>