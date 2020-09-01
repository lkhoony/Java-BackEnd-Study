package sec04.ex03_show_board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public List selectAllArticles() {
		List articlesList = new ArrayList();
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT LEVEL, articleNO, parentNO, LPAD(' ',4*(LEVEL-1)) || title title, content, id, writedate"
				       + " FROM t_board"
				       + " START WITH parentNO=0"
				       + " CONNECT BY PRIOR articleNO = parentNO"
				       + " ORDER SIBLINGS BY articleNO DESC";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return articlesList;
	}
	
	private int getNewArticleNO() {
		
		int maxArticleNO=0;
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT max(articleNO) from t_board ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				maxArticleNO =  rs.getInt(1)+1;
			}
			rs.close();
			pstmt.close();
			conn.close();
			return maxArticleNO;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return maxArticleNO;
	}
	
	public int insertNewArticle(ArticleVO article) {
		
		int articleNO = getNewArticleNO();
		
		try {
			
			conn = dataFactory.getConnection();
			
			String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id) VALUES (?, ?, ?, ?, ?, ?)";
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String imageFileName = article.getImageFileName();
			String id = article.getId();
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, imageFileName);
			pstmt.setString(6, id);
			
			pstmt.executeLargeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return articleNO;
		
	}
	
	public ArticleVO selectArticle(int articleNO) {
		
		ArticleVO article = new ArticleVO();
		
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT articleNO, parentNO, title, content, imageFileName, writedate, id FROM t_board where articleNO=?";
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			pstmt.setInt(1, articleNO);
			System.out.println("ok2");
			ResultSet rs = pstmt.executeQuery();
			System.out.println("ok1");
			rs.next();
			System.out.println(rs.getInt("articleNO"));
			article.setArticleNO(rs.getInt("articleNO"));
			article.setParentNO(rs.getInt("parentNO"));
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
			article.setImageFileName(rs.getString("imageFileName"));
			article.setWriteDate(rs.getDate("writedate"));
			article.setId(rs.getString("id"));
			
			System.out.println(rs.getInt("articleNO"));
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return article;
	}
}
