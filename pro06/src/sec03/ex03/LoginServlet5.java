package sec03.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet5
 */
@WebServlet("/login5")
public class LoginServlet5 extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init �޼ҵ� ȣ��");
	}


	public void destroy() {
		System.out.println("destroy �޼ҵ� ȣ��");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet �޼ҵ� ȣ��");
		doHandle(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost �޼ҵ� ȣ��");
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		System.out.println("doHandle �޼ҵ� ȣ��");
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out=response.getWriter();
		String id=request.getParameter("user_id");
		String pw=request.getParameter("user_pw");
		String address=request.getParameter("address");
		
		String data="<html>";
		data+="<body>";
		data+="<div>user_id :";
		data+=id;
		data+="</div>";
		data+="<div>user pw : ";
		data+=pw;
		data+="</div>";
		data+="<div>user address : ";
		data+=address;
		data+="</div>";
		data+="</body>";
		data+="</html>";
		
		out.print(data);
	} 
}
