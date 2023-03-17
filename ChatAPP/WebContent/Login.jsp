<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src=Login.js></script>
<link rel="stylesheet" href="Login.css" > 
<title>CHAT APP</title>
</head>
<body>
<div id='body'>
	<%
		if(session!=null && session.getAttribute("name")!=null){
			response.sendRedirect("login");
		}
	%>

	<div class="login">
	<form action="login" method="post" >
		<h1>Dream Chat</h1>
		<input type="text" id="uname" name="uname" placeholder="Username" oninput="checkUname()" required><br/>
		<span id = "message1" style="color:black"> </span> <br>
		<input type="password" id="pwd" name="pwd" placeholder="Password" oninput="checkPwd()" required><br/>
		<span id = "message2" style="color:black"> </span> <br><br>
		<!-- <a href="ForgotPassword.jsp">Forget Password??</a> --><br>
		<input type="submit" value="Login"><br>
		New User?<a href="AddUser.jsp">Sign Up</a>
		<script type="text/javascript" >     history.forward();   </script>
	</form>
	</div>
</div>
</body>
</html>