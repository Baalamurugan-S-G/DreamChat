<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Login.css" > 
<script src="pass.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Input Password</title>
</head>
<body>
	<%
		if(session.getAttribute("uname")==null){
			response.sendRedirect("AddUserName.jsp");
		}
	%>
	<div class="login">
	<form action="pass" method="post" >
		<h1>User: <%=(session.getAttribute("uname")) %>
		</h1>
		
		<input type="password" id="crtpwd" name="crtpwd" placeholder="Create Password" oninput="verifyPassword()" required><br/>
		<span id = "message1" style="color:black"> </span> <br>
		<input type="password" id="conpwd" name="conpwd" placeholder="Confirm Password" oninput="checkPassword()" required><br/>
		<span id = "message2" style="color:black"> </span> <br>
		
		<input type="submit" value="Save Password"><br>
		
		
	</form>
	</div>
	<script type="text/javascript" >     history.forward();   </script>
</body>
</html>