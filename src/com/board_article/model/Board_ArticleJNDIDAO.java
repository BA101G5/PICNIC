package com.board_article.model;

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

public class Board_ArticleJNDIDAO implements Board_ArticleDAO_interface {

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
		"INSERT INTO BOARD_ARTICLE (ARTICLE_NO, AUTHOR_NO, TOPIC_NO, BOARD_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW) VALUES ('AP' || LPAD(BOARD_ARTICLE_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, BOARD_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM BOARD_ARTICLE order by ARTICLE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, BOARD_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM BOARD_ARTICLE where ARTICLE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM BOARD_ARTICLE where ARTICLE_NO = ?";
	private static final String UPDATE = 
		"UPDATE BOARD_ARTICLE set AUTHOR_NO=?, TOPIC_NO=?, BOARD_NO=?, ARTICLE_TITLE=?, ARTICLE_TEXT=?, ARTICLE_POST=?, ARTICLE_EDIT=?, ARTICLE_VIEWS=?, ARTICLE_STA=?, ARTICLE_KIND=?, ARTICLE_PW=? where ARTICLE_NO = ?";

	@Override
	public void insert(Board_ArticleVO boardArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, boardArticleVO.getAuthor_no());
			pstmt.setString(2, boardArticleVO.getTopic_no());
			pstmt.setString(3, boardArticleVO.getBoard_no());
			pstmt.setString(4, boardArticleVO.getArticle_title());
			pstmt.setString(5, boardArticleVO.getArticle_text());
			pstmt.setTimestamp(6, boardArticleVO.getArticle_post());
			pstmt.setTimestamp(7, boardArticleVO.getArticle_edit());
			pstmt.setInt(8, boardArticleVO.getArticle_view());
			pstmt.setString(9, boardArticleVO.getArticle_sta());
			pstmt.setInt(10, boardArticleVO.getArticle_kind());
			pstmt.setString(11, boardArticleVO.getArticle_pw());

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
	public void update(Board_ArticleVO boardArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, boardArticleVO.getAuthor_no());
			pstmt.setString(2, boardArticleVO.getTopic_no());
			pstmt.setString(3, boardArticleVO.getBoard_no());
			pstmt.setString(4, boardArticleVO.getArticle_title());
			pstmt.setString(5, boardArticleVO.getArticle_text());
			pstmt.setTimestamp(6, boardArticleVO.getArticle_post());
			pstmt.setTimestamp(7, boardArticleVO.getArticle_edit());
			pstmt.setInt(8, boardArticleVO.getArticle_view());
			pstmt.setString(9, boardArticleVO.getArticle_sta());
			pstmt.setInt(10, boardArticleVO.getArticle_kind());
			pstmt.setString(11, boardArticleVO.getArticle_pw());
			pstmt.setString(12, boardArticleVO.getArticle_no());

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
	public Board_ArticleVO findByPrimaryKey(String article_no) {

		Board_ArticleVO boardArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// boardArticleVO 也稱為 Domain objects
				boardArticleVO = new Board_ArticleVO();
				boardArticleVO.setArticle_no(rs.getString("ARTICLE_NO"));
				boardArticleVO.setAuthor_no(rs.getString("AUTHOR_NO"));
				boardArticleVO.setTopic_no(rs.getString("TOPIC_NO"));
				boardArticleVO.setBoard_no(rs.getString("BOARD_NO"));
				boardArticleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				boardArticleVO.setArticle_text(rs.getString("ARTICLE_TEXT"));
				boardArticleVO.setArticle_post(rs.getTimestamp("ARTICLE_POST"));
				boardArticleVO.setArticle_edit(rs.getTimestamp("ARTICLE_EDIT"));
				boardArticleVO.setArticle_view(rs.getInt("ARTICLE_VIEWS"));
				boardArticleVO.setArticle_sta(rs.getString("ARTICLE_STA"));
				boardArticleVO.setArticle_kind(rs.getInt("ARTICLE_KIND"));
				boardArticleVO.setArticle_pw(rs.getString("ARTICLE_PW"));
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
		return boardArticleVO;
	}

	@Override
	public List<Board_ArticleVO> getAll() {
		List<Board_ArticleVO> list = new ArrayList<Board_ArticleVO>();
		Board_ArticleVO boardArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// boardArticleVO 也稱為 Domain objects
				boardArticleVO = new Board_ArticleVO();
				boardArticleVO.setArticle_no(rs.getString("ARTICLE_NO"));
				boardArticleVO.setAuthor_no(rs.getString("AUTHOR_NO"));
				boardArticleVO.setTopic_no(rs.getString("TOPIC_NO"));
				boardArticleVO.setBoard_no(rs.getString("BOARD_NO"));
				boardArticleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				boardArticleVO.setArticle_text(rs.getString("ARTICLE_TEXT"));
				boardArticleVO.setArticle_post(rs.getTimestamp("ARTICLE_POST"));
				boardArticleVO.setArticle_edit(rs.getTimestamp("ARTICLE_EDIT"));
				boardArticleVO.setArticle_view(rs.getInt("ARTICLE_VIEWS"));
				boardArticleVO.setArticle_sta(rs.getString("ARTICLE_STA"));
				boardArticleVO.setArticle_kind(rs.getInt("ARTICLE_KIND"));
				boardArticleVO.setArticle_pw(rs.getString("ARTICLE_PW"));
				list.add(boardArticleVO); // Store the row in the list
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