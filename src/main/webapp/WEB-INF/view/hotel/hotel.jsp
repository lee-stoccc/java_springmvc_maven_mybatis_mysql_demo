<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hotel</title>
</head>
<body>
	<div style="text-align:center" id="login"> 搜索</div>
	<div style="text-align:center">
		<span style="padding-right:20px">HotelID：</span><input type="text" id="HotelID">
	</div>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px">HotelCode：</span><input type="text" id="HotelCode">
	</div>
	<br>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px">HotelName：</span><input type="text" id="HotelName">
	</div>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px">HotelAdd：</span><input type="text" id="HotelAdd">
	</div>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px">HotelTel：</span><input type="text" id="HotelTel">
	</div>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px">LegalPerson：</span><input type="text" id="LegalPerson">
	</div>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px">CityID：</span><input type="text" id="CityID">
	</div>
	<br>
	<div style="text-align:center">
		<span style="padding-right:20px">AuditUserCode：</span><input type="text" id="AuditUserCode">
	</div>
	
	<div style="text-align:center">
		<span style="padding-right:20px"></span><button id="submit">  搜索 </button>
		<span style="padding-right:20px"></span><button id="register"> 注册 </button>
		<span style="padding-right:20px"></span><button id="delUser"> 销毁用户 </button>
	</div>
</body>

<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script>
	$('#submit').click(function(){
		var HotelID = $('#HotelID').val()
		var HotelCode = $('#HotelCode').val()
		var HotelName = $('#HotelName').val()
		var HotelAdd = $('#HotelAdd').val()
		var HotelTel = $('#HotelTel').val()
		var LegalPerson = $('#LegalPerson').val()
		var CityID = $('#CityID').val()
		var AuditUserCode = $('#AuditUserCode').val()
		
		var data = {
			'HotelID':HotelID,
			'HotelCode':HotelCode,
			'HotelName':HotelName,
			'HotelAdd':HotelAdd,
			'HotelTel':HotelTel,
			'LegalPerson':LegalPerson,
			'CityID':CityID,
			'AuditUserCode':AuditUserCode,
		}
		
		$.ajax({
			type:"POST",
			url:"/stockmgt_maven_project/searchHotel",
			data:data,
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
		console.log(data)
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