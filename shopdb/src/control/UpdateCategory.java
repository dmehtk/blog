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

@WebServlet("/admin/UpdateCategory")
public class UpdateCategory extends HttpServlet {
	
	private CategoryDao categoryDao;
	//수정 폼 C-M-V
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		System.out.println(categoryId+"<----------UpdateCategory.doGet,  control,  categoryId");
		
		this.categoryDao = new CategoryDao();
		Category category = categoryDao.selectCategoryOne(categoryId);
		request.setAttribute("cate", category);
		//view
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateCategory.jsp").forward(request, response);
	}

	//수정 액션 C-M -> 리다이렉트 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		System.out.println(categoryId+"<----------UpdateCategory.doGet,  control,  categoryId");
		String categoryName = request.getParameter("categoryName");
		System.out.println(categoryName);
		Category category = new Category();
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);
		
		this.categoryDao = new CategoryDao();
		this.categoryDao.updateCategory(category);
		
		response.sendRedirect(request.getContextPath()+"/CategoryList");
	}

}
