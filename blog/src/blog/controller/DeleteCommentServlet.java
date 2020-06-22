package blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.CommentService;
import blog.vo.Comment;


@WebServlet("/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
	private CommentService commentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int postNo =  Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+"<---postNo doPost");
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		System.out.println(commentNo+"<--commentNo doPost");
		String commentContent = request.getParameter("commentContent");
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setCommentContent(commentContent);
		this.commentService = new CommentService();
		this.commentService.deleteComment(commentNo, commentContent);
		request.getRequestDispatcher("/SelectPostServlet?postNo="+postNo).forward(request, response);
	}

}
