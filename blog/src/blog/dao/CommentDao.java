package blog.dao;

import java.sql.*;
import java.util.*;

import blog.vo.*;
public class CommentDao {
	//댓글 하나 삭제
	public void DeleteComment(Connection conn, int commentNo, String commentContent) throws SQLException {
		System.out.println(commentNo+"<--dao commentNo");
		PreparedStatement stmt = null;
		String sql = "DELETE FROM blog_comment WHERE comment_no=? AND comment_content=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, commentNo);
			stmt.setString(2, commentContent);
			stmt.executeUpdate();	
		} finally {
			// TODO: handle finally clause
			stmt.close();
		}
	}
	//게시글 삭제시 같이 삭제
	public int DeleteComment(Connection conn, int postNo) throws SQLException {
		System.out.println(postNo+"<--dao postNo");
		PreparedStatement stmt = null;
		String sql = "DELETE FROM blog_comment WHERE post_no=?";
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
	public int countComment(Connection conn, int postNo) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="SELECT COUNT(*) FROM blog_comment WHERE post_no=?";
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			stmt.close();
			rs.close();
		}
		return count;
	}
	public void insertComment(Connection conn, Comment comment) throws SQLException {
		System.out.println(comment.getCommentContent());
		System.out.println(comment.getMemberId());
		System.out.println(comment.getPostNo());
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_comment(post_no, member_id, comment_content, comment_date) VALUE(?,?,?,now())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comment.getPostNo());
			stmt.setString(2, comment.getMemberId());
			stmt.setString(3, comment.getCommentContent());
			stmt.executeUpdate();
		} finally {
			// TODO: handle finally clause
			stmt.close();
		}
	}
	
	public List<Comment> selectCommentList(Connection conn , int postNo , int beginRow, int rowPerPage) throws SQLException{
		System.out.println(postNo+"<---postNo commentDao");
		List<Comment> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT c.* , m.member_level FROM blog_comment c INNER JOIN blog_member m ON c.member_id = m.member_id WHERE post_no=? limit ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();
			list = new ArrayList<Comment>();
			while(rs.next()) {
				Comment c = new Comment();
				c.setCommentNo(rs.getInt("c.comment_no"));
				c.setPostNo(rs.getInt("c.post_no"));
				c.setMemberId(rs.getString("c.member_id"));
				c.setCommentContent(rs.getString("c.comment_content"));
				c.setCommentDate(rs.getString("c.comment_date"));
				c.setMemberLevel(rs.getInt("m.member_level"));
				list.add(c);
				
			}
			System.out.println(list.size()+"<---list.size 확인");
			
		} finally {
			// TODO: handle finally clause
			stmt.close();
			rs.close();
		}
		
		return list;
	}
}
