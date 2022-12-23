<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*"%>
	<%@ page import="DTO.Search"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Search> voteList = new ArrayList<Search>();
voteList = (ArrayList<Search>) request.getAttribute("voteList"); //강제형변환
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="script.js"></script>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="topmenu.jsp"%>
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
				<%
				for (Search m : voteList) {
				%>
				<tr>
					<td><%=m.getName()%></td>
					<td><%=m.getBirth()%></td>
					<td><%=m.getAge()%></td>
					<td><%=m.getSex()%></td>
					<td><%=m.getCandNum()%></td>
					<td><%=m.getVoteTime()%></td>
					<td><%=m.getConfirm()%></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>