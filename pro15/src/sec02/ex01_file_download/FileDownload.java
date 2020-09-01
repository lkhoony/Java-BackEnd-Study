package sec02.ex01_file_download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet("/download.do")
public class FileDownload extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String fileRepo = "C:\\myJSP\\workspace\\pro15\\file_repo";
		String fileName = request.getParameter("fileName");
		System.out.println("파일이름 : " + fileName);
		OutputStream out = response.getOutputStream();
		String downFile = fileRepo+"\\"+fileName;
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + fileName);
		FileInputStream fis = new FileInputStream(downFile);
		byte[] buffer = new byte[1024*8];
		int byteNum;
		while((byteNum=fis.read(buffer))!=-1) {
			out.write(buffer, 0,byteNum);
		}
		fis.close();
		out.close();	
	}
}
