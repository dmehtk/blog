package model;

import java.sql.DriverManager;
import java.util.*;
import vo.*;
import java.sql.*;
public class CategoryDao {
	//移댄뀒怨좊━ �닔�젙
	public void updateCategory(Category c) {
		System.out.println(c.getCategoryId()+"<----categoryId updateCategoryDao");
		System.out.println(c.getCategoryName()+"<----categoryName");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("UPDATE shopdb_category SET category_name=? WHERE category_id=?");
			stmt.setString(1, c.getCategoryName());
			stmt.setInt(2, c.getCategoryId());
			stmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//移댄뀒怨좊━ �닔�젙�뤌
	public Category selectCategoryOne(int categoryId) {
		System.out.println(categoryId+"<---selectCategoryDao  ---- categoryId");
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Category category = null;
		try {
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
		stmt = conn.prepareStatement("select * from shopdb_category WHERE category_id=?");
		stmt.setInt(1, categoryId);
		rs = stmt.executeQuery();
		category = new Category();
		if(rs.next()) {
			category.setCategoryId(rs.getInt("category_id"));
			category.setCategoryName(rs.getString("category_name"));
		}
		}catch(Exception e) {
			System.out.println("categorydao �삁�쇅 諛쒖깮");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return category;
	}
	//移댄뀒怨좊━ 由ъ뒪�듃
	public ArrayList<Category> selectCategoryList(){
		ArrayList<Category> list = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
		stmt = conn.prepareStatement("select * from shopdb_category");
		rs = stmt.executeQuery();
		list = new ArrayList<Category>();
		while(rs.next()) {
			Category category = new Category();
			category.setCategoryId(rs.getInt("category_id"));
			category.setCategoryName(rs.getString("category_name"));
			list.add(category);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//移댄뀒怨좊━ 異붽�
	public void insertCategory(Category category) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			System.out.println(category.getCategoryName()+"<--category");
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
			stmt = conn.prepareStatement("insert into shopdb_category(category_name) value(?)");
			stmt.setString(1, category.getCategoryName());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace(); // 肄섏넄李쎌뿉 �삁�쇅硫붿꽭吏� 異쒕젰
		}finally {
			try {
				conn.close();
				stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
