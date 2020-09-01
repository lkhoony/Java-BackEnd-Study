package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/input2")
public class LoginServlet2 extends HttpServlet {

	public void init() throws ServletException {
		System.out.println("init 매서드 호출");
	}

	public void destroy() {
		System.out.println("destroy 매서드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration enu=request.getParameterNames();
		
		while(enu.hasMoreElements()) {
			
			String name=(String)enu.nextElement();
			System.out.println(name);
			
			String[] values=request.getParameterValues(name);
			
			for(String value : values) {
				System.out.println("name : " + name + " value : " + value);
			}

		}
	}

}
