package com.forum_article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Forum_ArticleJDBCDAO implements Forum_ArticleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

	private static final String INSERT_STMT = 
		"INSERT INTO FORUM_ARTICLE (ARTICLE_NO, AUTHOR_NO, TOPIC_NO, FORUM_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW) VALUES ('AF' || LPAD(FORUM_ARTICLE_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, FORUM_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM Forum_Article order by ARTICLE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, FORUM_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM Forum_Article where ARTICLE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM Forum_Article where ARTICLE_NO = ?";
	private static final String UPDATE = 
		"UPDATE Forum_Article set AUTHOR_NO=?, TOPIC_NO=?, FORUM_NO=?, ARTICLE_TITLE=?, ARTICLE_TEXT=?, ARTICLE_POST=?, ARTICLE_EDIT=?, ARTICLE_VIEWS=?, ARTICLE_STA=?, ARTICLE_KIND=?, ARTICLE_PW=? where ARTICLE_NO = ?";

	@Override
	public void insert(Forum_ArticleVO forumArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, forumArticleVO.getAuthor_no());
			pstmt.setString(2, forumArticleVO.getTopic_no());
			pstmt.setString(3, forumArticleVO.getForum_no());
			pstmt.setString(4, forumArticleVO.getArticle_title());
			pstmt.setString(5, forumArticleVO.getArticle_text());
			pstmt.setTimestamp(6, forumArticleVO.getArticle_post());
			pstmt.setTimestamp(7, forumArticleVO.getArticle_edit());
			pstmt.setInt(8, forumArticleVO.getArticle_view());
			pstmt.setString(9, forumArticleVO.getArticle_sta());
			pstmt.setInt(10, forumArticleVO.getArticle_kind());
			pstmt.setString(11, forumArticleVO.getArticle_pw());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Forum_ArticleVO forumArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumArticleVO.getAuthor_no());
			pstmt.setString(2, forumArticleVO.getTopic_no());
			pstmt.setString(3, forumArticleVO.getForum_no());
			pstmt.setString(4, forumArticleVO.getArticle_title());
			pstmt.setString(5, forumArticleVO.getArticle_text());
			pstmt.setTimestamp(6, forumArticleVO.getArticle_post());
			pstmt.setTimestamp(7, forumArticleVO.getArticle_edit());
			pstmt.setInt(8, forumArticleVO.getArticle_view());
			pstmt.setString(9, forumArticleVO.getArticle_sta());
			pstmt.setInt(10, forumArticleVO.getArticle_kind());
			pstmt.setString(11, forumArticleVO.getArticle_pw());
			pstmt.setString(12, forumArticleVO.getArticle_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}

	@Override
	public void delete(String article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, article_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Forum_ArticleVO findByPrimaryKey(String article_no) {

		Forum_ArticleVO forumArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forumArticleVO 也稱為 Domain objects
				forumArticleVO = new Forum_ArticleVO();
				forumArticleVO.setArticle_no(rs.getString("ARTICLE_NO"));
				forumArticleVO.setAuthor_no(rs.getString("AUTHOR_NO"));
				forumArticleVO.setTopic_no(rs.getString("TOPIC_NO"));
				forumArticleVO.setForum_no(rs.getString("FORUM_NO"));
				forumArticleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				forumArticleVO.setArticle_text(rs.getString("ARTICLE_TEXT"));
				forumArticleVO.setArticle_post(rs.getTimestamp("ARTICLE_POST"));
				forumArticleVO.setArticle_edit(rs.getTimestamp("ARTICLE_EDIT"));
				forumArticleVO.setArticle_view(rs.getInt("ARTICLE_VIEWS"));
				forumArticleVO.setArticle_sta(rs.getString("ARTICLE_STA"));
				forumArticleVO.setArticle_kind(rs.getInt("ARTICLE_KIND"));
				forumArticleVO.setArticle_pw(rs.getString("ARTICLE_PW"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return forumArticleVO;
	}

	@Override
	public List<Forum_ArticleVO> getAll() {
		List<Forum_ArticleVO> list = new ArrayList<Forum_ArticleVO>();
		Forum_ArticleVO forumArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forumArticleVO 也稱為 Domain objects
				forumArticleVO = new Forum_ArticleVO();
				forumArticleVO.setArticle_no(rs.getString("ARTICLE_NO"));
				forumArticleVO.setAuthor_no(rs.getString("AUTHOR_NO"));
				forumArticleVO.setTopic_no(rs.getString("TOPIC_NO"));
				forumArticleVO.setForum_no(rs.getString("FORUM_NO"));
				forumArticleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				forumArticleVO.setArticle_text(rs.getString("ARTICLE_TEXT"));
				forumArticleVO.setArticle_post(rs.getTimestamp("ARTICLE_POST"));
				forumArticleVO.setArticle_edit(rs.getTimestamp("ARTICLE_EDIT"));
				forumArticleVO.setArticle_view(rs.getInt("ARTICLE_VIEWS"));
				forumArticleVO.setArticle_sta(rs.getString("ARTICLE_STA"));
				forumArticleVO.setArticle_kind(rs.getInt("ARTICLE_KIND"));
				forumArticleVO.setArticle_pw(rs.getString("ARTICLE_PW"));
				list.add(forumArticleVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	public static void main(String[] args) {

		Forum_ArticleJDBCDAO dao = new Forum_ArticleJDBCDAO();

//		// 新增
		Forum_ArticleVO forumArticleVO1 = new Forum_ArticleVO();
		forumArticleVO1.setAuthor_no("MG00000001");
		forumArticleVO1.setTopic_no(null);
		forumArticleVO1.setForum_no("BF00000001");
		forumArticleVO1.setArticle_title("標題: 新增討論區文章");
		forumArticleVO1.setArticle_text("新增討論區內文");
		forumArticleVO1.setArticle_post(java.sql.Timestamp.valueOf("2017-06-15 21:38:40"));
		forumArticleVO1.setArticle_edit(null);
		forumArticleVO1.setArticle_view(0);
		forumArticleVO1.setArticle_sta("V");
		forumArticleVO1.setArticle_kind(1);
		forumArticleVO1.setArticle_pw(null);
		dao.insert(forumArticleVO1);
		
//		// 修改
		Forum_ArticleVO forumArticleVO2 = new Forum_ArticleVO();
		forumArticleVO2.setArticle_no("AF00000002");
		forumArticleVO2.setAuthor_no("MG00000001");
		forumArticleVO2.setTopic_no(null);
		forumArticleVO2.setForum_no("BF00000001");
		forumArticleVO2.setArticle_title("***已修改***標題: 新增討論區文章");
		forumArticleVO2.setArticle_text("***已修改***新增討論區內文");
		forumArticleVO2.setArticle_post(java.sql.Timestamp.valueOf("2017-06-15 21:38:40"));
		forumArticleVO2.setArticle_edit(null);
		forumArticleVO2.setArticle_view(0);
		forumArticleVO2.setArticle_sta("V");
		forumArticleVO2.setArticle_kind(1);
		forumArticleVO2.setArticle_pw(null);
		dao.update(forumArticleVO2);
		
//		// 刪除
//		dao.delete("AF00000002");
		
		// 查詢 單筆.
		Forum_ArticleVO forumArticleVO3 = dao.findByPrimaryKey("AF00000001");
		System.out.print(forumArticleVO3.getArticle_no() + ",");
		System.out.print(forumArticleVO3.getTopic_no() + ",");
		System.out.print(forumArticleVO3.getForum_no() + ",");
		System.out.print(forumArticleVO3.getArticle_title() + ",");
		System.out.print(forumArticleVO3.getArticle_text() + ",");
		System.out.print(forumArticleVO3.getArticle_post() + ",");
		System.out.print(forumArticleVO3.getArticle_edit() + ",");
		System.out.print(forumArticleVO3.getArticle_view() + ",");
		System.out.print(forumArticleVO3.getArticle_sta() + ",");
		System.out.print(forumArticleVO3.getArticle_kind() + ",");
		System.out.println(forumArticleVO3.getArticle_pw());
		System.out.println("---------------------");
		
		// 查詢 全部
		List<Forum_ArticleVO> list = dao.getAll();
		for (Forum_ArticleVO aEmp : list) {
			System.out.print(aEmp.getArticle_no() + ",");
			System.out.print(aEmp.getTopic_no() + ",");
			System.out.print(aEmp.getForum_no() + ",");
			System.out.print(aEmp.getArticle_title() + ",");
			System.out.print(aEmp.getArticle_text() + ",");
			System.out.print(aEmp.getArticle_post() + ",");
			System.out.print(aEmp.getArticle_edit() + ",");
			System.out.print(aEmp.getArticle_view() + ",");
			System.out.print(aEmp.getArticle_sta() + ",");
			System.out.print(aEmp.getArticle_kind() + ",");
			System.out.print(aEmp.getArticle_pw());
			System.out.println();
		}
	
	}
}
