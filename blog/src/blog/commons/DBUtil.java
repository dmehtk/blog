package blog.commons;

import java.sql.*;
public class DBUtil {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://localhost/dmehtk", "dmehtk", "java1004");
		return conn;
	}
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if(rs != null) {
			try{
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try{
				stmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try{
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
