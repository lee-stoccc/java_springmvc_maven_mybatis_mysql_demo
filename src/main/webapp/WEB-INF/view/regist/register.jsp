<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>register</title>
</head>
<body>
	<div style="text-align:center" id="login"> 登录</div>
	<div style="text-align:center">
		<span style="padding-right:20px">账号：</span><input type="text" id="userName">
	</div>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px">密码：</span><input type="text" id="password">
	</div>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px"></span><button id="submit"> 注册 </button>
	</div>
</body>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script>
	$('#submit').click(function(){
		var userName = $('#userName').val()
		var password = $('#password').val()
		
		$.ajax({
			type:"POST",
			url:"/stockmgt_maven_project/doRegister",
			data:{'userName':userName,"password":password},
			dataType:"",
			/* contentType:'application/json', */
			success:function(data){
				console.log("请求成功")
				console.log(data)
				switch(data.code){
					case '-9':
						alert('账号已存在')
						break;
					case '0':
						alert('注册失败');
						break;
					case '1':
						alert('注册成功')
						window.location.href = "/stockmgt_maven_project/registerSuccess"; 
						break;
				}
			}
		})
		console.log(userName)
		console.log(password)
		
	})
</script>
</html>