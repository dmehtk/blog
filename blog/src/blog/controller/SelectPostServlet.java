package blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.*;
import blog.vo.*;


@WebServlet("/SelectPostServlet")
public class SelectPostServlet extends HttpServlet {
	private SubjectService subjectService;
	private PostService postService;
	private CommentService commentService;
	private LikeyService likeyService;
	private final int ROW_PER_PAGE=5;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null ) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		//좌측 나라별 메뉴
		List<Subject> subjectList = new ArrayList<Subject>();
 		this.subjectService = new SubjectService();
		subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		//게시물 상세 리스트
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+"<---selectPostServlet postNo");
		Post post = new Post();
		post.setPostNo(postNo);
		
		this.postService = new PostService();
		post = this.postService.getPostOne(postNo);
		request.setAttribute("post", post);
		//댓글 리스트
		//페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		this.commentService = new CommentService();
		Map<String, Object> map = new HashMap<String, Object>();
		map = this.commentService.getComment(postNo, currentPage, ROW_PER_PAGE);
		request.setAttribute("commentList", map.get("list"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("currentPage", currentPage);
		//좋아요 수  likeCount
		this.likeyService = new LikeyService();
		int likeyCount = this.likeyService.getLikeyCount(postNo);
		request.setAttribute("likeyCount", likeyCount);
		//싫어요 수 hatelyCount
		this.likeyService = new LikeyService();
		int hateyCount = this.likeyService.getHateyCount(postNo);
		request.setAttribute("hateyCount", hateyCount);
		
		request.getRequestDispatcher("/WEB-INF/views/selectPost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//게시물 수정
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+"<---postNo doPost");
		String postContent = request.getParameter("postContent");
		System.out.println(postContent+"<---postContent doPost");
		String postTitle = request.getParameter("postTitle");
		System.out.println(postTitle+"<--postTitle doPost");
		
		Post post = new Post();
		post.setPostNo(postNo);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		
		this.postService = new PostService();
		this.postService.updatePost(post);
		
		response.sendRedirect(request.getContextPath()+"/SelectPostServlet?postNo="+postNo);
	}

}
