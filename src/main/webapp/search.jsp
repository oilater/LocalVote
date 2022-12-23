<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
request.setCharacterEncoding("UTF-8");
ArrayList<Member> list = new ArrayList<Member>();
list = (ArrayList<Member>) request.getAttribute("list"); //강제형변환
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="topmenu.jsp" %>
<section>
<div class="title">투표검수조회</div>
<div class="wrapper">
	<table style="width: 900px">
		<tr>
			<th>성명</th>
			<th>생년월일</th>
			<th>나이</th>
			<th>성별</th>
			<th>후보번호</th>
			<th>투표시간</th>
			<th>유권자확인</th>
		</tr>
	
	</table>


</div>
</section>
</body>
</html>