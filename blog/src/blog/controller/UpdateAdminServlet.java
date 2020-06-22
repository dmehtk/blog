package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.*;
import blog.vo.Member;
import blog.vo.Subject;


@WebServlet("/UpdateAdminServlet")
public class UpdateAdminServlet extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 등급 업데이트 , 통합관리자 외엔 못들어옴
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getMemberLevel() > 0) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		this.subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<--memberId");
		Member member = new Member();
		member.setMemberId(memberId);
		
		this.memberService = new MemberService();
		member = this.memberService.getMemberOne(memberId);
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/views/updateMemberList.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<--memberId");
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		System.out.println(memberLevel+"<--memberLevel");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberLevel(memberLevel);
		
		this.memberService = new MemberService();
		this.memberService.updateMember(member);
		
		response.sendRedirect(request.getContextPath()+"/HomeServlet");
	}

}
