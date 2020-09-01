package sec04.ex04_mod_board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class BoardController
 */
//@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
    BoardService boardService;
    ArticleVO articleVO;
    private static String ARTICLE_IMAGE_REPO = "C:\\Temp\\board\\article_image";
    
    public void init() throws ServletException {
    	boardService = new BoardService();
    	articleVO = new ArticleVO();
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
		
		String nextPage = "";
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();

			if (action == null || action.equals("/listArticles.do")) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/sec04ex04_mvc_board_mod/ListArticles.jsp";
				
			}else if(action.equals("/modArticle.do")){
				
				Map<String, String> articleMap = upload(request,response);
				int articleNO = Integer.parseInt(articleMap.get("articleNO"));
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				boardService.modArticle(articleVO);
				
				if(imageFileName!=null && imageFileName.length()!=0) {
					
					String oldFileName = articleMap.get("originalFileName");
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\temp\\"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
					destDir.mkdir();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
					
					File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO + "\\" + oldFileName);
					oldFile.delete();
				}

				PrintWriter pw = response.getWriter();
				pw.print("<script>"
						+ "alert('글을 수정했습니다.');"
						+ "location.href='"
						+ request.getContextPath()
						+ "/board/viewArticle.do?articleNO="
						+ articleNO
						+ "';"
						+ "</script>");
				
				return;
				
			}else if(action.equals("/viewArticle.do")) {
				String articleNO = request.getParameter("articleNO");
				articleVO = boardService.viewArticle(Integer.parseInt(articleNO));
				request.setAttribute("article", articleVO);
				nextPage = "/sec04ex04_mvc_board_mod/viewArticle.jsp";
				
			}else if(action.equals("/articleForm.do")) {
				nextPage = "/sec04ex04_mvc_board_mod/articleForm.jsp";

			}else if(action.equals("/addArticle.do")) {
				Map<String, String> articleMap = upload(request,response);
				String title = articleMap.get("title");
				String content = articleMap.get("content");
				String imageFileName = articleMap.get("imageFileName");
				articleVO.setParentNO(0);
				articleVO.setId("hong");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);
				int articleNO = boardService.addArticle(articleVO);
				
				if(imageFileName!=null && imageFileName.length()!=0) {
					File srcFile = new File(ARTICLE_IMAGE_REPO+"\\temp\\"+imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+articleNO);
					destDir.mkdir();
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}

				PrintWriter pw = response.getWriter();
				pw.print("<script>"
						+ "alert('새 글을 추가했습니다.');"
						+ "location.href='"
						+ request.getContextPath()
						+ "/board/listArticles.do';"
						+ "</script>");
				
				return;
			}

			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	private Map<String,String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			for(int i=0;i<items.size();i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + " : " + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				}else {
					System.out.println("파라미터 이름 : " + fileItem.getFieldName());
					System.out.println("파일 이름 : " + fileItem.getName());
					System.out.println("파일 크기 : " + fileItem.getSize() + "bytes");
					articleMap.put(fileItem.getFieldName(), fileItem.getName());
					
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx==-1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileName = fileItem.getName().substring(idx+1);
						File uploadFile = new File(currentDirPath+"\\temp\\"+fileName);
						fileItem.write(uploadFile);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return articleMap;
		
	}

}
