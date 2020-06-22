package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.OrdersDao;
import vo.*;


@WebServlet("/admin/OrdersList")
public class OrdersList extends HttpServlet {
	private OrdersDao ordersDao;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//비로그인 상태시에는 로그인화면으로 이동
		HttpSession session = request.getSession(); 
		System.out.println(session.getAttribute("loginId")+ "<---세션 확인");
		
		if(session.getAttribute("loginId") == null) {
			response.sendRedirect(request.getContextPath()+"/admin/AdminLogin");
			return;
		}
		//model 호출
		this.ordersDao = new OrdersDao();
		List<OrdersAndItem> list = new ArrayList<OrdersAndItem>();
		list = this.ordersDao.selectOrdersListAll();
		request.setAttribute("list", list); // list를 ordersList.jsp에서 사용할 수 있개끔
		//view
		request.getRequestDispatcher("/WEB-INF/jsp/admin/ordersList.jsp").forward(request, response);
	}

}
