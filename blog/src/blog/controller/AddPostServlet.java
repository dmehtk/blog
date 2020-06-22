package blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.*;
import blog.vo.*;


@WebServlet("/AddPostServlet")
public class AddPostServlet extends HttpServlet {
	private SubjectService subjectService;
	private PostService postService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		List<Subject> subjectList = new ArrayList<Subject>();
 		this.subjectService = new SubjectService();
		subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		request.getRequestDispatcher("/WEB-INF/views/addPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글쓰기  Servlet
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---memberId servlet");
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+"<---subjectName servlet");
		String postTitle = request.getParameter("postTitle");
		System.out.println(postTitle+"<---postTitle servlet");
		String postContent = request.getParameter("postContent");
		System.out.println(postContent+"<---postContent servlet");
		
		Post post = new Post();
		post.setMemberId(memberId);
		post.setSubjectName(subjectName);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		
		this.postService = new PostService();
		this.postService.insertPostBySubject(post);
		
		response.sendRedirect(request.getContextPath()+"/HomeServlet");
	}

}
