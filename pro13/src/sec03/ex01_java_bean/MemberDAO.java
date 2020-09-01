package sec03.ex01_java_bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	PreparedStatement pstmt;
	Connection conn;
	DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List<MemberBean> listMember(){
		
		List<MemberBean> list = new ArrayList<MemberBean>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member order by joinDate desc";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery(query);
			while(rs.next()) {
				MemberBean member = new MemberBean(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("email"));
				member.setJoinDate(rs.getDate("joinDate"));
				list.add(member);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return list;
	}
	
	public void addMember(MemberBean member) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into t_member (id,pwd,name,email) values(?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
