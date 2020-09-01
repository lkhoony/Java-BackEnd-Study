package sec03.ex03_mvc_member_modify_delete;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sec03.ex03_mvc_member_modify_delete.MemberVO;

public class MemberDAO {
	
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup("java:comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List listMember()  {
		
		List memberList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member order by joinDate desc";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO memberVo = new MemberVO(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("email"), rs.getDate("joinDate"));
				memberList.add(memberVo);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return memberList;
	}
	
	public void addMember(MemberVO memberVo) {
		try {
			conn = dataFactory.getConnection();
			String query = "INSERT INTO t_member(id,pwd,name,email) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberVo.getId());
			pstmt.setString(2, memberVo.getPwd());
			pstmt.setString(3, memberVo.getName());
			pstmt.setString(4, memberVo.getEmail());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public MemberVO findMember(String enteredId) { // id를 입력받아 해당 회원정보를 탐색
		MemberVO memInfo = null;
		
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, enteredId);
			System.out.println(query);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			memInfo = new MemberVO(rs.getString("id"),rs.getString("pwd"),rs.getString("name"),rs.getString("email"),rs.getDate("joinDate"));
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return memInfo;
	}
	
	public void modMember(MemberVO memberVo) { // MemberVO를 입력받아 id로 회원을 찾아 해당 정보를 update
		
		String id = memberVo.getId();
		String pwd = memberVo.getPwd();
		String name = memberVo.getName();
		String email = memberVo.getEmail();
		
		try {
			conn = dataFactory.getConnection();
			String query = "update t_member set pwd=?,name=?,email=? where id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void delMember(String id) {
		
		try {
			conn = dataFactory.getConnection();
			String query = "delete from t_member where id=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, id);

			pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
