package sec04.ex04_delete_session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.println("<div>세션 아이디 : " + session.getId()+"</div>");
		out.println("<div>최초 세션 생성 시각 : " + new Date(session.getCreationTime())+"</div>");
		out.println("<div>최근 세션 접근 시각 : " + new Date(session.getLastAccessedTime())+"</div>");
		out.println("<div>기본 세션 유효 시간 : " + session.getMaxInactiveInterval()+"</div>");
		
		if(session.isNew()) {
			out.println("새 세션이 만들어졌습니다.");
		}
		
		session.invalidate();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
