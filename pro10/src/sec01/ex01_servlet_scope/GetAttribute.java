package sec01.ex01_servlet_scope;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/GetAttribute")
public class GetAttribute extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ServletContext ctx = getServletContext();
		HttpSession sess = request.getSession();
		
		String ctxMsg = (String)ctx.getAttribute("context");
		String sesMsg = (String)sess.getAttribute("session");
		String reqMsg = (String)request.getAttribute("request");
		
		out.println("<div>context 값 : " + ctxMsg + "</div>");
		out.println("<div>session 값 : " + sesMsg + "</div>");
		out.println("<div>request 값 : " + reqMsg + "</div>");
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
