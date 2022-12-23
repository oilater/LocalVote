<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.candRank"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<candRank> rankList = new ArrayList<>();
rankList = (ArrayList<candRank>) request.getAttribute("rankList"); //setAttribute의 키를 받아옴
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후보자등수</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="topmenu.jsp"%>
	<section>
		<div class="title">후보자등수</div>
		<div class="wrapper">
			<table style="width: 900px">
				<tr>
					<th>후보번호</th>
					<th>성명</th>
					<th>총투표건수</th>
				</tr>
 				<%
				for (candRank r : rankList) {
				%>
				<tr>
					<td><%=r.getCandno()%></td>
					<td><%=r.getCandname()%></td>
					<td><%=r.getTotalvote()%></td>
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