package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDao;
import vo.Member;

@WebServlet("/admin/MemberList")
public class MemberList extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("loginId"));
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---memberId");
		Member member = new Member();
		member.setMemberId(memberId);
		MemberDao memberDao = new MemberDao();
		member = memberDao.selectMemberOne(memberId);
		request.setAttribute("m", member);
		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/memberList.jsp").forward(request, response);
	}

}
