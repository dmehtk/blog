package blog.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.*;


@WebServlet("/SelectMemberListServlet")
public class SelectMemberListServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	private final int ROW_PER_PAGE = 2;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//통합관리자 0번이 아닌 다른 회원이 들어오면 홈화면으로 ~
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getMemberLevel() > 0) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		List<Subject> subjectList = new ArrayList<Subject>();
 		this.subjectService = new SubjectService();
		subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		this.memberService = new MemberService();
		Map<String, Object> map = this.memberService.getMemberList(currentPage, this.ROW_PER_PAGE);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("currentPage", currentPage);
		request.getRequestDispatcher("/WEB-INF/views/selectMemberList.jsp").forward(request, response);
	}

}
