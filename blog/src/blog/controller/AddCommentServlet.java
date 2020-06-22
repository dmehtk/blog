package blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.CommentService;
import blog.vo.*;


@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private CommentService commentService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//인증
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		//request -> comment
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+"<--addcommentServlet postNo");
		String memberId = loginMember.getMemberId();
		System.out.println(memberId+"<--memberId");
		String commentContent = request.getParameter("commentContent");
		System.out.println(commentContent+"<---commentContent");
		Comment comment = new Comment();
		comment.setPostNo(postNo);
		comment.setMemberId(memberId);
		comment.setCommentContent(commentContent);
		
		this.commentService = new CommentService();
		this.commentService.addComment(comment);
		//redirect
		response.sendRedirect(request.getContextPath()+"/SelectPostServlet?postNo="+postNo);
	}

}
