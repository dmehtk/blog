package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.*;


@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---memberId signUpServlet");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+"<---memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		this.memberService = new MemberService();
		
		
		response.sendRedirect(request.getContextPath()+"/LoginServlet");
		
	}

}
