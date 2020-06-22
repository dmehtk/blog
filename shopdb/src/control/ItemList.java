package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemDao;
import vo.*;


@WebServlet("/admin/ItemList")
public class ItemList extends HttpServlet {
	private ItemDao itemDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		
		this.itemDao = new ItemDao();
		//model 호출
		List<ItemAndCategory> list = this.itemDao.selectItemListAll();
		request.setAttribute("list", list);
		// view
		request.getRequestDispatcher("/WEB-INF/jsp/admin/itemList.jsp").forward(request, response);
	}

}
