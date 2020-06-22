package blog.dao;

import java.sql.*;
import java.util.*;

import blog.vo.*;

public class PostDao {
	public void deletePost(Connection conn, int postNo) throws SQLException {
		System.out.println(postNo+"<--dao postNo");
		PreparedStatement stmt = null;
		String sql = "DELETE FROM blog_post WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			stmt.executeUpdate();
		} finally {
			// TODO: handle finally clause
			stmt.close();
		}
	}
	public void updatePost(Connection conn, Post post) throws SQLException {
		System.out.println(post.getPostNo()+"<---dao postNo");
		System.out.println(post.getPostTitle()+"<--dao postTitle");
		System.out.println(post.getPostContent()+"<---dao postContent");
		PreparedStatement stmt = null;
		String sql = "UPDATE blog_post SET post_title=? , post_content=? , post_date=now() WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, post.getPostTitle());
			stmt.setString(2, post.getPostContent());
			stmt.setInt(3, post.getPostNo());
			stmt.executeUpdate();
		} finally {
			// TODO: handle finally clause
			stmt.close();
		}
		
	}
	
	public void insertPostBySubject(Connection conn ,Post post) throws SQLException {
		System.out.println(post.getMemberId()+"<---memberId servlet");
		System.out.println(post.getSubjectName()+"<---subjectName servlet");
		System.out.println(post.getPostTitle()+"<---postTitle servlet");
		System.out.println(post.getPostContent()+"<---postContent servlet");
		
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_post(member_id, subject_name, post_title, post_content, post_date) VALUE(?,?,?,?,NOW())";
		try {
			stmt = conn.prepareStatement(sql);
				stmt.setString(1, post.getMemberId());
				stmt.setString(2, post.getSubjectName());
				stmt.setString(3, post.getPostTitle());
				stmt.setString(4, post.getPostContent());
			stmt.executeUpdate();
		} finally {
			// TODO: handle finally clause
			stmt.close();
		}
	}
	
	public int countPostBySubject(Connection conn, String subjectName) throws SQLException {
		System.out.println(subjectName+"<---subjectName");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) from blog_post WHERE subject_name=?";
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subjectName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}finally {
			try {
				stmt.close();
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return count;
	}
	//subject 리스트
	public List<Post> selectPostBySubject(Connection conn, String subjectName , int beginRow, int rowPerPage) throws SQLException{
		List<Post> list = new ArrayList<Post>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT post_no, member_id, subject_name, post_title, post_content, post_date FROM blog_post WHERE subject_name=? limit ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subjectName);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostDate(rs.getString("post_date"));
				list.add(post);
			}
		}finally {
			try {
				
			}finally {
				stmt.close();
				rs.close();
			}
		}
		
		return list;
	}
	//게시물 상세정보
	public Post selectPostOne(int postNo, Connection conn) throws SQLException {
		System.out.println(postNo+"<---PostDao postNo");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Post post = null;
		String sql="SELECT post_no, member_id, subject_name, post_title, post_content, post_date FROM blog_post WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				post = new Post();
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostDate(rs.getString("post_date"));
			}
		}finally {
			try {
				stmt.close();
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return post;
	}
	//페이징 행의 수
	public int countPost(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) from blog_post";
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}finally {
			try {
				stmt.close();
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return count;
	}
	//포스트 리스트
	public List<Post> selectPostListALL(Connection conn, int beginRow, final int rowPerPage) throws SQLException{
		List<Post> list = new ArrayList<Post>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT post_no, member_id, subject_name, post_title, post_date FROM blog_post limit ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostDate(rs.getString("post_date"));
				list.add(post);
			}
		}finally {
			try {
				
			}finally {
				stmt.close();
				rs.close();
			}
		}
		
		return list;
		
	}
}
