package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.OrdersDao;
import vo.Orders;


@WebServlet("/admin/UpdateOrdersState")
public class UpdateOrdersState extends HttpServlet {
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		int ordersId = Integer.parseInt(request.getParameter("ordersId"));
		System.out.println(ordersId+"<----updateOrdersState ordersId");
		String ordersState = request.getParameter("ordersState");
		System.out.println(ordersState+"<---updateOrdersState ordersState");
		//model 호출
		this.ordersDao = new OrdersDao();
		Orders orders = this.ordersDao.selectOrdersOne(ordersId, ordersState);
		request.setAttribute("o", orders);
		// view
		request.getRequestDispatcher("/WEB-INF/jsp/admin/updateOrdersState.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int ordersId = Integer.parseInt(request.getParameter("ordersId"));
		System.out.println(ordersId+"<----updateOrdersState ordersId");
		String ordersState = request.getParameter("ordersState");
		System.out.println(ordersState+"<----updateOrdersState ordersState");
		
		Orders orders = new Orders();
		orders.setOrdersId(ordersId);
		orders.setOrdersState(ordersState);
		
		this.ordersDao = new OrdersDao();
		this.ordersDao.updateOrdersState(orders);
		
		response.sendRedirect(request.getContextPath()+"/admin/OrdersList");
	}

}
