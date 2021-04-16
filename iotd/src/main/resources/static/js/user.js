function checkUser(){
	var userName=$("#userName").val();
	var userPassword=$("#userPassword").val();
	$.ajax({
		type:'post',
		data:'userName='+userName+"&userPassword="+userPassword,
		url:'user/login.do',
		dataType:"json",
		success:function(result){
			if (result.state==0){
				  window.location.href="page/index.html";
			  }else{
				  alert(result.message);
			  }
		}
	});
}
function addUser(){
	  var userName=$("#user").val();
	  var userPassword=$("#passwd").val();
	  var surePassword=$("#passwd2").val();
	  var userAge=$("#userAge").val();
	  var userSex=$("#userSex").val();
	  var userPhone=$("#userPhone").val();
	  $.ajax({
			type:'post',
			data:'userName='+userName+"&userPassword="+userPassword+"&surePassword="+surePassword+"&userAge="+userAge+"&userSex="+userSex+"&userPhone="+userPhone,
			url:'user/regist.do',
			success:function(result){
				if (result.state==0){
					  window.location.reload();
				  }else{
					  alert(result.message);
				  }
			}
		});
	}