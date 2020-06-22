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

@WebServlet("/admin/UpdateMember")
public class UpdateMember extends HttpServlet {
	private MemberDao memberDao ;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 확인
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("loginId"));
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		//
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---memberId");
		Member member = new Member();
		member.setMemberId(memberId);
		this.memberDao = new MemberDao();
		member = this.memberDao.selectMemberOne(memberId);
		request.setAttribute("m", member);
		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+"<---memberPw");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		memberDao = new MemberDao();
		memberDao.updateMember(member);
		
		HttpSession session = request.getSession();
		session.invalidate(); //현재 요청주인의 세션을 리셋
		System.out.println("현재 요청주인의 세션을 리셋");
		response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
	}

}
