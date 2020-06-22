package blog.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.*;
import blog.service.*;
import blog.vo.*;


@WebServlet("/DeleteMemberServlet")
public class DeleteMemberServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	//탈퇴폼(비밀번호)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		request.getRequestDispatcher("/WEB-INF/views/deleteLogin.jsp").forward(request, response);
	}

	//탈퇴액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// member 테이블 삭제 + memberid 테이블 입력
		
		this.memberService = new MemberService();
		Member member = (Member)(request.getSession().getAttribute("loginMember"));
		System.out.println(member.getMemberId()+"<---deleteMemberServlet memberID");
		member.setMemberPw(request.getParameter("memberPw"));
		System.out.println(member.getMemberPw()+"<----deleteMemberServlet memberPw");
		memberService.removeMember(member);
		
		response.sendRedirect(request.getContextPath()+"/LogoutServlet");
		
	}

}
