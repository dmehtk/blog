package blog.dao;

import java.sql.*;

import blog.vo.*;

public class LikeyDao {
	public int deleteLikey(Connection conn, int postNo) throws SQLException {
		System.out.println(postNo+"<--likeyDao postNo");
		PreparedStatement stmt = null;
		String sql = "DELETE blog_FROM likey WHERE post_no=?";
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			row = stmt.executeUpdate();
			
		} finally {
			// TODO: handle finally clause
			stmt.close();
		}
		return row;
	}
	public void insertHatey(Connection conn, Likey likey) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_likey(post_no, member_id, likey_ck, likey_date) VALUE(?,?,0,now())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			stmt.executeUpdate();
		} finally {
			// TODO: handle finally clause
			stmt.close();
		}
	}
	//좋아요 가능하면 true 불가능하면 false
		public boolean isInsertLikey(Connection conn, Likey likey) throws SQLException {
			boolean flag = true;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM blog_likey WHERE post_no=? AND member_id=? AND likey_ck=1";
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, likey.getPostNo());
				stmt.setString(2, likey.getMemberId());
				rs = stmt.executeQuery();
				if(rs.next()) {
					flag = false;
				}
			} finally {
				// TODO: handle finally clause
				stmt.close();
				rs.close();
			}
			return flag;
		}
		public boolean isInsertHatey(Connection conn, Likey likey) throws SQLException {
			boolean flag = true;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM blog_likey WHERE post_no=? AND member_id=? AND likey_ck=0";
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, likey.getPostNo());
				stmt.setString(2, likey.getMemberId());
				rs = stmt.executeQuery();
				if(rs.next()) {
					flag = false;
				}
			} finally {
				// TODO: handle finally clause
				stmt.close();
				rs.close();
			}
			return flag;
		}
	public void insertLikey(Connection conn, Likey likey) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_likey(post_no, member_id, likey_ck, likey_date) VALUE(?,?,1,now())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			stmt.executeUpdate();
		} finally {
			// TODO: handle finally clause
			stmt.close();
		}
	}
	public int selectLikeyCount(Connection conn, int postNo) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(likey_ck) FROM blog_likey WHERE post_no=? AND likey_ck=1";
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			// TODO: handle finally clause
			stmt.close();
			rs.close();
		}
		return count;
	}
	public int selectHateyCount(Connection conn, int postNo) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(likey_ck) FROM blog_likey WHERE post_no=? AND likey_ck=0";
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			// TODO: handle finally clause
			stmt.close();
			rs.close();
		}
		return count;
	}
}
