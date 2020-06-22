package blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.LikeyService;
import blog.vo.Likey;
import blog.vo.Member;

@WebServlet("/AddLikeyServlet")
public class AddLikeyServlet extends HttpServlet {
	private LikeyService likeyService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//좋아요  Servlet
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null ) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		String memberId = loginMember.getMemberId();
		Likey likey = new Likey();
		likey.setPostNo(postNo);
		likey.setMemberId(memberId);
		
		this.likeyService = new LikeyService();
		this.likeyService.addLikey(likey);
		
		response.sendRedirect(request.getContextPath()+"/SelectPostServlet?postNo="+postNo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
