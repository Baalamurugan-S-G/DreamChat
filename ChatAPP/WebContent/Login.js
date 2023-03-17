/**
 * 
 */

setTimeout(hide_div(), 2000);

function hide_div(){
	var element=document.getElementById("message");
	element.remove();
}

function checkUname(){
	var u = document.getElementById("uname").value;
	if(u == "") {  
	     document.getElementById("message1").innerHTML = "**Fill the password please!";  
	     return false;  
	 }
	else{
		document.getElementById("message1").innerHTML = "";  
	     return false;
	}
}

function checkPwd(){
	var u = document.getElementById("pwd").value;
	if(u == "") {  
	     document.getElementById("message2").innerHTML = "**Fill the password please!";  
	     return false;  
	  }
	else{
	document.getElementById("message2").innerHTML = "";  
    return false;
	}
}