<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="DTO.Candidator"%>
<%
request.setCharacterEncoding("UTF-8");
ArrayList<Candidator> candList = new ArrayList<Candidator>();
candList = (ArrayList<Candidator>) request.getAttribute("list"); //강제형변환
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="topmenu.jsp"%>
	<section>
		<div class="title">후보조회</div>
		<div class="wrapper">
			<table style="width: 900px">
				<tr>
					<th>후보번호</th>
					<th>성명</th>
					<th>소속정당</th>
					<th>학력</th>
					<th>주민번호</th>
					<th>지역구</th>
					<th>대표전화</th>
				</tr>
 				<%
				for (Candidator m : candList) {
				%>
				<tr>
					<td><%=m.getCandno()%></td>
					<td><%=m.getCandname()%></td>
					<td><%=m.getParty()%></td>
					<td><%=m.getSchool()%></td>
					<td><%=m.getSsn()%></td>
					<td><%=m.getRegion()%></td>
					<td><%=m.getTel()%></td>
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