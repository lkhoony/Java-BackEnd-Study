package sec04.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
	
	private static String ARTICLE_IMAGE_REPO = "C:\\Temp\\board\\article_image";
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String imageFileName = (String)request.getParameter("imageFileName");
		String articleNO = (String)request.getParameter("articleNO");
		
		System.out.println("imageFileName : " + imageFileName);
		OutputStream out = response.getOutputStream();
		
		String path = ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + imageFileName;
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);
		
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[1024*8];
		
		int count;
		while((count=fis.read(buffer))!=-1) {
			out.write(buffer);
		}
		
		out.flush();
		out.close();
		fis.close();
	}

}
