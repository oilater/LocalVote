// viewer -Controller - dao - Controller - viewer 

package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBPKG.CandidatorDAO;

/**
 * Servlet implementation class VotingController
 */
@WebServlet("/")
public class VotingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VotingController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request, response);
	}

	protected void doPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String context = request.getContextPath();
		String command = request.getServletPath();
		String site = null;

		CandidatorDAO dao = new CandidatorDAO();

		switch (command) {
		case "/home":
			site = "index.jsp";
			break;
		case "/list":
			site = dao.selectAll(request, response);
			break;
		case "/vote":
			site = "vote.jsp";
			break;
		case "/search":
			site = "search.jsp";
			break;
		case "/insert":
			int result1 = dao.insert(request, response);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println(result1);
			if(result1 == 1) {
				out.println("<script>");
				out.println("alert('투표하기 정보가 정상적으로 등록되었습니다!'); location.href= '" + context +  "' ;");
				out.println("</script>");
				out.flush(); //한꺼번에 내보내기
				
			} else {
				out.println("<script>");
				out.println("alert('등록 실패! 회원정보를 모두 입력해주세요'); location.href= '" + context + "' ;");
				out.println("</script>");
				out.flush();// 한꺼번에 내보내기
			} 
			site = "index.jsp";
			break;
			
		case "/voteview":
			site = dao.selectVote(request, response);
			break;
		case "/rank":
			site = dao.selectRank(request, response);
			break;
		}
	
		getServletContext().getRequestDispatcher("/" + site).forward(request, response);

	}
}