package sec04.ex03_servlet_binding;

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
	
	private Connection conn;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<MemberVO> listMember() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member";
			pstmt = conn.prepareStatement(query);
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				MemberVO member = new MemberVO();
				member.setId(resultSet.getString("id"));
				member.setPwd(resultSet.getString("pwd"));
				member.setName(resultSet.getString("name"));
				member.setEmail(resultSet.getString("email"));
				member.setJoinDate(resultSet.getDate("joinDate"));
				list.add(member);
			}
			
			resultSet.close();
			pstmt.close();
			conn.close();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
		
	}
}
