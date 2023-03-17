<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.sql.Date" %>
    <%@page import="java.util.*"%>
    <%@page import="com.user.Messages"%> 
    <%@page import="java.time.format.DateTimeFormatter"%>
    <%@page import="java.time.LocalDateTime " %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="Nav.css">
<link rel="stylesheet" href="split.css" >
<link rel="stylesheet" href="Message.css" >
<link rel="stylesheet" href="Contact.css" >
<link rel="stylesheet" href="Banner.css" >
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="MsgLogic.js"></script>
<script type="text/javascript" src="TimedMsg.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messsage</title>
</head>
<body>
	<%
		if(session==null || session.getAttribute("name")==null){
			response.sendRedirect("Login.jsp");
		}
	%>
	
	
	<div id='body'>
<div class="body">
<div class="topnav" id="myTopnav">
  <a href="#title" class="left">Dream Chat</a>
  <input type="text" name="search" id="search" placeholder="search">
  <a href="#me" class="right user"><%out.println(session.getAttribute("name")); %></a>
  <a href="#home" class="right"><i class="material-icons s">person</i></a>
  <a href="#news" class="right"><i class="material-icons s">favorite</i></a>
  <a href="#contact" class="right"><i class="material-icons s">add</i></a>
  <a href="#message" class="right"><i class="material-icons s" style="color:#ADD8E6">cloud </i></a>
  <a href="#about" class="right"><i class="material-icons s">home</i></a><br><br>
  <i class="logout" style = "cursor:pointer"><a href="Logout.jsp">logout</a></i>
  </a>
</div>


  <div class="sep col1">
  <div style = "cursor:pointer" class="initial"><i class="material-icons">add_comment</i><br>
  <span>Click Here to begin new chat</span>
  </div>
  <div class="input-icons">
  <input type="text" id="newchat" name="searchuser" placeholder="search"/>
  <i class="material-icons inp">search</i><br>
  <div class="inf">&nbspSearch Contacts Here&nbsp</div>
  </div>
  <div id='namelist'></div>
  <div id="contacts">
	<div id="show-imp"></div>
	<div id="unread"></div>
    <div id="read">
   
   <%Map<String,Messages> contacts=(HashMap<String,Messages>)session.getAttribute("Chats");
   for(Map.Entry<String,Messages> contact:contacts.entrySet()){
   Messages msg=(Messages)contact.getValue();%>
   <a id="<%=msg.getContact()%>"><ul class='info'>
   <li class="name"><%=msg.getContact() %> <spam class='cnt'></spam></li>
   <li class='msg'><table><tr><td id='m'><%=msg.getMessage()%></td>
   <td id='d'><%DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
   				LocalDateTime now = LocalDateTime.now();  
  				if(dtf.format(now).toString().equals(msg.getDate().toString()))
  					out.println(msg.getTime());
				else
					out.println(msg.getDate());%></td></tr></table></li>
   </ul></a><%} %>
   </div>
    </div>
  </div>

  <div class="sep col2">
  	<div id="content">
  	<div class="msghead">
  		<spam id="name">NAME</spam>
  		<a href="#" class="right infor"><i class="material-icons s">error_outline</i></a>
  		<a href="#" class="right schedule"><i class="material-icons s">schedule</i></a>
  		<!-- <a href="#" class="right find"><i class="material-icons s">search</i></a> -->
  		<input type='text' class='search' placeholder='search'>
  		<div class="searchmsg">a</div>
  		<span class="hang">Important Message - Use :-} before text</span>
  	</div>
  	<div class="banner">
		<div id="head">
			<a href="#"><span class="material-icons" id="close">disabled_by_default</span></a>
		</div>
		
		<textarea name="message" id="message" rows="5" cols="30" placeholder="Enter your message ..."></textarea>
		<input type="date" id="senddate" name="senddate">
	<!-- <input type="text" id="sendtime" name="sendtime" placeholder="hh:mm am/pm" pattern="[0-1][0-9]:[0-5][0-9] [a][p]"><br> -->
		 <input type="time" id="sendtime" name="sendtime" min="00:00:00" max="11-59"><br>
		 <span class="comment valid">Enter VaLID DaTA !!</span><span class="comment fail">Enter VaLID TiME !!</span><span class="comment success">MeSSAGE Scheduled!!</span><br>
		<button id="cancel">Cancel <i class="material-icons" style="color:#808080">clear</i></button>
		<button id="send">Send <i class="material-icons" style="color:#808080">send</i></button>
		
	</div>
  	
  	<div id="chats">
  	
  	</div>
  	<div class="new"><a><i class="material-icons s">expand_more</i><span class="cnt">&nbsp;0&nbsp;</span></a></div>
  	<div class="MsgEntry">
    	<input type="text" name="msg" id="msg" class="msg" placeholder="Enter Your Text ...">
    	<button ><i class="material-icons md-42" style="color:#EEFFEE">send</i></button>
    </div>
  </div>
  </div>
 </div>
 </div>
</body>
</html>