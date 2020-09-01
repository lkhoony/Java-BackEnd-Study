package sec01.ex01_hidden_session_tracking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/HiddenSession")
public class LoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id=request.getParameter("user_id");
		String pw=request.getParameter("user_pw");
		String address=request.getParameter("user_address");
		String email=request.getParameter("user_email");
		String hp=request.getParameter("user_hp");
		
		out.println("<html><body>");
		out.println("<div>user_id : "+id+"</div>");
		out.println("<div>user_pw : "+pw+"</div>");
		out.println("<div>user_address : "+address+"</div>");
		out.println("<div>user_email : "+email+"</div>");
		out.println("<div>user_hp : "+hp+"</div>");
		out.println("</body></html>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
