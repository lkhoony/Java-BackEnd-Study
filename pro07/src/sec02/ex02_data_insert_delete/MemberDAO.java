package sec02.ex02_data_insert_delete;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // 인터페이스
import java.sql.SQLException;
import java.sql.Statement; // 인터페이스
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	/*
	private static final String driver = 
			"oracle.jdbc.driver.OracleDriver";
	private static final String url=
			"jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="scott";
	private static final String pwd="tiger";
	*/
	
	private Connection con; // 데이터베이스 연결을 위한 인터페이스
	private PreparedStatement pstmt; // sql을 실행할 수 있는 인터페이스
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			// DataSource에 접근하기 위해 JNDI 사용 
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle"); // 톰켓이 미리 만들어놓은 DataSource를 가져오기 위함(jdbc/oracle은 name)
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public List<MemberVO> listMembers(){
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {

			con = dataFactory.getConnection();
			String query = "select * from t_member";
			System.out.println("prepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
				
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void addMember(MemberVO memberVO) {
		try {
			con=dataFactory.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			String query = "insert into t_member";
			query += " (id,pwd,name,email)";
			query += " values(?,?,?,?)";
			// prepareStatement에서는 값이 들어갈 자리를 ?로 표시함
			System.out.println("prepareStatement : "+ query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void delMember(String id) {
		
		try {
			con=dataFactory.getConnection();
			String query = "delete from t_member where id=?";
			System.out.println("prepareStatement : "+query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
