package sec03.ex03_mvc_member_modify_delete;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// request의 요소들 출력해보기 

@WebServlet("/member/*")
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
		// Returns any extra path information associated with the URL the client sent when it made this request.
		System.out.println("action : " + action);
		
		// 
		/*
		Enumeration headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String hName = (String)headers.nextElement();
			String hValue = request.getHeader(hName);
			System.out.println(hName + " : " + hValue);
		}
		*/
		
		if(action == null || action.equals("/listMembers.do")) {
			
			List<MemberVO> memberList = memberDao.listMember();
			request.setAttribute("memberList", memberList);
			nextPage = "/sec03ex03_mvc_member_modify_delete/ListMembers.jsp";
			
		}
		
		// 입력받은 회원정보를 db에 등록할 때 
		else if(action.equals("/addMember.do")) {
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVo = new MemberVO(id, pwd, name, email);
			memberDao.addMember(memberVo);
			nextPage = "/member/listMembers.do";
			
		}
		
		// 회원정보 입력 페이지로 이동
		else if(action.equals("/memberForm.do")) {
			nextPage = "/sec03ex03_mvc_member_modify_delete/MemberForm.jsp";
		}
		
		// 회원정보 수정 페이지로 이동
		else if(action.equals("/modMemberForm.do")) {
			String id = request.getParameter("id");
			MemberVO memInfo = memberDao.findMember(id);
			request.setAttribute("memInfo", memInfo);
			nextPage = "/sec03ex03_mvc_member_modify_delete/ModMemberForm.jsp";
		}
		
		// 수정된 회원정보를 받으면 회원정보를 수정함
		else if(action.equals("/modMember.do")) {
			
			// 수정된 회원정보를 getParameter로 받아옴
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			MemberVO memberVo = new MemberVO(id, pwd, name, email);
			memberDao.modMember(memberVo);
			request.setAttribute("msg", "modified");
			
			nextPage = "/member/listMembers.do";
		}
		
		// 회원정보 삭제
		else if(action.equals("/delMember.do")) {
			String id = request.getParameter("id");
			memberDao.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage = "/member/listMembers.do";
		}
		
		else {
			List memberList = memberDao.listMember();
			request.setAttribute("memberList", memberList);
			nextPage = "/sec03ex03_mvc_member_modify_delete/ListMembers.jsp";
		}
		
		System.out.println(nextPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		// ## URL이 변경되지 않는 이유 : 서버에서 포워딩이 진행되기 때문에 URL은 바뀌지 않음
		// 즉 확장자가 jsp인 nextPage로 dispatch해도 브라우저 상의 URL은 변경되지 않음
		
	}
}
