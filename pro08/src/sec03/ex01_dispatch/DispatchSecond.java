package sec03.ex01_dispatch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DispatchSecond")
public class DispatchSecond extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Iterator<String> iterator = request.getParameterNames().asIterator();
		out.println("<html><body>");
		out.println("<div>두번째 dispatch 서블릿</div>");
		while(iterator.hasNext()) {
			String name = iterator.next();
			out.println("<div>" + name+" : " + request.getParameter(name)+"</div>");
		}
		
		out.println("</body></html>");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
