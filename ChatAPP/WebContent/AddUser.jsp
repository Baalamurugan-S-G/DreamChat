<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Login.css" > 
<script src=Login.js></script>
<script src=pass.js></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignUp</title>
</head>
<body>
<div id='body'>	
	<div class="login">
	<form action="validate" method="post" >
		<h1>Sign Up</h1>
		<input type="text" name="uname" placeholder="Create Username" required><br/>
		<input type="email" name="mail" placeholder="Enter Email" required><br/>
		<input type="password" id="crtpwd" name="crtpwd" placeholder="Create Password" oninput="verifyPassword()" required><br/>
		<span id = "message1" style="color:black"> </span> <br>
		<input type="password" id="conpwd" name="conpwd" placeholder="Confirm Password" oninput="checkPassword()" required><br/>
		<span id = "message2" style="color:black"> </span> <br>
		<input type="submit" value="Next"><br>
		Existing User?<a href="Login.jsp">Log IN</a>
		
	</form>
	</div>
	<!-- <script type="text/javascript" >     history.forward();   </script>-->
	</div>
</body>

</html>