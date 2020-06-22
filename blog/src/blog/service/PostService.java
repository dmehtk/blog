package blog.service;

import java.sql.*;
import java.util.*;

import blog.commons.DBUtil;
import blog.dao.*;
import blog.vo.*;

public class PostService {
	private PostDao postDao;
	private CommentDao commentDao;
	private LikeyDao likeyDao;
	//게시물 삭제 + 트랜잭션
	public void deletePostByComment(int postNo, String memberId) {
		System.out.println(postNo+"<--postNo service");
		System.out.println(memberId+"<--memberId service");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			this.commentDao = new CommentDao();
			this.postDao = new PostDao();
			this.likeyDao = new LikeyDao();
			int commentRow = this.commentDao.DeleteComment(conn, postNo);
			System.out.println(commentRow+"<--row 값");
			int likeyRow = this.likeyDao.deleteLikey(conn, postNo);
			System.out.println(likeyRow);
				this.postDao.deletePost(conn, postNo);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback(); // 예외상황 발생시 롤백
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// 포스트 수정
	public void updatePost(Post post) {
		System.out.println(post.getPostNo()+"<--postNo service");
		System.out.println(post.getPostTitle()+"<--postTitle service");
		System.out.println(post.getPostContent()+"<--postContent service");
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.postDao = new PostDao();
			this.postDao.updatePost(conn, post);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 게시글 추가
	public void insertPostBySubject(Post post) {
		System.out.println(post.getMemberId()+"<---memberId service");
		System.out.println(post.getSubjectName()+"<---subjectName service");
		System.out.println(post.getPostTitle()+"<---postTitle service");
		System.out.println(post.getPostContent()+"<---postContent service");
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.postDao = new PostDao();
			this.postDao.insertPostBySubject(conn, post);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	//게시글 페이징
	public Map<String, Object> getPostBySubject(int currentPage, int rowPerPage , String subjectName){
		System.out.println(subjectName+"<--subjectName service");
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage;
			this.postDao = new PostDao();
			List<Post> list = new ArrayList<Post>();
			list = this.postDao.selectPostBySubject(conn, subjectName, beginRow, rowPerPage);
			int count = this.postDao.countPostBySubject(conn, subjectName);
			int lastPage = count/rowPerPage;
			if(count%rowPerPage != 0) {
				lastPage+=1;
			}
			map.put("list", list);
			map.put("lastPage", lastPage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return map;
	}
	
	//post selectOne
	public Post getPostOne(int postNo) {
		System.out.println(postNo+"<---postService postNo");
		Post post = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.postDao = new PostDao();
			post = this.postDao.selectPostOne(postNo, conn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		return post;
	}
	//post list
	public Map<String, Object> getPostList(int currentPage, int rowPerPage){
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage;
			this.postDao = new PostDao();
			List<Post> list = new ArrayList<Post>();
			list = this.postDao.selectPostListALL(conn, beginRow, rowPerPage);
			int count = this.postDao.countPost(conn);
			int lastPage = count/rowPerPage;
			if(count%rowPerPage != 0) {
				lastPage+=1;
			}
			map.put("list", list);
			map.put("lastPage", lastPage);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return map;
	}
}
