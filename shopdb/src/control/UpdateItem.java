package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemDao;
import vo.*;
@WebServlet("/admin/UpdateItem")
public class UpdateItem extends HttpServlet {
	private ItemDao itemDao;
	//입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		System.out.println(itemId+"<---itemId doGet UpdateItem");
		this.itemDao = new ItemDao();
		ItemAndCategory CategoryAndItem = itemDao.selectItemOne(itemId);
		request.setAttribute("ci", CategoryAndItem);
		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateItem.jsp").forward(request, response);
	}
	//실행
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("<------------------------> updateAction");
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		System.out.println(itemId+"<------itemId");
		String itemContents = request.getParameter("itemContents");
		System.out.println(itemContents+"<-----itemContents");
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		System.out.println(itemPrice+"<----------itemPrice");
		String itemName = request.getParameter("itemName");
		System.out.println(itemName+"<---------itemName");
		
		Item item = new Item();
		item.setItemContents(itemContents);
		item.setItemId(itemId);
		item.setItemName(itemName);
		item.setItemPrice(itemPrice);;
		
		this.itemDao = new ItemDao();
		itemDao.updateItem(item);
		
		response.sendRedirect(request.getContextPath()+"/admin/ItemList");
		
		
	}

}
