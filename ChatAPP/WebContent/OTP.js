/**
 * 
 */
console.log("H");
function clickEvent(first,last){
	 	var a=parseInt(first.value);
		
		if(a>=0 && a<=9){
			if(first.value.length){
				document.getElementById(last).focus();
			}			
		}
		else{}
		document.getElementById(first).innerHTML = ""; 
			
		}