<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<div style="display:inline-flex; float:right; margin-bottom:50px;">
<form action="list.do" method="get">
	<select name="searchType">
		<option value="title" <c:if test="${param.searchType eq 'title' }">selected</c:if>>제목</option>
		<option value="content" <c:if test="${param.searchType eq 'content' }">selected</c:if>>내용</option>
		<option value="id" <c:if test="${param.searchType eq 'id' }">selected</c:if>>작성자</option>
	</select>
	<input type="text" name="searchVal" value="${param.searchVal }">
	<button>검색</button>
</form>
</div>
<table border="1" style="width:100%; text-align:center; margin-bottom:20px;">
	<tr>
		<th>NO</th>
		<th style="width:55%;">제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach items="${datalist}" var="vo">
	<tr>
		<td style="padding:10px;">${vo.bidx}</td>
		<td><a  style="color:black; text-decoration:none;" href="<%=request.getContextPath()%>/board/view.do?bidx=${vo.bidx}">${vo.title}</a></td>
		<td>${vo.id}</td>
		<c:set var="wdate" value="${vo.wdate }"/>
		<td>${fn:substring(wdate,0,11)}</td>
		<td>${vo.hit}</td>
	</tr>
	</c:forEach>
</table>
<c:if test="${login !=null }">
<button onclick="location.href='write.do'" style="margin-top:15px; float:right; padding:5px 20px;">
작성하기
</button>
</c:if>
</body>
</html>