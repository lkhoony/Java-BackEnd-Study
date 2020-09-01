package sec03.ex02_mvc_member_add;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberDAO memberDao;
    
    
    public MemberController() {
        memberDao = new MemberDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");
		String action = request.getPathInfo();
		// getPathInfo : client가 보낸 URL에 대한 path를 리턴
		System.out.println("action : " + action);
		
		if(action == null || action.equals("/listMembers.do")) {
			
			List<MemberVO> memberList = memberDao.listMember();
			request.setAttribute("memberList", memberList);
			nextPage = "/sec03ex02_mvc_member_add/ListMembers.jsp";
			
		}else if(action.equals("/addMember.do")) {
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVo = new MemberVO(id, pwd, name, email);
			memberDao.addMember(memberVo);
			nextPage = "/member/listMembers.do";
			
		}else if(action.equals("/memberForm.do")) {
			nextPage = "/sec03ex02_mvc_member_add/MemberForm.jsp";
			
		}else {
			List memberList = memberDao.listMember();
			request.setAttribute("memberList", memberList);
			nextPage = "/sec03ex02_mvc_member_add/ListMembers.jsp";
		}
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}
}
