function fn_submit() {
	var fn = document.frm;
	if (fn.ssn.value == "") {
		alert("주민번호를 입력하지 않았습니다.");
		fn.ssn.focus();
		return false;
	}

	if (fn.candname.value == "") {
		alert("이름을 입력하지 않았습니다.");
		fn.candname.focus();
		return false;
	}
	if (fn.candselect.value == "") {
		alert("투표 번호를 선택하지 않았습니다.");
		fn.candselect.focus();
		return false;
	}
	if (fn.votetime.value == "") {
		alert("투표 시간을 입력하지 않았습니다.");
		fn.votetime.focus();
		return false;
	}
	if (fn.voteplace.value == "") {
		alert("투표 장소를 입력하지 않았습니다.");
		fn.voteplace.focus();
		return false;
	}
	if (fn.confirm.value == "") {
		alert("유권자확인란을 선택하지 않았습니다.");
		fn.confirm.focus();
		return false;
	}

	fn.submit();
}