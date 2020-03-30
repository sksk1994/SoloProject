<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%@ include file="header.jsp"%>
<link rel="stylesheet" type="text/css" href="resources/MyStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>

	<h1>Update Details</h1>
	<form action="updateUser" method="post" class = "container, leftcontainer">

		First name: <br> 
		<input type="text" name="firstName"	value=""> <br> 
		Last name: 
		<br>	
		<input type="text" name="lastName" value="">
		<br> 
		Username: 
		<br> 
		<input type="text" name="username" value="${username}" readonly> 
		<br> Password: <br> 
		<input type="text" name="password" value=""> 
		<br>
		Confirm Password: <br> 
		<input type="text" name="passwordConfirmation" value=""> 
		<br> Email Address: <br> 
		<input type="text" name="email"	value=""> <br> 
		<input type="submit" value="Update Details">


	</form>
	
	<div class ="container, rightcontainer">
		<h1>Add Bank Account</h1>

		<form action="addBankAccount" method="post">

				Bank Account Number: <br> 
				<input type="number" minlength="8" maxlength="8" name="bankaccountnumber"  value="">
				<br> Amount: <br> 
				<input type="number" name="balance" value=""> <br> 
				<input type="hidden" name="user" value=""> <br> 
				
				<input type="submit" value="Submit"> 
			
				<span style="background: red">
				
				<span style="background: red">
	        ${bankexists}
		 </span> 
			      
				 </span>
		</form>
	</div>
	
		<div class ="container, rightcontainer">
		<h1>Remove Bank Account</h1>

		<form action="removeBankAccount" method="post">

				Bank Account Number: <br> 
				<input type="text" name="bankaccountnumber"  minlength="8" maxlength="8" value="">		
				<input type="submit" value="Submit"> 

		</form>
	</div>
</body>
</html>