package sec01.ex02_url_rewriting;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UrlRewriting")
public class UrlRewriting extends HttpServlet {
	
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
		out.println("<div>user_id : "+"안녕하세요! 로그인하셨습니다."+"</div>");
		out.println("<div>user_id : "+id+"</div>");
		out.println("<div>user_pw : "+pw+"</div>");
		out.println("<div>user_address : "+address+"</div>");
		out.println("<div>user_email : "+email+"</div>");
		out.println("<div>user_hp : "+hp+"</div>");
		
		address = URLEncoder.encode(address, "utf-8"); // address를 get방식전송을 위해 utf-8로 인코딩
		out.println("<a href='/pro09/UrlRewritingSecond?user_id=" + id
				+"&user_pw=" + pw
				+"&user_address="+address
				+"'>두 번째 서블릿으로 보내기</a>");
		
		out.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 메서드 호출");
	}
}
