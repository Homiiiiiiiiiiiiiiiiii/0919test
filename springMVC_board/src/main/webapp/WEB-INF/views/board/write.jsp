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
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>

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


<hr>
<p style="margin-top:50px;">자유게시판</p>
<hr>
<form action="write.do" method="post" enctype="multipart/form-data">
	<div style="padding:5px 0;">
	제목 <input type="text" name="title" id="title">
	</div>
	<br>
	<textarea style="width:100%;" name="content" id="content" rows="40" cols="143"></textarea>
	<br>
	첨부파일 <input type="file" name="file" id="file">
<button style="margin-top:15px; float:right; padding:5px 20px;" id="btn">등록하기</button>
</form>

<script>
$(function(){
	
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "<%=request.getContextPath()%>/resources/smarteditor/SmartEditor2Skin.html",
	fCreator: "createSEditor2",
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : false,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
});

$("#btn").click(function(){
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD",[]);
	
	$("frm").submit;
	})

})

 </script>


</body>
</html>