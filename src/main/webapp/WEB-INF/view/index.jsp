<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div style="text-align:center">
			Index
			<span style="text-align:right;margin-left:100px">注销</span>: <button id="checkOut">checkOut</button>
	</div>
	
	<div style="text-align:center;margin-top:50px"> data from session</div>
	<div style="text-align:center">
	userId:            ${sessionScope.sessionId}
	<br >
	userName:          ${sessionScope.userName}
	<br>
	userPassword:      ${sessionScope.password}
	
	</div>
	
	<div  style="text-align:center">
		
	</div>
	
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script>
		$('#checkOut').click(function(){
			$.ajax({
				url:"/stockmgt_maven_project/checkout",
				data:"",
				method:"GET",
				success:function(){
					alert('退出成功')
					window.location.href="/stockmgt_maven_project/login"
				}
			})
		})
	
	</script>
</body>
</html>