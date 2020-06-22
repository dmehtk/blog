package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoryDao;
import vo.Category;

@WebServlet("/admin/InsertCategory")
public class InsertCategory extends HttpServlet {
	// InsertCategory 요청이 get방식 -=> 입력폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
			
		request.getRequestDispatcher("/WEB-INF/jsp/admin/insertCategory.jsp").forward(request, response);
	}
	// InsertCategory 요청이 post방식 --> 입력
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청 분석(주소분석, request분석)
		request.setCharacterEncoding("utf-8");
		
		String categoryName = request.getParameter("categoryName");
		System.out.println(categoryName+"<----categoryName");
		Category category = new Category();
		category.setCategoryName(categoryName);
		// 모델 호출
		CategoryDao categoryDao = new CategoryDao();
		categoryDao.insertCategory(category);
		// 뷰 연결 or 다른 컨트롤러를 리다리렉트
		response.sendRedirect(request.getContextPath()+"/admin/CategoryList");
	}
}
