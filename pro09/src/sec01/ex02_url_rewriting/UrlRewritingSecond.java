package sec01.ex02_url_rewriting;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UrlRewritingSecond")
public class UrlRewritingSecond extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id=request.getParameter("user_id");
		String pw=request.getParameter("user_pw");
		String address=request.getParameter("user_address");

		if(id!=null && id.length()!=0) {
			out.println("<html><body>");
			out.println("<div>이미 로그인 한 상태입니다!</div>");
			out.println("<div>user_id : "+id+"</div>");
			out.println("<div>user_pw : "+pw+"</div>");
			out.println("<div>user_address : "+address+"</div>");
			out.println("</body></html>");
		}else {
			out.println("<html><body>");
			out.println("<div>로그인 하지 않았습니다.</div>");
			out.println("<div>다시 로그인 하세요!</div>");
			out.println("<a href='/pro09/LoginUrl.html'>로그인 창으로 이동</a>");
			out.println("</body></html>");
		}

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}
}
