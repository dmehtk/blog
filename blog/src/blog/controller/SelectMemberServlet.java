package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.*;
import blog.service.*;
import blog.vo.*;

@WebServlet("/SelectMemberServlet")
public class SelectMemberServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원정보
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		
		this.subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		/*
		 * Member member = (Member) session.getAttribute("loginMember");
		   String memberId = member.getMemberId();
		*/
		
		String memberId = ((Member)(session.getAttribute("loginMember"))).getMemberId();
		System.out.println(memberId+"<---memberId");
		this.memberService = new MemberService();
		Member member = this.memberService.getMemberOne(memberId);
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/selectMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
