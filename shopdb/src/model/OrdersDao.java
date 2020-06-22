package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import vo.*;

public class OrdersDao {
	public List<OrdersAndItem> selectOrdersListCustormer(Orders orders){
		/*
		 * SELECT * FROM orders*/
		System.out.println(orders.getUserName()+"OrdersDao �븘�씠�뵒 �솗�씤");
		System.out.println(orders.getUserPhone()+"OrdersDao �룿�꽆踰� �솗�씤");
		List<OrdersAndItem> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("SELECT o.* , i.* FROM shopdb_orders o INNER JOIN shopdb_item i ON o.item_id = i.item_id WHERE o.user_name=? AND o.user_phone=?");
			stmt.setString(1, orders.getUserName());
			stmt.setString(2, orders.getUserPhone());
			rs = stmt.executeQuery();
			list = new ArrayList<OrdersAndItem>();
			while(rs.next()) {
				OrdersAndItem oi = new OrdersAndItem();
				Orders o = new Orders();
				o.setOrdersId(rs.getInt("orders_id"));
				o.setItemId(rs.getInt("item_id"));
				o.setItemCount(rs.getInt("item_count"));
				o.setOrdersDate(rs.getString("orders_date"));
				o.setOrdersPrice(rs.getInt("orders_price"));
				o.setOrdersState(rs.getString("orders_state"));
				o.setUserName(rs.getString("user_name"));
				o.setUserPhone(rs.getString("user_phone"));
				o.setUserAddress(rs.getString("user_address"));
				oi.setOrders(o);
				
				Item i = new Item();
				i.setItemId(rs.getInt("item_id"));
				i.setCategoryId(rs.getInt("category_id"));
				i.setItemContents(rs.getString("item_contents"));
				i.setItemPrice(rs.getInt("item_price"));
				i.setItemName(rs.getString("item_name"));
				oi.setItem(i);
				
				list.add(oi);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	//二쇰Ц�젙蹂� 異붽�
	public void insertOrders(Orders orders) {
		System.out.println("-----------------OrdersDao");
		System.out.println(orders.getItemId()+"<--itemId");
		System.out.println(orders.getItemCount()+"<---itemCount");
		System.out.println(orders.getOrdersPrice()+"<--ordersPrice");
		System.out.println(orders.getUserName()+"<--userName");
		System.out.println(orders.getUserPhone()+"<--userPhone");
		System.out.println(orders.getUserAddress()+"<---userAddress");
		System.out.println(orders.getOrdersPrice()+"<--ordersPrice");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("Insert INTO shopdb_orders(item_id, item_count, orders_date, orders_price, orders_state, user_name, user_phone, user_address) VALUE(?, ?, now(), ?, 1, ?, ?, ?)");
			stmt.setInt(1, orders.getItemId());
			stmt.setInt(2, orders.getItemCount());
			stmt.setInt(3, orders.getOrdersPrice());
			stmt.setString(4, orders.getUserName());
			stmt.setString(5, orders.getUserPhone());
			stmt.setString(6, orders.getUserAddress());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
				stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	// orders_state �닔�젙 �뤌
	public Orders selectOrdersOne (int ordersId , String ordersState) {
		System.out.println(ordersId+"<---selectOrdersOne ordersId");
		System.out.println(ordersState+"<---selectOrdersOne ordersState");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Orders o = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("SELECT orders_id, orders_state from shopdb_orders WHERE orders_id=?");
			stmt.setInt(1, ordersId);
			rs = stmt.executeQuery();
			o = new Orders();
			if(rs.next()) {
				o.setOrdersId(rs.getInt("orders_id"));
				o.setOrdersState(rs.getString("orders_state"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return o;
	}
	//orders 由ъ뒪�듃 + inner join item
	public List<OrdersAndItem> selectOrdersListAll(){
		/*
		 * SELECT * FROM orders*/
		List<OrdersAndItem> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("SELECT o.* , i.* FROM shopdb_orders o INNER JOIN shopdb_item i ON o.item_id = i.item_id");
			rs = stmt.executeQuery();
			list = new ArrayList<OrdersAndItem>();
			while(rs.next()) {
				OrdersAndItem oi = new OrdersAndItem();
				Orders o = new Orders();
				o.setOrdersId(rs.getInt("orders_id"));
				o.setItemId(rs.getInt("item_id"));
				o.setItemCount(rs.getInt("item_count"));
				o.setOrdersDate(rs.getString("orders_date"));
				o.setOrdersPrice(rs.getInt("orders_price"));
				o.setOrdersState(rs.getString("orders_state"));
				o.setUserName(rs.getString("user_name"));
				o.setUserPhone(rs.getString("user_phone"));
				o.setUserAddress(rs.getString("user_address"));
				oi.setOrders(o);
				
				Item i = new Item();
				i.setItemId(rs.getInt("item_id"));
				i.setCategoryId(rs.getInt("category_id"));
				i.setItemContents(rs.getString("item_contents"));
				i.setItemPrice(rs.getInt("item_price"));
				i.setItemName(rs.getString("item_name"));
				oi.setItem(i);
				
				list.add(oi);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//admin update(orders_state)action
	public void updateOrdersState(Orders orders) {
		/*
		 * UPDATE orders set orders_state=? WHERE orders_id=?*/
		System.out.println(orders.getOrdersId()+"<---ordersId updateOrdersState");
		System.out.println(orders.getOrdersState()+"<---ordersState updateOrdersState");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("UPDATE shopdb_orders set orders_state=? WHERE orders_id=?");
			stmt.setString(1, orders.getOrdersState());
			stmt.setInt(2, orders.getOrdersId());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
				stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	//guest update
	public void updateOrders(Orders orders) {
		
	}
}
