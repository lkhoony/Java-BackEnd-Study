package sec12.ex01_el_jstl_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addMember(MemberBean memberBean) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into t_member(id,pwd,name,email) values(?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getPwd());
			pstmt.setString(3, memberBean.getName());
			pstmt.setString(4, memberBean.getEmail());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public List<MemberBean> memberList(){
		
		List<MemberBean> memberList = new ArrayList<MemberBean>();
		
		try {
			String query = "select * from t_member";
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberBean member = new MemberBean(rs.getString("id"),rs.getString("pwd"),rs.getString("name"),rs.getString("email"));
				member.setJoinDate(rs.getDate("joinDate"));		
				memberList.add(member);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return memberList;
	}
}
