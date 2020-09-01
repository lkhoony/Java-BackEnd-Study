package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/input")

public class InputServlet extends HttpServlet {


	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드 호출");
	}

	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		
		String[] subjects=request.getParameterValues("subject");
		Enumeration<String> subjectNames=request.getParameterNames();
		System.out.println("user id : " + user_id);
		System.out.println("user pw : " + user_pw);
		
		for(String subject : subjects) {
			System.out.println("선택한 과목 : " + subject);
		}
		
		Iterator<String> iterator = subjectNames.asIterator(); // 전송된 이름들을 알고 싶을 때
		while(iterator.hasNext()) {
			String str = iterator.next();
			System.out.println(str);
		}
	}

}
