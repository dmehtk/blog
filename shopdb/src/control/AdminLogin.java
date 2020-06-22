package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDao;
import vo.Member;

@WebServlet("/admin/AdminLogin")
public class AdminLogin extends HttpServlet {
	private MemberDao memberDao;
	//loginForm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/admin/adminLogin.jsp").forward(request, response);
	}
	//login action(인증)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+"<---memberPw");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		this.memberDao = new MemberDao();
		Member returnMember = memberDao.login(member);
		if(returnMember != null) {
			System.out.println("로그인 성공");
			//1.성공했다는 정보를 톰캣안(변수)에 저장
			HttpSession session = request.getSession();
			session.setAttribute("loginId", returnMember.getMemberId());
			request.setAttribute("member", returnMember.getMemberId());
			//2.관리자 indexPage
			response.sendRedirect(request.getContextPath()+"/admin/");
		}else {
			System.out.println("로그인 실패!");
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
		}
	}

}
