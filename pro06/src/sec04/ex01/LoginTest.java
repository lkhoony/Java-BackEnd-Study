package sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logintest")
public class LoginTest extends HttpServlet {
	
    
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드호출");
	}


	public void destroy() {
		System.out.println("destroy 메소드호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 메소드호출");
		doHandle(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost 메소드호출");
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doHandle 메소드호출");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id=request.getParameter("user_id");
		String pw=request.getParameter("user_pw");
		
		PrintWriter out=response.getWriter();
		
		if(id!=null && id.length()!=0) {
			out.print("<html>");
			out.print("<body>");
			out.print(id+"님 로그인되었습니다.");
			out.print("</body>");
			out.print("</html>");
		}else {
			out.print("<html>");
			out.print("<body>");
			out.print("아이디를 입력하세요.");
			out.print("<a href='http://127.0.0.1:8090/pro06/test01/login.html'>로그인 창으로 이동");
			out.print("</body>");
			out.print("</html>");
		}
	}
	
}
