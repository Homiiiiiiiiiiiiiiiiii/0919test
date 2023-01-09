<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>

</head>
<body style="width:1024px; margin:0 auto;">
<ul style="list-style:none; display:inline-flex">
	<li style="padding:0 10px">공지사항</li>
	<li style="padding:0 10px"><a href="<%=request.getContextPath()%>/board/list.do">자유게시판</a></li>
	<li style="padding:0 10px">Q&A</li>
</ul>

<c:if test="${login != null }">
<ul style="list-style:none; display:inline-flex; float:right;">
	<li style="padding:0 10px">${login.id}님 환영합니다.</li>
	<li style="padding:0 10px"><a>마이페이지</a></li>
	<li style="padding:0 10px"><a href="<%=request.getContextPath()%>/user/logout.do">로그아웃</a></li>
</ul>
</c:if>

<c:if test="${login == null }">
<ul style="list-style:none; display:inline-flex; float:right;">
	<li style="padding:0 10px"><a href="<%=request.getContextPath()%>/user/join.do">회원가입</a></li>
	<li style="padding:0 10px"><a href="<%=request.getContextPath()%>/user/login.do">로그인</a></li>
</ul>
</c:if>

<hr>
<p style="margin-top:50px;">자유게시판</p>
<hr>
<form action="modify.do" method="post">
<input type="hidden" name="bidx" value="${vo.bidx }">
<table border="1" style="width:100%;">
	<tr>
		<td>작성자</td>
		<td>${vo.id }</td>
		<td>등록일</td>
		<td style="width:20%;">${vo.wdate }</td>
		<td>조회수</td>
		<td>${vo.hit }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td colspan="5"><input type="text" name="title" value="${vo.title}"></td>
	</tr>
</table>
<textarea style="width:100%; height:300px;" name="content">${vo.content}</textarea>

<button style="margin:15px 10px 0 10px; float:right; padding:5px 20px;">
수정
</button>
</form>

<button onclick="location.href='view.do?bidx=${vo.bidx}'" style="margin-top:15px; float:right; padding:5px 20px;">
취소
</button>
</body>
</html>