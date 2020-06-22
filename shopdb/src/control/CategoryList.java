package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.*;
import model.*;

@WebServlet("/admin/CategoryList")
public class CategoryList extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		 // controller 역할
		 // 1) request 분석
			System.out.println(request.getRemoteAddr());
		 // 2) model 호출
			CategoryDao categoryDao = new CategoryDao();
			ArrayList<Category> list = categoryDao.selectCategoryList();
			request.setAttribute("list", list); //어느 페이지에서라도 list를 사용할 수 있게 list 값의 이름으로 list 객체를 등록 + list 만이 아닌 object 타입 다 담겨질수있음
		 // 3) view 연결
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/categoryList.jsp");
			rd.forward(request, response);
		 
	}
		
}
