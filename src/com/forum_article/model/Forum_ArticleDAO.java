package com.forum_article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Forum_ArticleDAO implements Forum_ArticleDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, article_no);

			pstmt.executeUpdate();

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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}
