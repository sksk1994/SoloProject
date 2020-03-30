<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/MyStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aux</title>
</head>
<body>
	<h1>Signed in</h1>

	<p>Welcome ${username}</p>

	<div class="signedinbar">
	
		<form class="form" action="${pageContext.request.contextPath}/listProducts" method="get">
			<button class="button" type="submit">List Products</button>
		</form>

	</div>




	<c:forEach items="${ulist}" var="element">
		Username: ${element.username}
		Email: ${element.email}
		</br>
	</c:forEach>
	
		

	<c:forEach items="${productList}" var="eachProduct">		
		<div>
			<form class="form" action="${pageContext.request.contextPath}/addToBasket/${eachProduct.productID}" method="post">
		 		<p class="prodName">
					 ${eachProduct.name}
				</p> 
				<a class="purchasedetails"><img src="${pageContext.request.contextPath}/resources/${eachProduct.productID}.jpg" alt="" /></a> 
				</br><a class="purchasedetails">Cost: </a>
				<a class="purchasedetails">${eachProduct.price}</a> 
				<a class="purchasedetails">Quantity: </a>
				<input class="purchasedetails" type="number" name="quantity" value="">
				<input type="hidden" name="productID" value="${eachProduct.productID}">
				<button class="button" value="${eachProduct.productID}" type="submit">Add to basket</button>
			</form>
			
			<form class="remove" action="${pageContext.request.contextPath}/removeFromBasket/${eachProduct.productID}" method="post">
				<input type="hidden" name="productID" value="${eachProduct.productID}">
				<button class="button" value="${eachProduct.productID}" type="submit">Remove from basket</button>
			</form>
			
			
		</div>
	</c:forEach>
	
		</br>
		</br>
		</br>
		</br>
		

</body>
</html>