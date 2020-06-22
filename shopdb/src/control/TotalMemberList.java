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

import java.util.*;

@WebServlet("/admin/TotalMemberList")
public class TotalMemberList extends HttpServlet {
	
	private MemberDao memberDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("loginId"));
		
		if(session.getAttribute("loginId") == null && !session.getAttribute("loginId").equals("YUSUK")) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		
		List<Member> list = new ArrayList<Member>();
		this.memberDao = new MemberDao();
		
		list = this.memberDao.selectMemberALL();
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/totalMemberList.jsp").forward(request, response);
	}

}
