$(function(){
	

	function bottomdiv(){
		console.log("bottomdiv");
		var element = document.getElementById('chats');
		element.scrollTop = element.scrollHeight;
	}
	onload();
	getnewMsg();
	
	
	
	
	
	var callrepeat=window.setInterval(function(){
		checkMsg(onload,getnewMsg);
	},10000);
	
	function checkMsg(callback1,callback2){
		console.log("checkMsg");
		var f;
		$.ajax({
			url     : 'api/chkmsg',
			method     : 'POST',
			success    : function(resultText){
				console.log("v=",resultText[0]);
				f=resultText[0];
				if (f=="0") return;
				callback1();
				callback2();
			},
			error : function(jqXHR, exception){
			console.log('Error occured!!');
			}
			});
	}

	function getnewMsg(){
		$.ajax({
			url     : 'api/getnewmsg',
			method     : 'POST',
			success    : function(resultText){
				console.log(resultText);
				update_rec(JSON.parse(resultText));
			},
			error : function(jqXHR, exception){
			console.log('Error occured!!');
			}
			});
	}

	function contactUpdates(contacts){
		for(var i=0;i<contacts.length;i++){
			$('.col1 #contacts #'+contacts[i].cname).html("&nbsp;"+contacts[i].no+"&nbsp;");
		}
	}
	
	function incrementCount(arr, contact) {
		  for (var i = 0; i < content.length; i++) {
		    if (arr[i]["contact"] === contact){
		    	arr[i]["count"]++;
		    } 
		  }
		  
		}
	
	function getcount(arr, contact) {
		  for (var i = 0; i < content.length; i++) {
		    if (arr[i]["contact"] === contact){
		    	return arr[i]["count"];
		    } 
		  }
		  
		}
	
	function updatecount(arr, contact) {
		  for (var i = 0; i < content.length; i++) {
		    if (arr[i]["contact"] === contact){
		    	arr[i]["count"]+=15;
		    } 
		  }
		  
		}
	
	function addMessageLast(arr, contact, chats) {
		  for (var i = 0; i < content.length; i++) {
		    if (arr[i]["contact"] === contact){
		    	arr[i]["chats"]=arr[i]["chats"].concat(chats);
		    	return arr[i]["chats"];
		    } 
		  }
		  
		}
	
	function addMessageFirst(arr, contact, chats) {
		  for (var i = 0; i < content.length; i++) {
		    if (arr[i]["contact"] === contact){
		    	arr[i]["chats"]=chats.concat(arr[i]["chats"]);
		    	return arr[i]["chats"];
		    } 
		  }
		  
		}
		
	
	function contains(arr, key, val) {
		  for (var i = 0; i < content.length; i++) {
		    if (arr[i][key] === val) return true;
		  }
		  return false;
		}
	
	var content=[];

	$('.col1 #show-imp a').click(function(){
		$(".col2 #content .banner").hide();
		$('.col2 .new').hide();
		selectname=$(this).attr('id');
		var newMsgFlag=$('.col1 #show-imp #'+selectname+' .cnt').text();
		checkPath(content,selectname,newMsgFlag);
		postPathway();		
	});
	
//	$('.col1 #namelist a').click(function(){
//		alert("  ");//$(this).val());
//	});
	
	$(document).on('click', '.col1 #namelist a', function() {
		var name=$(this).text();
		$('.col1 #newchat').val('');
		$('.col1 #namelist').html('');
		if(!(name==="No results")){
			selectname=name;
			var element=$('.col1 #contacts #'+selectname);
			if($(element).length>0){
				var pos=$(element).parent().attr('id');
				if(!(pos==='read')){
					$('.col1 #contacts #read').prepend($(this));
					$(this).find("ul").removeClass('imp');	
				}
				$(".col2 #content .banner").hide();
				$('.col2 .new').hide();
				var newMsgFlag=$('.col1 #contacts #'+selectname+' .cnt').text();
				checkPath(content,selectname,newMsgFlag);
				postPathway();
			}
			else{
				console.log("no");
				createContact(selectname);
				$(".col2 #content .banner").hide();
				$('.col2 .new').hide();
				update("Your Mesage","--:--",selectname," ",1);
				postPathway();
			}
		}
	});
	
//	clearInterval(callrepeat);
	var selectname,number;
	$('.col1 #contacts a').click(function(){
		var pos=$(this).parent().attr('id');
		console.log("HEY");
		if(!(pos==='read')){
			$('.col1 #contacts #read').prepend($(this));
//			if($(this).find("ul").attr('class')==="show-imp"){
			$(this).find("ul").removeClass('imp');	
//			}
		}
		$(".col2 #content .banner").hide();
		$('.col2 .new').hide();
		selectname=$(this).attr('id');
		var newMsgFlag=$('.col1 #contacts #'+selectname+' .cnt').text();
		checkPath(content,selectname,newMsgFlag);
		postPathway();
//		if(contains(content,"contact",selectname)){ 
//			if(newMsgFlag>0  && true){
//				recievedMessage();
////				alert("new message");
//			}
//			else{
//				$('.col2 #content #chats').html("");
//				var chats=addMessageLast(content,selectname,[]);
//				dispChats(chats,selectname,newMsgFlag);
//				console.log("contains condition ",selectname);		
//				}
//		}
//		else{
//			var obj={};
//			obj["contact"]=selectname;
//			obj["count"]=0;
//			obj["chats"]=[];
//			content.push(obj);
////			console.log(content);		
//			$('.col2 #content #chats').html("");
//		
//		
//		console.log("HLO");
//		console.log(selectname,newMsgFlag);
//		number=0;
//		messages(number);
//		}
		
	});
	
	
	function postPathway(){
		$('.col1 #contacts #'+selectname+' .cnt').html('');
		$('.col2 #content .msghead #name').html(selectname);
		$('.col2 #content').hide(300).fadeIn();
		$('.col2 #content .MsgEntry .msg').focus();
	}
	
	function checkPath(content,selectname,newMsgFlag){
		if(contains(content,"contact",selectname)){ 
			if(newMsgFlag>0  && true){
				recievedMessage();
//				alert("new message");
			}
			else{
				$('.col2 #content #chats').html("");
				var chats=addMessageLast(content,selectname,[]);
				dispChats(chats,selectname,newMsgFlag);
				console.log("contains condition ",selectname);		
				}
		}
		else{
			var obj={};
			obj["contact"]=selectname;
			obj["count"]=0;
			obj["chats"]=[];
			content.push(obj);
//			console.log(content);		
			$('.col2 #content #chats').html("");
		
		
		console.log("HLO");
		console.log(selectname,newMsgFlag);
		number=0;
		messages(number);
		}
	}
	
	
	$('.col2 #content .new').click(function(){
		$(this).hide();
		$('.col1 #contacts #'+selectname+' .cnt').html('');
//		number=0;
		$('.col1 #contacts #read').prepend($('.col1 #contacts #'+selectname));
		recievedMessage();
		})
	
	$(".col2 #chats").scroll(function(){
	var pos = $(this).scrollTop();
	console.log("height"+$(".col2 #content #chats").height());
	console.log("top"+pos);
    if (pos < 1) {
    	$(".col2 #content #chats .loader").remove();
        $(".col2 #content #chats").prepend("<div class='loader'></div>");
        console.log("Reload");
        messages(getcount(content,selectname),-1);
        $(".col2 #content #chats .loader").remove();
        
    }
    
	});
	
	function recievedMessage(){
		$.ajax({
			url      : 'api/rec_msgs',
			method   : 'POST',
			data     : {rec : selectname},
			success    : function(resultText){
				console.log(resultText);
				var resultTex=JSON.parse(resultText);
				dispRecievedMsgs(resultTex);
//				changeContent(addMessageFirst(content,selectname , []));	
//				changeContent(resultTex);
//				var chats=addMessageLast(content,selectname,resultTex);	
//				updatecount(content,selectname);
//				dispChats(resultTex,selectname,newMsgFlag);
			},
			error : function(jqXHR, exception){
			console.log(exception);
			}
			});
		
	}
	
	function messages(n,newMsgFlag){
		$.ajax({
			url      : 'api/msgs',
			method   : 'POST',
			data     : {rec : selectname,count:n},
			success    : function(resultText){
				var resultTex=JSON.parse(resultText);
				var chats=addMessageLast(content,selectname,resultTex);	
				updatecount(content,selectname);
				dispChats(resultTex,selectname,newMsgFlag);
			},
			error : function(jqXHR, exception){
			console.log(exception);
			}
			});
	}
	
	
	function dispChats(chats,name,newMsgFlag){
		if($(".col2 #content #chats .date span").length>0)
		{
			checkFirstDate(chats[0].date);
		}
		var i;
		for(i=0;i<chats.length;i++){
			var chat;
			var msg=chats[i].msg;
			var date=chats[i].date;	
			var time=chats[i].time;
			var type=chats[i].type;
			if (type=="sender"){
				chat="<div class='mes'><ul class='rm'>";
			}
			else{
				chat="<div class='mes'><ul class='lm'>";
			}
			chat+="<li class='m'><pre>"+msg+"</pre></li><li class='t'>"+time+"</li></ul></div>";

			$('.col2 #content #chats').prepend(chat);
			
			if(i<chats.length-1){
				var dateNext=chats[i+1].date;		
				if(dateNext!=date){
					addDateFirst(date);
				}
			}
			else addDateFirst(date);
		}i--;
		if(newMsgFlag>0)
			update(chats[i].msg,chats[i].time,name,type,1);
		if(newMsgFlag!=-1)
			bottomdiv();
	}
	
	
	function addDateFirst(date){
		$('.col2 #content #chats').prepend("<div class='date'><span>"+date+"</span></div>");
	}
	function addDateLast(date){
		$('.col2 #content #chats').append("<div class='date'><span>"+date+"</span></div>");
	}
	
	
	
	

	
	$('.col1 #newchat').on('keyup',function(){
		var search=$(this).val();
		if(search!=''){
			$.ajax({
				url:"api/namelist",
				method:'POST',
				data:{search:search},
				success : function(resultText){
					$('.col1 #namelist').html(resultText);
				},
				error : function(jqXHR, exception){
					console.log(exception);
				}
			});
		}
		else{
			$('.col1 #namelist').html('');
		}
	});

		
	
	
	
	
	
	
	



//function cons(){
//$.ajax({
//	url     : 'api/contact',
//	method     : 'POST',
//	data     : {rec : ""},
//	success    : function(resultText){
//	$('.col1 #contacts').append(resultText);
//	},
//	error : function(jqXHR, exception){
//	console.log('Error occured!!');
//	}
//});
//}

//To send Message on clicking Button
$('.col2 #content .MsgEntry button').click(function(){
	var txt=$('.col2 #content .MsgEntry .msg').val();
	var textFlag=containsText(txt);
	$('.col2 input').val('');
	if(txt.length!=0 && textFlag){
		txt=ValidateText(txt,checkCondition);
		
	}
});

$('.col2 #content .MsgEntry .msg').keypress(function(event){
		
	var keycode=event.keyCode;
	if(keycode==13){
		var txt=$(this).val();
		var textFlag=containsText(txt);
		$(this).val('');
		if(txt.length>0 && textFlag){
			txt=ValidateText(txt,checkCondition);
		}
		
	}	
});

function containsText(str) {
	 for (var i in str) {
	    if (str[i] == " ")
	      continue;
	    else 
	    	return true;
	 }	  
	 return false;
	}

function ValidateText(txt,callback){
	if(txt.includes("&")){
		txt=txt.replace(/&/g,"&#38");
	}
	if(txt.includes("<")){
		txt=txt.replace(/</g,"&lt");
	}
	if(txt.includes(">")){
		txt=txt.replace(/>/g,"&gt");
	}
	callback(txt,send);
}

function checkCondition(txt,callback){
	if(txt.includes(":-}")){
		txt=txt.replace(":-}","<i class='material-icons s'>star</i>")
	}
	callback(txt);
}

function checkFirstDate(date){
	var firstDate = $('.col2 #content #chats .date').first().find('span').text();
	if(date==firstDate){
		$('.col2 #content #chats .date').first().remove();
	}
}	


function checkLastDate(){
	var currentDate=getCurrentDate();
	var lastDate = $('.col2 #content #chats .date').last().find('span').text();
	if(currentDate!=lastDate){
		addDateLast(currentDate);		
	}
}

function checkWithLastDate(date){
	var lastDate = $('.col2 #content #chats .date').last().find('span').text();
	if(date!=lastDate){
		addDateLast(date);		
	}
}

function getCurrentDate(){
	var fullDate = new Date();
//	alert(fullDate.getDate()>9);
	var twoDigitDay= (fullDate.getDate()<10)?("0"+fullDate.getDate()):(fullDate.getDate())
	var twoDigitMonth = (fullDate.getMonth()<9)? ("0" +(fullDate.getMonth()+1)) : (fullDate.getMonth()+1);
	return fullDate.getFullYear() + "-" + twoDigitMonth + "-" + twoDigitDay  ;
}



//Store date of message sent in-to DB And Display
function send(txt){
	$.ajax({
		url     : 'api/newmsg',
		method     : 'POST',
		data     : {rec : selectname, msg : txt},
		success    : function(resultText){
			dispOne(JSON.parse(resultText),1);			
			addMessageFirst(content,selectname,JSON.parse(resultText));
		},
		error : function(jqXHR, exception){
		console.log('Error occured!!');
		}
		});
}
//To Display message sent at a time
function dispOne(message,PrependFlag){
	var chat;
	var msg=message[0].msg;
	var date=message[0].date;
	checkWithLastDate(date);
	var time=message[0].time;
	var type=message[0].type;
	if (type=="sender"){
		chat="<div class='mes'><ul class='rm'>";
	}
	else{
		chat="<div class='mes'><ul class='lm'>";
	}
	chat+="<li class='m'><pre>"+msg+"</pre></li><li class='t'>"+time+"</li></ul></div>";
	$('.col2 #content #chats').append(chat);
	incrementCount(selectname);
	update(msg,time,selectname,type,PrependFlag);
	console.log($(".col2 #content #chats").innerheight-$(".col2 #content #chats").scrollTop());
	if($(".col2 #content #chats").height()==$(".col2 #content #chats").scrollHeight -$(".col2 #content #chats").offsetHeight ){
		bottomdiv();
		console.log("calling Bottomdiv  ");
	}
}

function dispRecievedMsgs(chats){
	for(var i=0;i<chats.length;i++){
		var chat=[];
		chat=chat.concat(chats[i])
		dispOne(chat,0);
		addMessageFirst(content,selectname,chat);
		console.log(chat[0].msg);
		
	}
}

function checkImp(chat){
	if(chat.includes("material-icons s")){
		return 1;
	}
	else return 0;
}

function changeContent(lastMsg){
	console.log(lastMsg);
}

//Display updated contact on sending a message
function update(msg,time,name,type,PrependFlag){
	var path=$('.col1 #contacts #'+name);
	if(type=="sender") msg="<i class='material-icons icons'>done</i>"+msg;
	$(path).find('#m').html(msg);
	$(path).find('#d').html(time);
//	var contact="<a id='"+cname+"'><ul class='info'><li class='name'>"+cname+"</li><li class='msg'><table><tr><td id='m'>"+msg+"</td><td id='d'>"+time+"</td></tr></table></li></ul></a>";
	if(PrependFlag==1)
//	$('.col1 #contacts').prepend($('.col1 #contacts #'+name));
	$(path).parent().prepend($(path));
	console.log("Prepend");
}


function update_rec(contact){
	for(i=0;i<contact.length;i++){
		var path=$('.col1 #contacts #'+contact[i].cname)
			$(path).find('.cnt').html("&nbsp;"+contact[i].no+"&nbsp;");
			$(path).parent().prepend($(path));
			if(selectname==contact[i].cname){
				$('.col2 .new .cnt').html("&nbsp;"+contact[i].no+"&nbsp;");
				$('.col2 .new').show();
			}
		
	}
}












//invoke during refresh to display contacts
function onload(){
	console.log("onload");
	$.ajax({
		url     : 'api/onload',
		method     : 'POST',
		success    : function(resultText){
			dispContact(JSON.parse(resultText));
			console.log(resultText);
			console.log("onload ajax");
			
		},
		error : function(jqXHR, exception){
		console.log('Error occured!!');
		}
		});
//	callback();

}

//DO Display Message-d Contact with last message & time
function dispContact(contacts){
	console.log(contacts);
	for(var i=0;i<contacts.length;i++){
		cname=contacts[i].cname;
		console.log(cname);
		msg=contacts[i].msg;
		time=contacts[i].time;
		var path=$('.col1 #contacts #'+cname);
		$(path).find('#m').html(msg);
		$(path).find('#d').html(time);
		var pos=$(path).parent().attr('id');
		var impFlag=checkImp(contacts[i].msg);
		
		if(pos==='show-imp'){
		}
		else if(impFlag==1){
			$('.col1 #contacts #show-imp').append($(path));
			$(".col1 #show-imp #"+cname+" ul").addClass("imp");
		}
		else if(pos==='read'){
			console.log("pos:read");
			$('.col1 #contacts #unread').prepend($(path));
		}
		else if(pos==='unread'){
			console.log("pos:unread");
			$(path).parent().prepend($(path));
		}
		
		
		
	
	}
}

function createContact(name){
	var newcontact="<a id="+name+"><ul class='info'><li class='name'>"+name+"<spam class='cnt'></spam></li><li class='msg'><table><tbody><tr><td id='m'></i>knd</td><td id='d'></td></tr></tbody></table></li></ul></a>";
	$(".col1 #contacts #read").prepend(newcontact);
}


});