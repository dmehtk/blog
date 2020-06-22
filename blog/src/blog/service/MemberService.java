package blog.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import blog.commons.DBUtil;
import blog.dao.*;
import blog.vo.*;

public class MemberService {
	private MemberidDao memberidDao;
	private MemberDao memberDao;
	
	public void updateMember(Member member) {
		System.out.println(member.getMemberId()+"<--service memberId");
		System.out.println(member.getMemberLevel()+"<---service memberLevel");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.memberDao = new MemberDao();
			this.memberDao.updateMember(conn, member);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public Map<String, Object> getMemberList(int currentPage , int rowPerPage){
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		this.memberDao = new MemberDao();
		List<Member> list = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage;
			list = this.memberDao.selectMemberList(conn, beginRow, rowPerPage);
			int count = this.memberDao.countMember(conn);
			int lastPage =  count/rowPerPage;
			if(count%rowPerPage != 0) {
				lastPage +=1;
			}
			map.put("list", list);
			map.put("lastPage", lastPage);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return map;
	}
	
	//true : 회원가입 가능한 id
	//false : 회원가입 불가한 id
	public boolean addMember(Member member) {
		memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if(this.memberDao.selectMemberId(member.getMemberId(), conn)) {
				this.memberDao.insertMember(member, conn);
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBUtil.close(null, null, conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public void removeMember(Member member) {
		System.out.println(member.getMemberId()+" memberService memberId");
		System.out.println(member.getMemberPw()+" memberService memberPw");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false); // 기본 커밋은 true , commit 명령전까지는 실행이 안되게함
			this.memberDao = new MemberDao();
			this.memberidDao = new MemberidDao();
			int row = memberDao.deleteMember(member, conn);
			System.out.println(row + "<---removeMember row=?");
			if(row == 1) { // 삭제 성공시
				memberidDao.insertMemberid(member.getMemberId(), conn);
			}
			conn.commit(); // 내용 다 확인후 commit
		}catch (Exception e) {
			try {
				conn.rollback(); // 예외상황 발생시 롤백
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			
		}finally {
			try {
				DBUtil.close(null, null, conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public Member getMemberOne(Member member) {
		System.out.println(member.getMemberId()+"memberService memberId");
		System.out.println(member.getMemberPw()+"memberService memberPw");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.memberDao = new MemberDao();
			member = this.memberDao.selectMemberOne(conn, member);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return member;
	}
	public Member getMemberOne(String memberId) {
		System.out.println(memberId+"<--MemberService memberId");
		Connection conn = null;
		Member member = null;
		try {
			conn = DBUtil.getConnection();
			this.memberDao = new MemberDao();
			member = this.memberDao.selectMemberOne(conn, memberId);
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return member;
	}
}
