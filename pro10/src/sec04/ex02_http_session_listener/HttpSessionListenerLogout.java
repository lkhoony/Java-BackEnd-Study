package sec04.ex02_http_session_listener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HttpSessionListenerLogout
 */
@WebServlet("/HttpSessionListenerLogout")
public class HttpSessionListenerLogout extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ServletContext context = getServletContext();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");

		session.invalidate();
		
		List list = (ArrayList) context.getAttribute("user_list");
		list.remove(user_id);
		context.removeAttribute("user_list");
		context.setAttribute("user_list", list);
		
		out.println("<html><body>");
		out.println("<div>로그아웃 됬습니다.</div>");
		out.println("</body></html>");
		
	
		
	}

}
