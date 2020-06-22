package blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.PostService;
import blog.vo.Member;
import blog.vo.Post;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
	private PostService postService;
	//게시물 삭제
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = (Member)(request.getSession().getAttribute("loginMember"));
		String memberPw = request.getParameter("memberPw");

		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+"<--doPost postNo");
		//String subjectName = request.getParameter("subjectName");
		String memberId = member.getMemberId();
		System.out.println(memberId+"<--doPost memberId");
		//삭제전 관리자 or 글쓴이가 맞는지 비밀번호 확인  
		if(member == null  || !member.getMemberPw().equals(memberPw) ) {
			System.out.println("비밀번호가 틀립니다.");
			response.sendRedirect(request.getContextPath()+"/SelectPostServlet?postNo="+postNo);
			return;
		}
		Post post = new Post();
		post.setPostNo(postNo);
		member.setMemberId(memberId);
		
		this.postService = new PostService();
		this.postService.deletePostByComment(postNo, memberId);
		response.sendRedirect(request.getContextPath()+"/HomeServlet");
	}

}
