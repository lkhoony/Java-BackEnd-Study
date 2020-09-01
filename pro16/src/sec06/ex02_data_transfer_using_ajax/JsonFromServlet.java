package sec06.ex02_data_transfer_using_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/JsonFromServlet")
public class JsonFromServlet extends HttpServlet {

    public JsonFromServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		JSONObject totalObject = new JSONObject();
		JSONArray membersArray = new JSONArray();
		JSONObject memberInfo = new JSONObject();
		memberInfo.put("name","박지성");
		memberInfo.put("age", "25");
		memberInfo.put("gender","남자");
		memberInfo.put("nickname", "날쎈돌이");
		membersArray.add(memberInfo);

		memberInfo = new JSONObject();
		memberInfo.put("name","김연아");
		memberInfo.put("age", "21");
		memberInfo.put("gender","여자");
		memberInfo.put("nickname", "칼치");
		membersArray.add(memberInfo);
		
		totalObject.put("members", membersArray);
		
		String jsonInfo = totalObject.toString();
		System.out.println(jsonInfo);
		writer.print(jsonInfo);
	}
	
}
