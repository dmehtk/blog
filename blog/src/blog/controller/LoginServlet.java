package blog.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.*;
import blog.service.*;
import blog.vo.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	//login form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		} 
		
		this.subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		
	}
	//login action\
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---loginServlet memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+"<--loginServlet memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		this.memberService = new MemberService();
		Member returnMember = this.memberService.getMemberOne(member);
		
		if(returnMember != null){
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", returnMember);
			System.out.println("로그인 성공");
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
		}else {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
		}
	}

}
