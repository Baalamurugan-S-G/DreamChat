$(function(){
	$.ajax({
			url     : 'api/contact',
			method     : 'POST',
			data     : {rec : ""},
			success    : function(resultText){
			$('#contacts').html(resultText);
			},
			error : function(jqXHR, exception){
			console.log('Error occured!!');
			}
	});
});