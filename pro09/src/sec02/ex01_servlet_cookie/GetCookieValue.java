package sec02.ex01_servlet_cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetCookieValue")
public class GetCookieValue extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Cookie[] allVaues = request.getCookies();
		// 브라우저에게 쿠키 정보를 요청한 후 배열로 가져옴
		int cookieLen = allVaues.length;
		for(int i=0;i<cookieLen;i++) {
			if(allVaues[i].getName().equals("cookieTest")) {
				// 쿠키 이름으로 쿠키값을 가져옴
				out.println("<h2>cookie 값 가져오기 : "+URLDecoder.decode(allVaues[i].getValue(),"utf-8")+"</h2>");
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
