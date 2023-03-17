$(function(){
//	debugger;
	
//	$( "#chats" ).sortable();
	
	
	var callrepeat=window.setInterval(function(){
		checkSchedule(UpdateMsgFlag,UpdateScheduleFlag);
	},60000);
	
	
	
	$(".col2 #content .banner #senddate").attr({
//		"value":'2001-01-01',
//		"min":'2001-01-01'
	});
	
	$(".col2 #content .msghead .schedule").click(function(){
		$(".col2 #content .banner").toggle();
		
		if($(".col2 #content .banner").is(":visible"))	
			$(".col2 #content .banner #message").focus();
		else
			$(".col2 .MsgEntry input").focus();
	});
	
	$(".col2 #content .banner #head #close").click(function(){
		$(".col2 #content .banner").hide();
		$(".col2 .MsgEntry input").focus();
	});

	$(".col2 #content .banner #cancel").click(function(){
		$(".col2 #content .banner").hide();
		$(".col2 .MsgEntry input").focus();
	});
	
	$(".col2 #content .banner #send").click(function(){
		var cname=$(".col2 #content .msghead #name").text();
		
		var message=$(this).siblings("#message").val();
		var textFlag=containsText(message);
		var message_len=message.length;
		
		var senddate=$(this).siblings("#senddate").val();
		var senddate_len=senddate.length;
		
		var sendtime=$(this).siblings("#sendtime").val();
		var sendtime_len=sendtime.length;
		
		if(message_len>0 && senddate_len==10 && sendtime_len==5 && textFlag ){
			console.log("Ok For Schedule");
			message=ValidateText(message)
			message=checkCondition(message);
			schedule(cname,message,senddate,sendtime);
		}
		else{
			showError("valid");
		}
	});
	
	function clearData(){
		$(".col2 #content .banner #message").val('');
		$(".col2 #content .banner #senddate").val('');
		$(".col2 #content .banner #sendtime").val('');
	}
	
	function schedule(cname,message,senddate,sendtime){
		console.log("Schedule Message");
		$.ajax({
			url     : 'api/schedule',
			method     : 'POST',
			data     : {rec : cname,msg : message,date : senddate,time : sendtime},
			success    : function(resultText){
				if(resultText.includes("false")){
					console.log("Schedule Fails");
					showError("fail");
				}
				else{
					console.log("Schedule Sucess");
					showError("success");
					clearData();
				}
			},
			error : function(jqXHR, exception){
			console.log('Error occured!!');
			}
		});
	}
	
	function checkSchedule(callback1,callback2){
		$.ajax({
			url     : 'api/checkschedule',
			method     : 'POST',
			success    : function(resultText){
				if(resultText.includes("false")){
					console.log("No Schedule");
				}
				else{
					console.log("Contains Schedule");
					callback1();
					callback2();
					
				}
			},
			error : function(jqXHR, exception){
			console.log('Error occured!!');
			}
		});
	}
	
	function showError(type){
		$(".col2 #content .banner ."+type).show("slow").delay(5000).fadeOut("slow");
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
		return txt;
	}
	function checkCondition(txt){
		if(txt.includes(":-}")){
			txt=txt.replace(":-}","<i class='material-icons s'>star</i>")
		}
		return txt;
	}
	
	
	
	function UpdateMsgFlag(){
		$.ajax({
			url     : 'api/update_msg_flag',
			method     : 'POST',
			success    : function(resultText){
				
			},
			error : function(jqXHR, exception){
			console.log('Error occured!!');
			}
		});
	}	
	
	function UpdateScheduleFlag(){
		$.ajax({
			url     : 'api/update_schedule_flag',
			method     : 'POST',
			success    : function(resultText){
				
			},
			error : function(jqXHR, exception){
			console.log('Error occured!!');
			}
		});
	}
	
	
	$(".col1 .initial").click(function(){
		$('.col1 #newchat').focus();
		$('.col1 .input-icons .inf').show("slow").delay(5000).fadeOut("slow");
		$(this).fadeOut(4000);
	});
	
	$(".col2 #content .msghead .infor").hover(function(){
			$('.col2 #content .msghead .hang').show();
	    }, function(){
			$('.col2 #content .msghead .hang').hide();
	  });
	
	$(".topnav .user").click(function(){
		$('.topnav .logout').show("slow").delay(5000).fadeOut("slow");
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
	
});

$( document ).ready(function(){
	if($(".col1 #contacts a").length==0){
		$(".col1 .initial").fadeIn(300);
	}
});