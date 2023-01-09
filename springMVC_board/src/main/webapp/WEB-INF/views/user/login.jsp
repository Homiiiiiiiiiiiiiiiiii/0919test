<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body style="width:1024px; margin:0 auto;">
<p style="text-align:center;">해당 시스템은 로그인 후 사용이 가능합니다.</p>
<hr>
<form style="text-align:center;" action="login.do" method="post">
	아이디 :&nbsp; &nbsp; <input style="margin:10px;" type="text" name="id" id="id">
	<br>
	비밀번호 : <input style="margin:10px;" type="password" name="password" id="password">
	<br>
	<button style="padding:5px 110px; margin:10px 0 0 -8px;">로그인</button>
	<br>
</form>
	<p style="text-align:right;">
	<a style="color:black; "  href="<%=request.getContextPath()%>/user/join.do">[회원가입]</a>
	</p>
</body>
</html>