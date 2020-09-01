package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalServlet
 */
@WebServlet("/calc")
public class CalServlet extends HttpServlet {
	
    
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드 호출");
	}

	
	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String command = request.getParameter("command");
		String won = request.getParameter("won");
		String operator = request.getParameter("operator");
		
		if(command != null && command.equals("calculate")) {
			
			String result=calculate(Float.parseFloat(won),operator);
			
			pw.print("<html>");
			pw.print("<head>");
			pw.print("<title> 변환결과");
			pw.print("</title>");
			pw.print("</head>");
			pw.print("<body>");
			pw.print("<div style='font-size : 10;'> 변환결과 </div>");
			pw.print("<div>" + result + "</div>");
			pw.print("<a href='/pro06/calc'> 환율계산기 </a>");
			
			pw.print("</body>");
			
			pw.print("</html>");
			
			return;
		}
		
		pw.print("<html>");
		pw.print("<head>");
		pw.print("<title> 환율계산기");
		pw.print("</title>");
		pw.print("</head>");
		pw.print("<body>");
		pw.print("<div>환율 계산기</div>");
		pw.print("<form name='frmCalc' method='get' action='/pro06/calc'>");
		pw.print("<label for=text>원화</label>");
		pw.print("<input id='text' type='text' name='won' size=10/>");
		
		pw.print("<select name='operator'>");
		pw.print("<option value='dollar'>달러</option>");
		pw.print("<option value='en'>엔화</option>");
		pw.print("<option value='wian'>위안</option>");
		pw.print("<option value='pound'>파운드</option>");
		pw.print("<option value='euro'>유로</option>");
		pw.print("</select>");
		
		pw.print("<input type='hidden' name='command' value='calculate'/>");
		pw.print("<input type='submit' value='변환'/>");
		
		pw.print("</form>");
		pw.print("</body>");
		
		pw.print("</html>");
		
	}
	
	private static String calculate(float won, String operator) {
		String result=null;
		
		if(operator.equals("dollar")) {
			result = String.format("%.6f", won/2);
		}else if(operator.equals("en")) {
			result = String.format("%.6f", won/3);
		}else if(operator.equals("wian")) {
			result = String.format("%.6f", won/4);
		}else if(operator.equals("pound")) {
			result = String.format("%.6f", won/5);
		}else if(operator.equals("euro")) {
			result = String.format("%.6f", won/6);
		}
		
		return result;
		
	}

}
