<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="resources/MyStyle.css">

<title>Aux</title>
</head>
<body class = "background">
	

	<div class="mainheader">
		<div>
			<a><img src="resources/logo.PNG" alt=""/></a> 
			<a class ="compname">Aux</a>

		</div>
	</div>


	<div class="container">
		<form action="signIn" method="get">

			<p>Sign in </p> 
			Username:<input type="text" placeholder="Username" name="username"><br /> 
			Password: <input type="text" placeholder="Password" name="password"> 
			<input type="submit" value="Sign In" class="button" /> 
			<span style="background: red"> ${loginError } </span>
		</form>
		
		<form action="register" method="get">
			<input type="submit" value="Register" class="button" />
		</form>

	</div>



</body>
</html>