package sec02.ex01_data_source;

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

			//connDB();
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
	
	/*
	private void connDB() {
		try {
			Class.forName(driver); 
			// 드라이버 메모리에 로드
			// driver는 static이기 때문에 인스턴스 관리를 하지 않음
			// Class.forName은 객체의 인스턴스를 만들어서 그 객체의 정보들을 얻어옴 >> 만들어진 인스턴스 객체를 참조하는 변수가 없어 gabbage collector에 의해 처리됨
			// 하지만 driver는 인스턴스를 정적블록을 통해 생성하고 관리함
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
}
