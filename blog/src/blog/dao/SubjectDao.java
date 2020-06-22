package blog.dao;

import java.sql.*;
import java.util.*;

import blog.commons.DBUtil;
import blog.vo.*;

public class SubjectDao {
	public List<Subject> selectSubjectListAll(){
		List<Subject> list = new ArrayList<Subject>();//size -> 0
		String sql = "SELECT subject_name FROM blog_subject ORDER BY subject_name ASC";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Subject s = new Subject();
				s.setSubjectName(rs.getString("subject_name"));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, stmt, conn);
		}		return list;
	}
}
