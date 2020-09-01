package sec06.ex01_session_db_login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ShowMember")
public class ShowMember extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		Boolean isLogon = false;
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			isLogon = (Boolean)session.getAttribute("isLogon");
			if(isLogon == true) {
				String id=(String)session.getAttribute("user_id");
				String pw=(String)session.getAttribute("user_pwd");
				out.println("<html><body>");
				out.println("<div>id : "+id+"</div>");
				out.println("<div>pw : "+pw+"</div>");
				out.println("</body></html>");
			}else {
				response.sendRedirect("SessionLogin.html");
			}
		}else {
			response.sendRedirect("SessionLogin.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
