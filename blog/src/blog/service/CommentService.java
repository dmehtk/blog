package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import blog.commons.DBUtil;
import blog.dao.*;
import blog.vo.*;

public class CommentService {
	private CommentDao commentDao;
	public void deleteComment(int commentNo, String commentContent) {
		System.out.println(commentNo+"<--commentNo commentService");
		System.out.println(commentContent+"<--commentContent service");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.commentDao = new CommentDao();
			this.commentDao.DeleteComment(conn, commentNo, commentContent);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Map<String, Object> getComment(int postNo, int currentPage, int rowPerPage){
		System.out.println(postNo+"<--postNo commentService");
		System.out.println(currentPage+"<--currentPage");
		System.out.println(rowPerPage+"<---rowPerPage");
		List<Comment> list = new ArrayList<Comment>();
		Map<String, Object> map = new HashMap<String, Object>();
		int count = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage;
			this.commentDao = new CommentDao();
			list = this.commentDao.selectCommentList(conn, postNo, beginRow, rowPerPage);
			count = this.commentDao.countComment(conn, postNo);
			int lastPage = count/rowPerPage;
			if(count%rowPerPage != 0) {
				lastPage+=1;
			}
			map.put("list", list);
			map.put("lastPage", lastPage);
			System.out.println(list.size()+"<---service list.size");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return map;
	}
	public void addComment(Comment comment) {
		System.out.println(comment.getPostNo()+"<--service");
		System.out.println(comment.getMemberId()+"<--service");
		System.out.println(comment.getCommentContent()+"<--service");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.commentDao = new CommentDao();
			this.commentDao.insertComment(conn, comment);	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
				
	} 
}
