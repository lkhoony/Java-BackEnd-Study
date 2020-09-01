package com.spring.ex04;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;


@WebServlet("/mem4.do")
public class MemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		String action = request.getParameter("action");
		String nextPage = null;
		
		if(action==null || action.equals("listMembers")) {
			List membersList = dao.selectAllMemberList();
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
			
		}else if(action.equals("selectMemberById")) {
			String id = request.getParameter("value");
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test03/memberInfo.jsp";
		
		}else if(action.equals("selectMemberByPwd")) {
			String pwd = request.getParameter("value");
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
		
		}else if(action.equals("insertMember")) {
			memberVO.setId(request.getParameter("id"));
			memberVO.setPwd(request.getParameter("pwd"));
			memberVO.setName(request.getParameter("name"));
			memberVO.setEmail(request.getParameter("email"));
			dao.insertMember(memberVO);
			nextPage = "mem4.do?action=listMembers";
		
		}else if(action.equals("insertMember2")) {
			Map memberMap = new HashMap();
			memberMap.put("id", request.getParameter("id"));
			memberMap.put("pwd", request.getParameter("pwd"));
			memberMap.put("name", request.getParameter("name"));
			memberMap.put("email", request.getParameter("email"));
			dao.insertMember2(memberMap);
			nextPage = "mem4.do?action=listMembers";
		
		}else if(action.equals("updateMember")) {
			memberVO.setId(request.getParameter("id"));
			memberVO.setPwd(request.getParameter("pwd"));
			memberVO.setName(request.getParameter("name"));
			memberVO.setEmail(request.getParameter("email"));
			dao.updateMember(memberVO);
			nextPage = "mem4.do?action=listMembers";
		
		}else if(action.equals("deleteMember")) {
			memberVO.setId(request.getParameter("id"));
			dao.deleteMember(memberVO);
			nextPage = "mem4.do?action=listMembers";
		
		}else if(action.equals("searchMember")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			memberVO.setName(name);
			memberVO.setEmail(email);
			List<MemberVO> membersList = dao.searchMember(memberVO);
			request.setAttribute("membersList", membersList);
			nextPage = "test03/listMembers.jsp";
			
		}else if(action.equals("foreachSelect")) {
			List<String> nameList = new ArrayList<String>();
			nameList.add("홍길동");
			nameList.add("차범근");
			nameList.add("이순신");
			List membersList = dao.foreachSelect(nameList);
			request.setAttribute("membersList",membersList);
			nextPage = "test03/listMembers.jsp";
		
		}else if(action.equals("foreachInsert")) {
			
			List<MemberVO> memList = new ArrayList<MemberVO>();
			memList.add(new MemberVO("m21","1234","박길동","m1@test.com"));
			memList.add(new MemberVO("m22","1234","이길동","m2@test.com"));
			memList.add(new MemberVO("m23","1234","김길동","m3@test.com"));
			
			int result = dao.foreachInsert(memList);			
			nextPage = "mem4.do?action=listMembers";
		}
		
		
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}
}
