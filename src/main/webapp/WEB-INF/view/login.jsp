<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>XXX 系统登录</title>
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
		<span style="padding-right:20px"></span><button id="submit"> 登录 </button>
		<span style="padding-right:20px"></span><button id="register"> 注册 </button>
		<span style="padding-right:20px"></span><button id="delUser"> 销毁用户 </button>
	</div>
</body>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script>
	$('#submit').click(function(){
		var userName = $('#userName').val()
		var password = $('#password').val()
		
		$.ajax({
			type:"POST",
			url:"/stockmgt_maven_project/loginCheck",
			data:{'userName':userName,"password":password},
			dataType:"",
			/* contentType:'application/json', */
			success:function(data){
				console.log("请求成功")
				console.log(data)
				switch(data.code){
					case '0':
						alert('查无此账号，请注册！')
						break;
					case '2':
						alert('密码错误，请重新输入');
						break;
					case '1':
						window.location.href = data.url; 
						break;
				}
			}
		})
		console.log(userName)
		console.log(password)
	})
	
	$('#register').click(function(){
		window.location.href = "/stockmgt_maven_project/register"
	})
	
	$('#delUser').click(function(){
		var userName = $('#userName').val()
		$.ajax({
			url:"/stockmgt_maven_project/delUser",
			data:{"userName":userName},
			method:"POST",
			dataType:"",
			success:function(data){
				alert("用户已销毁")
			}
			
		})
	})
</script>
</html>