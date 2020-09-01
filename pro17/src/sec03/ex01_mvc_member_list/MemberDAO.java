package sec03.ex01_mvc_member_list;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	public List MemberList()  {
		
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
	
}
