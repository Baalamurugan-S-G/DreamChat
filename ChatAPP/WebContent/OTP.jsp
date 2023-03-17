<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src=OTP.js></script>
<link rel="stylesheet" href="OTP.css" > 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OTP</title>
</head>
<body>
	<%
		if(session.getAttribute("uname")==null){
			response.sendRedirect("AddUserName.jsp");
			System.out.println("otp page"+session.getAttribute("name"));
		}
		
	%>
	<div class="container">
		<h1>ENTER OTP</h1>
		<h4>OTP Sent to:<br>
		<u><%=(session.getAttribute("mail")) %></u></h4>
		<div class="userInput" >
			<form action="otpverify" method="post">
			<input type="text" name='c1' id='c1' maxlength="1" onkeyup="clickEvent(this,'c2')">
			<input type="text" name='c2' id="c2" maxlength="1" onkeyup="clickEvent(this,'c3')">
			<input type="text" name='c3' id="c3" maxlength="1" onkeyup="clickEvent(this,'c4')">
			<input type="text" name='c4' id="c4" maxlength="1">
		</div><button class="button2">CONFIRM</button></form>
		<form action="validate" method="post">
		<button class="button button1">ResendOTP</button>
		</form>
	</div>
	<script type="text/javascript" >     history.forward();   </script>
</body>
</html>