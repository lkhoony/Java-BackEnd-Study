package sec02.ex01_data_source;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberSec02Ex01")
public class MemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDAO dao=new MemberDAO();
		List list = dao.listMembers();
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>id</td><td>pw</td><td>name</td><td>email</td><td>joindate</td></tr>");
		
		for(int i=0;i<list.size();i++) {
			MemberVO memberVO=(MemberVO) list.get(i);
			String id=memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			out.print("<tr><td>"+id+"</td><td>"+
	                pwd+"</td><td>"+
	                name+"</td><td>"+
	                email+"</td><td>"+
	                joinDate+"</td></tr>");
		}
		
		out.print("</body></html>");
		
	}

}
