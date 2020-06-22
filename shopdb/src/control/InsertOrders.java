package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import vo.*;


@WebServlet("/mall/InsertOrders")
public class InsertOrders extends HttpServlet {
	private ItemDao itemDao;
	private OrdersDao ordersDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		System.out.println(itemId);
		this.itemDao = new ItemDao();
		Item item = new Item();
		item.setItemId(itemId);
		
		item = itemDao.selectMallItemOne(itemId);
		
		request.setAttribute("i", item);
		
		request.getRequestDispatcher("/WEB-INF/jsp/mall/insertOrders.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//request get..
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		System.out.println(itemId+"<--insertOrders itemId");
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		System.out.println(itemPrice+"<--insertOrders itemPrice");
		int itemCount = Integer.parseInt(request.getParameter("itemCount"));
		System.out.println(itemCount+"<--insertOrders itemCount");
		String userName = request.getParameter("userName");
		System.out.println(userName+"<--insertOrders userName");
		String userPhone = request.getParameter("userPhone");
		System.out.println(userPhone+"<--insertOrders userPhone");
		String userAddress = request.getParameter("userAddress");
		System.out.println(userAddress+"<--insertOrders userAddress");
		
		Orders orders = new Orders();
		//orders set..
		orders.setItemId(itemId);
		orders.setItemCount(itemCount);
		orders.setUserName(userName);
		orders.setOrdersPrice(itemCount*itemPrice);
		orders.setUserPhone(userPhone);
		orders.setUserAddress(userAddress);
	
		this.ordersDao = new OrdersDao();
		this.ordersDao.insertOrders(orders);
		
		response.sendRedirect(request.getContextPath()+"/mall/MyOrdersList");
		
	}

}
