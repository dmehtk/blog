package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import vo.*;

public class ItemDao {
	
	public Item selectMallItemOne(int itemId) {
		System.out.println(itemId+"<----itemId itemDao");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Item i = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("select * from shopdb_item where item_id=?");
			stmt.setInt(1, itemId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				i = new Item();
				i.setItemId(rs.getInt("item_id"));
				i.setCategoryId(rs.getInt("category_id"));
				i.setItemContents(rs.getString("item_contents"));
				i.setItemPrice(rs.getInt("item_price"));
				i.setItemName(rs.getString("item_name"));
				i.setItemImg(rs.getString("item_img"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ItemDao , selectItemListAll �삁�쇅泥섎━");
		}finally {
			try{
				conn.close();
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	//�븘�씠�뀥 由ъ뒪�듃 + inner join category
	public List<ItemAndCategory> selectItemListAll(){
		List<ItemAndCategory> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("SELECT i.* , c.* from shopdb_item i INNER JOIN shopdb_category c ON i.category_id = c.category_id");
			rs = stmt.executeQuery();
			list = new ArrayList<ItemAndCategory>();
			while(rs.next()) {
				ItemAndCategory ic = new ItemAndCategory();
				Item i = new Item();
				i.setItemId(rs.getInt("item_id"));
				i.setCategoryId(rs.getInt("category_id"));
				i.setItemContents(rs.getString("item_contents"));
				i.setItemPrice(rs.getInt("item_price"));
				i.setItemName(rs.getString("item_name"));
				i.setItemImg(rs.getString("item_img"));
				ic.setItem(i);
				
				Category c = new Category();
				c.setCategoryId(rs.getInt("category_id"));
				c.setCategoryName(rs.getString("category_name"));
				ic.setCategory(c);
				list.add(ic);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ItemDao , selectItemListAll");
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
	//�븘�씠�뀥 �뾽�뜲�씠�듃 �뤌 + inner join category
	public ItemAndCategory selectItemOne(int itemId) {
		System.out.println(itemId+"<----itemId itemDao");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Item i = null;
		Category c = null;
		ItemAndCategory ci = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("SELECT i.item_id, i.category_id, i.item_contents, i.item_price, i.item_name, c.category_id, c.category_name FROM shopdb_item i INNER JOIN shopdb_category c WHERE item_id=?");
			stmt.setInt(1, itemId);
			rs = stmt.executeQuery();
			ci = new ItemAndCategory();
			if(rs.next()) {
				i = new Item();
				i.setItemId(rs.getInt("item_id"));
				i.setCategoryId(rs.getInt("category_id"));
				i.setItemContents(rs.getString("item_contents"));
				i.setItemPrice(rs.getInt("item_price"));
				i.setItemName(rs.getString("item_name"));
				ci.setItem(i);
				
				c = new Category();
				c.setCategoryId(rs.getInt("category_id"));
				c.setCategoryName(rs.getString("category_name"));
				ci.setCategory(c);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ItemDao , selectItemListAll �삁�쇅泥섎━");
		}finally {
			try{
				conn.close();
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ci;
	}
	//�븘�씠�뀥 異붽�
	public void insertItem(Item item) {
		System.out.println(item.getCategoryId()+"<----InsertItem categoryId");
		System.out.println(item.getItemContents()+"InsertItem itemContents");
		System.out.println(item.getItemName()+"InsertItem itemName");
		System.out.println(item.getItemPrice()+"InsertItem itemPrice");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("INSERT INTO shopdb_item(category_id ,item_contents, item_price,item_name) VALUE(?, ?, ?, ?)");
			stmt.setInt(1, item.getCategoryId());
			stmt.setString(2, item.getItemContents());
			stmt.setInt(3, item.getItemPrice());
			stmt.setString(4, item.getItemName());
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
	//�븘�씠�뀥 �닔�젙�븸�뀡
	public void updateItem(Item item) {
		System.out.println(item.getItemId()+"<-----updateAction ItemId");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("UPDATE item SET shopdb_item_contents=?,item_price=?,item_name=? WHERE item_id=?");
			stmt.setString(1, item.getItemContents());
			stmt.setInt(2, item.getItemPrice());
			stmt.setString(3, item.getItemName());
			stmt.setInt(4, item.getItemId());
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
}
