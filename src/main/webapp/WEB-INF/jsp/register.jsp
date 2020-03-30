<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="resources/MyStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class ="container">
		<h1>Register Here</h1>

		<form action="processUser" method="post">

				First name: <br> 
				<input type="text" name="firstName"  value="">
				<br> Last name: <br> 
				<input type="text" name="lastName" value=""> <br> 
				Username: <br> 
				<input type="text" name="username" value="${uName}"> <br> 
				Password: <br> 
				<input type="text" name="password" value=""> <br> 
				Confirm	Password: <br> 
				<input type="text" name="passwordConfirmation" value=""> 
				<br> Email Address: <br> 
				<input type="text" name="email" value=""> <br> 
				<input type="submit" value="Submit"> 
			
				<span style="background: red">
			        ${uError }
				 </span>
		</form>
	</div>
</body>
</html>