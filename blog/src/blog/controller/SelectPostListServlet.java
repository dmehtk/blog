package blog.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.*;
import blog.vo.*;


@WebServlet("/SelectPostListServlet")
public class SelectPostListServlet extends HttpServlet {
	private SubjectService subjectService;
	final private int ROW_PER_PAGE=5;
	private PostService postService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전체 포스팅목록 +  Servlet
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getMemberLevel() > 9) {
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
		
		this.postService = new PostService();
		Map<String, Object> map = this.postService.getPostList(currentPage, ROW_PER_PAGE);
		request.setAttribute("postList", map.get("list"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("currentPage", currentPage);
		
		request.getRequestDispatcher("/WEB-INF/views/selectPostList.jsp").forward(request, response);
		
		
		
	}

}
