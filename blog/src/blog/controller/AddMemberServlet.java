package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;


@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		this.subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		//가입폼\
		request.getRequestDispatcher("/WEB-INF/views/addMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		this.subjectService = new SubjectService();
		List<Subject> subjectList = this.subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);

		String memberId = request.getParameter("memberId");
		System.out.println(memberId+"<---memberId signUpServlet");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+"<---memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		this.memberService = new MemberService();
		boolean flag = this.memberService.addMember(member);
		if(flag == true) {
			//로그인
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
		}else {
			//회원가입창
			response.sendRedirect(request.getContextPath()+"/AddMemberServlet");
		}
		
		
	}

}
