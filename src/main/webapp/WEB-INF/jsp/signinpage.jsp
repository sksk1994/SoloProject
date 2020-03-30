
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<link rel="stylesheet" type="text/css" href="resources/MyStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aux</title>
</head>
<body class = "background">
	

	<div class="mainheader">
		<div>
			<a><img src="resources/logo.PNG" alt=""/></a> 
			<a>Aux</a>

		</div>
	</div>


	<div class="container">
		<form action="signIn" method="get">

			<p>Sign in </p> 
			Username:<input type="text" placeholder="Username" name="username" value="${uName}"/><br/> 
			Password: <input type="text" placeholder="Password" name="password" value="${pWord}"/> 
			<input type="submit" value="Sign In" class="button" /> 
			</br><span style="background: red"> ${loginError } </span>
		</form>
		
		<form action="register" method="get">
			<input type="submit" value="Register" class="button" />
		</form>

	</div>
	


	
</body>
</html>