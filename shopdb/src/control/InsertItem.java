package control;

import java.io.IOException;
import java.util.*;
import vo.*;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/InsertItem")
public class InsertItem extends HttpServlet {
	private ItemDao itemDao;
	private CategoryDao categoryDao;
	//입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		
		this.categoryDao = new CategoryDao();
		List<Category> list = this.categoryDao.selectCategoryList();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/insertItem.jsp").forward(request, response);
		
	}
	//입력 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//request 요청
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String itemContents = request.getParameter("itemContents");
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		String itemName = request.getParameter("itemName");
		//model 호출
		Item item = new Item();
		item.setCategoryId(categoryId);
		item.setItemContents(itemContents);
		item.setItemName(itemName);
		item.setItemPrice(itemPrice);
		
		this.itemDao = new ItemDao();
		this.itemDao.insertItem(item);
		//view
		response.sendRedirect(request.getContextPath()+"/admin/ItemList");
		
	}

}
