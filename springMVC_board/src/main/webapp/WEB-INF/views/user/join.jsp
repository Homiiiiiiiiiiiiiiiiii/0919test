<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
<script>
	var chekchIdFlag = false; //아이디 체크여부 변수
	var checkIdVal = ""; //값 저장 변수
	
	function checkId(){
// 		alert("클릭ㄱ");
		var idval = $("#id").val(); //입력받은 id값을 가져와 변수에 담음

		$.ajax({
			url:"checkId.do",
			type:"post",	
			data:"id="+idval,
			success:function(data){
				if(data == 1){
					alert("중복된 아이디 입니다.");
					chekchIdFlag = false;
					checkIdVal = "";
				}else{
					alert("사용할 수 있는 아이디입니다.");
					chekchIdFlag = true;
					checkIdVal = idval;
				}
			}
		});
	}
	
	$(function(){
		$("form").submit(function(){
			if(!chekchIdFlag){
				alert("아이디 중복확인을 하세요.");
				$("#id").focus();
				return false;
			}else if($("#id").val() == ""){
				alert("아이디를 입력하세요.");
				$("#id").focus();
				return false;
			}else if($("#password").val() == ""){
				alert("비밀번호를 입력하세요.");
				$("#password").focus();
				return false;				
			}else if($("#password2").val() == ""){
				alert("비밀번호를 입력하세요.");
				$("#password2").focus();
				return false;				
			}else if($("#password").val() != $("#password2").val()){
				alert("비밀번호가 일치하지 않습니다.");
				$("#password2").focus();
				return false;
			}else if($("#name").val() == ""){
				alert("이름을 입력하세요.");
				$("#name").focus();
				return false;
			}else if($("#email").val() == ""){
				alert("이메일을입력하세요.");
				$("#email").focus();
				return false;
			}else if($("#phone").val() == ""){
				alert("연락처를 입력하세요.");
				$("#phone").focus();
				return false;
			}else if($("#addr").val() == ""){
				alert("주소를 입력하세요.");
				$("#addr").focus();
				return false;
			}else{
				return true;	
			}
		});
	})
	
	function blurId(obj){
		var val = obj.value;
		
		if(chekchIdFlag && val != checkIdVal){//chekchIdFlag참이고 val 이 checkIdVal와 같지않을 경우
			chekchIdFlag = false; //중복체크를 무효화시킴
		}
	}
</script>
</head>
<body style="width:1024px; margin:0 auto;">
<p style="text-align:center;">회원 가입 후 로그인하여 이용하세요.</p>
<hr>
<form style="text-align:center;" action="join.do" method="post">
	아이디 :<input style="margin:10px;" type="text" name="id" id="id" onblur="blurId(this)"><button type="button" onclick="checkId()">중복확인</button>
	<br>
	비밀번호 : <input style="margin:10px;" type="password" name="password" id="password">
	<br>
	비밀번호 확인 : <input style="margin:10px;" type="password" name="password2" id="password2">
	<br>
	이름 : <input style="margin:10px;" type="text" name="name" id="name">
	<br>
	이메일: <input style="margin:10px;" type="text" name="email" id="email">
	<br>
	연락처 : <input style="margin:10px;" type="text" name="phone" id="phone">
	<br>
	주소: <input style="margin:10px;" type="text" name="addr" id="addr">
	<br>	
	<button style="padding:5px 110px; margin:10px 0 0 -8px;">회원가입</button>
	<br>
</form>
</body>
</html>