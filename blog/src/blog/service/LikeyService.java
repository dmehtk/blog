package blog.service;

import java.sql.Connection;
import java.sql.SQLException;

import blog.commons.DBUtil;
import blog.dao.CommentDao;
import blog.dao.LikeyDao;
import blog.vo.Likey;

public class LikeyService {
	private LikeyDao likeyDao;
	public void addHatey(Likey likey) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.likeyDao = new LikeyDao();
			if(this.likeyDao.isInsertHatey(conn, likey)) {
				System.out.println("싫어요 실행.");
				this.likeyDao.isInsertHatey(conn, likey);
			}else {
				System.out.println("이미 싫어요를 했습니다. 실행 x");
				return;
			}
			this.likeyDao.insertHatey(conn, likey);
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
	public void addLikey(Likey likey) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.likeyDao = new LikeyDao();
			if(this.likeyDao.isInsertLikey(conn, likey)) {
				System.out.println("좋아요 실행.");
				this.likeyDao.isInsertLikey(conn, likey);
			}else {
				System.out.println("이미 좋아요를 했습니다. 실행 x");
				return;
			}
			this.likeyDao.insertLikey(conn, likey);
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
	
	public int getLikeyCount(int postNo) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			this.likeyDao = new LikeyDao();
			count = this.likeyDao.selectLikeyCount(conn, postNo);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	public int getHateyCount(int postNo) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			this.likeyDao = new LikeyDao();
			count = this.likeyDao.selectHateyCount(conn, postNo);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
}
