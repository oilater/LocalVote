<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<div class="title">투표하기</div>
		<form name="frm" action="insert">
			<div class="wrapper">
				<table>
					<tr>
						<th>주민번호</th>
						<td><input name="ssn"> 예:8906153154726</td>
					</tr>
					<tr>
						<th>성명</th>
						<td><input name="candname">
					</tr>
					<tr>
						<th>투표번호</th>
						<td><select name="candselect">
								<option value=""></option>
								<option value="1">[1]김후보</option>
								<option value="2">[2]이후보</option>
								<option value="3">[3]박후보</option>
								<option value="4">[4]조후보</option>
								<option value="5">[5]최후보</option>
						</select></td>
					</tr>
					<tr>
						<th>투표시간</th>
						<td><input name="votetime">
					</tr>
					<tr>
						<th>투표장소</th>
						<td><input name="voteplace">
					</tr>
					<tr>
						<th>유권자확인</th>
						<td><label><input type="radio" value="Y" name="confirm">확인</label>
							<label><input type="radio" value="N" name="confirm">미확인</label></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit"
							 onclick="fn_submit();">투표하기</button>
							<button class="btn" type="button" onclick="location='home'">다시하기</button>
						</td>
					</tr>
				</table>
			</div>
		</form>

	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>