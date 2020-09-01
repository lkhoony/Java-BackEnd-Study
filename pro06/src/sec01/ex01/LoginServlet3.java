package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/input3")
public class LoginServlet3 extends HttpServlet {
	
    
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드호출");
	}

	public void destroy() {
		System.out.println("destroy 메소드호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		String id=request.getParameter("user_id");
		String pw=request.getParameter("user_pw");
		
		String data="<html>";
		data += "<body>";
		data += "user id : " + id;
		data += "<br>";
		data += "user pw : " + pw;
		data += "</body>";
		data += "</html>";
		
		out.print(data);
	}

}
