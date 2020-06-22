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

@WebServlet("/admin/DeleteMember")
public class DeleteMember extends HttpServlet {
	private MemberDao memberDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<--DeleteMember memberId");
		
		Member member = new Member();
		member.setMemberId(memberId);
		
		this.memberDao = new MemberDao();
		this.memberDao.deleteMember(memberId);
		
		HttpSession session = request.getSession();
		session.invalidate(); //현재 요청주인의 세션을 리셋
		System.out.println("현재 요청주인의 세션을 리셋");
		response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
	}
}
