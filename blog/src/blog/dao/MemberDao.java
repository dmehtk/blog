package blog.dao;

import java.sql.*;
import java.util.*;

import blog.commons.DBUtil;
import blog.vo.*;

public class MemberDao {
	public void updateMember(Connection conn, Member member) throws SQLException {
		System.out.println(member.getMemberId()+"<--memberId memberDao");
		System.out.println(member.getMemberLevel()+"<---memberLevel memberDao");
		PreparedStatement stmt = null;
		String sql = "UPDATE blog_member SET member_level=? WHERE member_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, member.getMemberLevel());
			stmt.setString(2, member.getMemberId());
			stmt.executeUpdate();
		} finally {
			// TODO: handle finally clause
			try {
				stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public int countMember(Connection conn) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM blog_MEMBER";
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}			
		}
		return count;
	}
	public List<Member> selectMemberList(Connection conn, int beginRow, int rowPerPage){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT member_id, member_level FROM blog_member limit ?,?";
		List<Member> list = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			list = new ArrayList<Member>();
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberLevel(rs.getInt("member_level"));
				list.add(member);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	//before 회원가입전에 id 사용 여부
	//아이디를 사용가능 true -- 현재 없다
	//아이디를 사용 불가 false - 현재 있다
	public boolean selectMemberId(String memberId , Connection conn) throws SQLException {
		System.out.println(memberId+"<---selectMemberId boolean memberId");
		boolean flag = true;
		PreparedStatement stmt = null;
		//UNION , Subquery , JOIN 
		String sql1 = "SELECT mi FROM (SELECT member_id mi FROM blog_member UNION SELECT memberid mi FROM blog_memberid) t where t.mi=? ";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}
					
		}finally {
			stmt.close();
			rs.close();
		}
		return flag;
		
	}
	
	public void insertMember(Member member , Connection conn) throws SQLException {
		System.out.println(member.getMemberId()+"<---InsertMember memberId");
		System.out.println(member.getMemberPw()+"<---InsertMember memberPw");
		PreparedStatement stmt = null;
		String sql = "INSERT INTO blog_member(member_id, member_pw,member_date) VALUE(?, ?, now())";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			stmt.close();
		}
		
	}
	//회원 탈퇴시 삭제
	public int deleteMember(Member member, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM blog_member WHERE member_id=? AND member_pw=?";
		int row = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate(); //행을 삭제한 수만큼 로우값 +1
		}finally {
			stmt.close();
		}
		
		return row; //삭제한 행의 수를 리턴받음
	}
	public Member selectMemberOne(Connection conn , String memberId) {
		Member member = null;
		String sql = "SELECT * FROM blog_member WHERE member_id=? ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberNo(rs.getInt("member_no"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberLevel(rs.getInt("member_level"));
				member.setMemberDate(rs.getString("member_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return member;
	}
	
	public Member selectMemberOne(Connection conn , Member member) {
		System.out.println(member.getMemberId()+"<--MemberDao memberId");
		System.out.println(member.getMemberPw()+"<--MemberDao memberPw");
		Member returnMember = null;
		String sql = "SELECT * FROM blog_member WHERE member_id=? AND member_pw=? ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("member_id"));
				returnMember.setMemberLevel(rs.getInt("member_level"));
				returnMember.setMemberPw(rs.getString("member_pw"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return returnMember;
	}
	
}
