package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDao;
import vo.Member;
@WebServlet({"/admin/", "/admin/Index"})

public class Index extends HttpServlet {
	private MemberDao memberDao;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/index.jsp").forward(request, response);
	}
		
}
