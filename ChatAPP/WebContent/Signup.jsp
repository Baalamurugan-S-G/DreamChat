<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="Login.css" > 
<title>Email Entry</title>
</head>
<body>
	<%
		if(session.getAttribute("uname")==null){
			response.sendRedirect("AddUserName.jsp");
		}
	%>
	<div class="login">
	<form action="validate" method="post" >
		<h1>User: <%=(session.getAttribute("uname")) %>
		</h1><br><br>
				
		
		<input type="email" name="mail" placeholder="Enter Email" required><br/>
		<input type="submit" value="Next"><br>
		
		
	</form>
	</div>
	
	
	 <script type="text/javascript" >     history.forward();   </script>  
</body>
</html>