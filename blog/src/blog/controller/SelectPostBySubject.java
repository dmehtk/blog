package blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.*;
import blog.vo.*;


@WebServlet("/SelectPostBySubject")
public class SelectPostBySubject extends HttpServlet {
	private SubjectService subjectService;
	private PostService postService;
	final private int ROW_PER_PAGE=5;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 포스팅 목록 + 페이징
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+"<---subjectName servlet");
		Post post = new Post();
		post.setSubjectName(subjectName);
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage+"<--currentPage servlet");
		List<Subject> subjectList = new ArrayList<Subject>();
 		this.subjectService = new SubjectService();
		subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		this.postService = new PostService();
		Map<String, Object> map = this.postService.getPostBySubject(currentPage, ROW_PER_PAGE, subjectName);
		request.setAttribute("postList", map.get("list"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("subjectName", subjectName);
		
		request.getRequestDispatcher("/WEB-INF/views/selectPostBySubject.jsp").forward(request, response);
	}

}
