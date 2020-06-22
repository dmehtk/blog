package model;

import java.sql.*;
import java.util.*;

import commoms.DBUtil;
import vo.*;

public class MemberDao {
	
	public void insertMember(Member member) {
		System.out.println(member.getMemberId()+"<---InsertMember memberId");
		System.out.println(member.getMemberPw()+"<---InsertMember memberPw");
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO shopdb_member(member_id, member_pw) VALUE(?, ?)");
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(null, stmt, conn); // select
		}
	}
	
	public List<Member> selectMemberALL(){
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<Member> list = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT member_id, member_pw FROM shopdb_member");
			rs = stmt.executeQuery();
			list = new ArrayList<Member>();
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				list.add(member);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stmt, conn); // select
		}
		return list;
	}

	
	public void deleteMember(String memberId) {
		System.out.println(memberId+"<---deleteMember");
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("DELETE FROM shopdb_member WHERE member_id=?");
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(null, stmt, conn); // select
		}
	}
	public Member login(Member member) {
		Member returnmember = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("select * from shopdb_member where member_id=? and member_pw=?");
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnmember = new Member();
				returnmember.setMemberId(rs.getString("member_id"));
				returnmember.setMemberPw(rs.getString("member_pw"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stmt, conn); // select
			//DBUtil.close(null, stmt, conn)
		}
		
		return returnmember;
	}
	
	public Member selectMemberOne(String memberId){
		System.out.println(memberId+"<---selectMemberOne");
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Member member = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("SELECT member_id, member_pw FROM shopdb_member WHERE member_id=?");
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			member = new Member();
			if(rs.next()) {
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, stmt, conn); // select
		}
		return member;
	}
	
	public void updateMember(Member member) {
		System.out.println(member.getMemberId()+"<---updateMember");
		System.out.println(member.getMemberPw()+"<---UpdateMember");
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("UPDATE shopdb_member SET member_pw=? WHERE member_id=?");
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(null, stmt, conn); // select
		}
	}
}
