package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import vo.Orders;
import vo.OrdersAndItem;
@WebServlet("/mall/MyOrdersList2")
public class MyOrdersList2 extends HttpServlet {
	private OrdersDao ordersDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		System.out.println(userName+"<--doPost userName");
		String userPhone = request.getParameter("userPhone");
		System.out.println(userPhone+"<--doPost userPhone");
		
		List<OrdersAndItem> list = new ArrayList<OrdersAndItem>();
		Orders orders = new Orders();
		orders.setUserName(userName);
		orders.setUserPhone(userPhone);
		
		this.ordersDao = new OrdersDao();
		
		
		list = this.ordersDao.selectOrdersListCustormer(orders);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/jsp/mall/myOrdersList2.jsp").forward(request, response);
	}

}
