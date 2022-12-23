package DBPKG;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Candidator;
import DTO.Search;
import DTO.candRank;

public class CandidatorDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "system", "sys1234");
		return con;
	}

	public String selectAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Candidator> list = new ArrayList<Candidator>();
		try {
			conn = getConnection();
			String sql = "select b.m_no as 후보번호, b.m_name as 성명, a.p_name as 소속정당, DECODE(b.p_school,'2','학사','3', '석사', '4','박사','고졸') as 학력, substr(b.m_jumin,1,6)||'-'||substr(b.m_jumin,7,7) as 주민번호, b.m_city as 지역구, \r\n"
					+ "a.p_tel1||'-'||a.p_tel2||'-'||substr(a.p_tel3,4)||substr(a.p_tel3,4)||substr(a.p_tel3,4)||substr(a.p_tel3,4) as 대표전화 from tbl_party_202005 a join tbl_member_202005 b on a.p_code = b.p_code";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Candidator candidator = new Candidator();
				candidator.setCandno(rs.getString(1));
				candidator.setCandname(rs.getString(2));
				candidator.setParty(rs.getString(3));
				candidator.setSchool(rs.getString(4));
				candidator.setSsn(rs.getString(5));
				candidator.setRegion(rs.getString(6));
				candidator.setTel(rs.getString(7));
				list.add(candidator);
			}
		
	request.setAttribute("list", list);
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list.jsp";

	}

	public int insert(HttpServletRequest request, HttpServletResponse response) {
		String ssn = request.getParameter("ssn");
		String candname = request.getParameter("candname");
		String candselect = request.getParameter("candselect");
		String votetime = request.getParameter("votetime");
		String voteplace = request.getParameter("voteplace");
		String confirm = request.getParameter("confirm");
		int result = 0;
		try {
			conn = getConnection();
			// prepareStatement는 순서에 따라 값이 등록된다.

			String sql = "insert into tbl_vote_202005 values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, ssn);
			ps.setString(2, candname);
			ps.setString(3, candselect);
			ps.setString(4, votetime);
			ps.setString(5, voteplace);
			ps.setString(6, confirm);
			result = ps.executeUpdate();
			conn.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}


	public String selectVote(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Search> voteList = new ArrayList<Search>();

		try {
			conn = getConnection();
			String sql = "select a.v_name as 이름, 19||substr(a.v_jumin,1,2)||'년 '||substr(a.v_jumin,3,2)||'월 '||substr(a.v_jumin,5,2)||'일' as 생년월일, "
					+ "' 만 '|| (extract(YEAR from sysdate) - (1900 + to_number(substr(a.v_jumin,1,2))))||'세' as 나이, "
					+ "decode(substr(a.v_jumin,7,1),1,'남자',2,'여자') as 성별, a.m_no as 후보번호, a.v_time as 투표시간, a.v_confirm as 유권자확인 from tbl_vote_202005 a";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Search search = new Search();
				search.setName(rs.getString(1));
				search.setBirth(rs.getString(2));
				search.setAge(rs.getString(3));
				search.setSex(rs.getString(4));
				search.setCandNum(rs.getString(5));
				search.setVoteTime(rs.getString(6));
				search.setConfirm(rs.getString(7));
				voteList.add(search);
			}
			request.setAttribute("voteList", voteList);

			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "voteview.jsp";
	}
	
	public String selectRank(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<candRank> rankList = new ArrayList<candRank>();
		try {
			conn = getConnection();
			String sql = "select a.m_no, b.m_name, count(a.v_name) from tbl_vote_202005 a join tbl_member_202005 b on a.m_no = b.m_no "
					+ "group by a.m_no, b.m_no, b.m_name order by count(a.v_name) desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				candRank rank = new candRank();
				rank.setCandno(rs.getString(1));
				rank.setCandname(rs.getString(2));
				rank.setTotalvote(rs.getString(3));
				rankList.add(rank);   
			}
			request.setAttribute("rankList", rankList);

			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "rankList.jsp";

	}

}