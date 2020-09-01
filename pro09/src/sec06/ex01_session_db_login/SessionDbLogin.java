package sec06.ex01_session_db_login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SessionDbLogin")
public class SessionDbLogin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doHandle(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		
		boolean result = dao.isExistend(memberVO); // db에 존재하는지 확인
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("user_id", user_id);
			session.setAttribute("user_pwd", user_pwd);
			out.println("<html><body>");
			out.println("<div>"+user_id+"님 어서오세요!</div>");
			out.println("<a href='ShowMember'>회원정보 보기</a>");
			out.println("</body></html>");
		}else {
			out.println("<html><body>");
			out.println("<div>회원 정보가 틀립니다.</div>");
			out.println("<a href='SessionLogin.html'>다시 로그인하기</a>");
			out.println("</body></html>");
		}
	}
	
}
