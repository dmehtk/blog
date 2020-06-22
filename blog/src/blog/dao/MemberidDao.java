package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import blog.vo.*;

public class MemberidDao {
	// 회원 탈퇴시 이 테이블에 (삭제)된 아이디를 추가
	public void insertMemberid(String memberid , Connection conn) throws SQLException {
		System.out.println(memberid+"<---memberidDao memberid");
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_memberid(memberid, memberid_date) VALUES(?, now())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberid);
			stmt.executeUpdate(); 
		}finally {
			stmt.close();
		}
		
	}
}
