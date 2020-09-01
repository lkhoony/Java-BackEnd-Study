package sec02.ex02_data_insert_delete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/memberSec02Ex02")
public class MemberServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(req, resp);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		MemberDAO dao=new MemberDAO();
		
		
		if(command!=null && command.equals("addMember")) {
			MemberVO memberVO = new MemberVO();
			memberVO.setId(request.getParameter("id"));
			memberVO.setPwd(request.getParameter("pwd"));
			memberVO.setName(request.getParameter("name"));
			memberVO.setEmail(request.getParameter("email"));
			dao.addMember(memberVO);
			
		}else if(command!=null && command.equals("delMember")) {
			dao.delMember(request.getParameter("id"));
		}
		
		List list = dao.listMembers();
		
		out.print("<html><body>");
		out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>id</td><td>pw</td><td>name</td><td>email</td><td>joindate</td><td>삭제</td></tr>");
		
		for(int i=0;i<list.size();i++) {
			
			MemberVO memberVO=(MemberVO) list.get(i);
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			Date joinDate = memberVO.getJoinDate();
			
			out.print("<tr><td>"+id+"</td><td>"+
	                pwd+"</td><td>"+
	                name+"</td><td>"+
	                email+"</td><td>"+
	                joinDate+"</td>"+
	                "<td><a href='/pro07/memberSec02Ex02?command=delMember&id=" + id + "'>삭제</a></td></tr>"
	                
	                );

		}
		out.print("<a href='/pro07/MemberForm.html'>회원가입 페이지로</a>");
		out.print("</body></html>");
		
	}

}
