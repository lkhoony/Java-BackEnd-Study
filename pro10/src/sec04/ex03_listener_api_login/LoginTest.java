package sec04.ex03_listener_api_login;

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
 * Servlet implementation class LoginTest
 */
@WebServlet("/LoginTest")
public class LoginTest extends HttpServlet {
	
	ServletContext context = null;
	List<User> user_list = new ArrayList<User>();
	
	@Override
	public void init() throws ServletException {
		context = getServletContext();
		context.setAttribute("user_list",user_list);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		context = getServletContext();
		user_list = (ArrayList)context.getAttribute("user_list");
		
		HttpSession session = request.getSession();
		User enteredUser = null;

		if(session.isNew()) {
			
			for(User user : user_list) {
				if(user.getUser_id().equals(user_id)) {
					enteredUser = user;
				}
			}
			
			if(enteredUser!=null) {
				if(enteredUser.getUser_num()>=5) {
					session.invalidate();
					out.println("<html><body>");
					out.println("<div>동시접속 횟수를 초과하였습니다.</div>");
					out.println("<div>동시접속 수 : "+enteredUser.getUser_num()+"</div>");
					out.println("<a href='sec04ex03.html'>로그인 페이지로</a>");
					out.println("</body></html>");
				}else {
					session.setAttribute("user_id", user_id);
					session.setAttribute("user_pw", user_pw);
					session.setAttribute("isLogOn", true);
					enteredUser.setUser_num(enteredUser.getUser_num()+1);
					out.println("<html><body>");
					out.println("<div>로그인 되었습니다.</div>");
					out.println("<div>아이디 : " + session.getAttribute("user_id") +"</div>");
					out.println("<div>동시접속 수 : "+enteredUser.getUser_num()+"</div>");
					out.println("<a href='sec04ex03.html'>로그인 페이지로</a>");
					out.println("</body></html>");
				}
			}else {
				User newUser = new User();
				newUser.setUser_id(user_id);
				newUser.setUser_pw(user_pw);
				newUser.setUser_num(1);
				user_list.add(newUser);
				context.setAttribute("user_list", user_list);
				session.setAttribute("user_id", user_id);
				session.setAttribute("user_pw", user_pw);
				session.setAttribute("isLogOn", true);
				out.println("<html><body>");
				out.println("<div>로그인 되었습니다.</div>");
				out.println("<div>아이디 : " + session.getAttribute("user_id") +"</div>");
				out.println("<div>동시접속 수 : "+newUser.getUser_num()+"</div>");
				out.println("<a href='sec04ex03.html'>로그인 페이지로</a>");
				out.println("</body></html>");
			}
			
			
		}else {
			if((Boolean)session.getAttribute("isLogOn")) {
				out.println("<html><body>");
				out.println("<div>이미 로그인 하였습니다.</div>");
				out.println("<div>아이디 : " + session.getAttribute("user_id") +"</div>");
				out.println("<a href='sec04ex03.html'>로그인 페이지로</a>");
				out.println("</body></html>");
			}else {
				session.invalidate();
				response.sendRedirect("sec04ex03.html");
			}
		}
		
	}

}
