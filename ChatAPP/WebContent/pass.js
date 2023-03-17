/**
 * 
 */

console.log("Hi");
function verifyPassword() {  
  var pw = document.getElementById("crtpwd").value;  
  
  //check empty password field  
  if(pw == "") {  
     document.getElementById("message1").innerHTML = "**Fill the password please!";  
     return false;  
  }  
   
 //minimum password length validation  
  if(pw.length < 8) {  
     document.getElementById("message1").innerHTML = "**weak password ";  
     return false;  
  }  
  
//maximum length of password validation  
  if(pw.length > 15) {  
     document.getElementById("message1").innerHTML = "**too long";  
     return false;  
  } 
  if(pw.length >= 8 && pw.length <= 15) {  
	    document.getElementById("message1").innerHTML = "";  
	     return false;
  }
  
}

function checkPassword() {  
	  var crt = document.getElementById("crtpwd").value; 
	  var con = document.getElementById("conpwd").value;
//	  console.log("Hi");
//	  document.getElementById("message1").innerHTML = "";
	  if(crt.localeCompare(con) && con.length>3){
		  document.getElementById("message2").innerHTML = "**Password doesn't match";
	  }
	  else{
		  document.getElementById("message2").innerHTML = "";
	  }
}