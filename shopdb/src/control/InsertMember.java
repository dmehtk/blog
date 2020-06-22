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


@WebServlet("/admin/InsertMember")
public class InsertMember extends HttpServlet {
	private MemberDao memberDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/insertMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+"<----memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		this.memberDao = new MemberDao();
		this.memberDao.insertMember(member);
		
		response.sendRedirect(request.getContextPath()+"/admin/TotalMemberList");
	}

}
