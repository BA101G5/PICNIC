package com.pboard_article.model;

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

public class Pboard_ArticleDAO implements Pboard_ArticleDAO_interface {

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
		"INSERT INTO PBOARD_ARTICLE (ARTICLE_NO, AUTHOR_NO, TOPIC_NO, PICNIC_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW) VALUES ('AB' || LPAD(PBOARD_ARTICLE_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, PICNIC_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM PBOARD_ARTICLE order by ARTICLE_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, PICNIC_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM PBOARD_ARTICLE where ARTICLE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM PBOARD_ARTICLE where ARTICLE_NO = ?";
	private static final String UPDATE = 
		"UPDATE PBOARD_ARTICLE set AUTHOR_NO=?, TOPIC_NO=?, PICNIC_NO=?, ARTICLE_TITLE=?, ARTICLE_TEXT=?, ARTICLE_POST=?, ARTICLE_EDIT=?, ARTICLE_VIEWS=?, ARTICLE_STA=?, ARTICLE_KIND=?, ARTICLE_PW=? where ARTICLE_NO = ?";
	private static final String GET_ALL_STMT_COND_P = 
	"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, PICNIC_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM PBOARD_ARTICLE WHERE PICNIC_NO = ? order by ARTICLE_POST";

	@Override
	public void insert(Pboard_ArticleVO pboardArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, pboardArticleVO.getAuthor_no());
			pstmt.setString(2, pboardArticleVO.getTopic_no());
			pstmt.setString(3, pboardArticleVO.getPicnic_no());
			pstmt.setString(4, pboardArticleVO.getArticle_title());
			pstmt.setString(5, pboardArticleVO.getArticle_text());
			pstmt.setTimestamp(6, pboardArticleVO.getArticle_post());
			pstmt.setTimestamp(7, pboardArticleVO.getArticle_edit());
			pstmt.setInt(8, pboardArticleVO.getArticle_views());
			pstmt.setString(9, pboardArticleVO.getArticle_sta());
			pstmt.setInt(10, pboardArticleVO.getArticle_kind());
			pstmt.setString(11, pboardArticleVO.getArticle_pw());

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
	public void update(Pboard_ArticleVO pboardArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pboardArticleVO.getAuthor_no());
			pstmt.setString(2, pboardArticleVO.getTopic_no());
			pstmt.setString(3, pboardArticleVO.getPicnic_no());
			pstmt.setString(4, pboardArticleVO.getArticle_title());
			pstmt.setString(5, pboardArticleVO.getArticle_text());
			pstmt.setTimestamp(6, pboardArticleVO.getArticle_post());
			pstmt.setTimestamp(7, pboardArticleVO.getArticle_edit());
			pstmt.setInt(8, pboardArticleVO.getArticle_views());
			pstmt.setString(9, pboardArticleVO.getArticle_sta());
			pstmt.setInt(10, pboardArticleVO.getArticle_kind());
			pstmt.setString(11, pboardArticleVO.getArticle_pw());
			pstmt.setString(12, pboardArticleVO.getArticle_no());

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
	public Pboard_ArticleVO findByPrimaryKey(String article_no) {

		Pboard_ArticleVO pboardArticleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// pboardArticleVO 也稱為 Domain objects
				pboardArticleVO = new Pboard_ArticleVO();
				pboardArticleVO.setArticle_no(rs.getString("ARTICLE_NO"));
				pboardArticleVO.setAuthor_no(rs.getString("AUTHOR_NO"));
				pboardArticleVO.setTopic_no(rs.getString("TOPIC_NO"));
				pboardArticleVO.setPicnic_no(rs.getString("PICNIC_NO"));
				pboardArticleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				pboardArticleVO.setArticle_text(rs.getString("ARTICLE_TEXT"));
				pboardArticleVO.setArticle_post(rs.getTimestamp("ARTICLE_POST"));
				pboardArticleVO.setArticle_edit(rs.getTimestamp("ARTICLE_EDIT"));
				pboardArticleVO.setArticle_views(rs.getInt("ARTICLE_VIEWS"));
				pboardArticleVO.setArticle_sta(rs.getString("ARTICLE_STA"));
				pboardArticleVO.setArticle_kind(rs.getInt("ARTICLE_KIND"));
				pboardArticleVO.setArticle_pw(rs.getString("ARTICLE_PW"));
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
		return pboardArticleVO;
	}

	@Override
	public List<Pboard_ArticleVO> getAll() {
		List<Pboard_ArticleVO> list = new ArrayList<Pboard_ArticleVO>();
		Pboard_ArticleVO pboardArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// pboardArticleVO 也稱為 Domain objects
				pboardArticleVO = new Pboard_ArticleVO();
				pboardArticleVO.setArticle_no(rs.getString("ARTICLE_NO"));
				pboardArticleVO.setAuthor_no(rs.getString("AUTHOR_NO"));
				pboardArticleVO.setTopic_no(rs.getString("TOPIC_NO"));
				pboardArticleVO.setPicnic_no(rs.getString("PICNIC_NO"));
				pboardArticleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				pboardArticleVO.setArticle_text(rs.getString("ARTICLE_TEXT"));
				pboardArticleVO.setArticle_post(rs.getTimestamp("ARTICLE_POST"));
				pboardArticleVO.setArticle_edit(rs.getTimestamp("ARTICLE_EDIT"));
				pboardArticleVO.setArticle_views(rs.getInt("ARTICLE_VIEWS"));
				pboardArticleVO.setArticle_sta(rs.getString("ARTICLE_STA"));
				pboardArticleVO.setArticle_kind(rs.getInt("ARTICLE_KIND"));
				pboardArticleVO.setArticle_pw(rs.getString("ARTICLE_PW"));
				list.add(pboardArticleVO); // Store the row in the list
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

	@Override
	public List<Pboard_ArticleVO> getAll(String picnic_no) {
		// TODO Auto-generated method stub
		List<Pboard_ArticleVO> list = new ArrayList<Pboard_ArticleVO>();
		Pboard_ArticleVO pboardArticleVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_COND_P);
			pstmt.setString(1, picnic_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// pboardArticleVO 也稱為 Domain objects
				pboardArticleVO = new Pboard_ArticleVO();
				pboardArticleVO.setArticle_no(rs.getString("ARTICLE_NO"));
				pboardArticleVO.setAuthor_no(rs.getString("AUTHOR_NO"));
				pboardArticleVO.setTopic_no(rs.getString("TOPIC_NO"));
				pboardArticleVO.setPicnic_no(rs.getString("PICNIC_NO"));
				pboardArticleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				pboardArticleVO.setArticle_text(rs.getString("ARTICLE_TEXT"));
				pboardArticleVO.setArticle_post(rs.getTimestamp("ARTICLE_POST"));
				pboardArticleVO.setArticle_edit(rs.getTimestamp("ARTICLE_EDIT"));
				pboardArticleVO.setArticle_views(rs.getInt("ARTICLE_VIEWS"));
				pboardArticleVO.setArticle_sta(rs.getString("ARTICLE_STA"));
				pboardArticleVO.setArticle_kind(rs.getInt("ARTICLE_KIND"));
				pboardArticleVO.setArticle_pw(rs.getString("ARTICLE_PW"));
				list.add(pboardArticleVO); // Store the row in the list
			}
	
			// Handle any SQL errors
		}  catch (SQLException se) {
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
	//------------------------------------------------------------------
	//-------------------------------------------------------------------------------------
	private static final String K_RESEARCH_SELECT = 
			" select pbo.ARTICLE_NO,pbo.AUTHOR_NO,gen.MEM_NAME ,gen.MEM_MAIL, pbo.PICNIC_NO,pic.PICNIC_NAME ,pbo.ARTICLE_TITLE，pbo.ARTICLE_TEXT,pbo.ARTICLE_POST ,pbo.TOPIC_NO，pbo.ARTICLE_EDIT，pbo.ARTICLE_VIEWS，pbo.ARTICLE_STA，pbo.ARTICLE_KIND，pbo.ARTICLE_PW "
			+ " from PBOARD_ARTICLE pbo left outer join GENERAL_MEMBER gen on pbo.AUTHOR_NO = gen.MEM_NO left outer join PICNIC pic on pbo.PICNIC_NO = pic.PICNIC_NO "
			+ " where 1=1 ";
	private static final String K_GET_SEARCH_NAME =
			 " select pbo.ARTICLE_NO,pbo.AUTHOR_NO,gen.MEM_NAME ,gen.MEM_MAIL "
			+" from PBOARD_ARTICLE pbo left outer join GENERAL_MEMBER gen on pbo.AUTHOR_NO = gen.MEM_NO "
			+" where ARTICLE_NO = ? ";
	
	
	public List<Pboard_ArticleVO> k_research(String search_string) {
		List<Pboard_ArticleVO> list = new ArrayList<Pboard_ArticleVO>();
		Pboard_ArticleVO pboardArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			if(search_string == ""){
				pstmt = con.prepareStatement(K_RESEARCH_SELECT);
			}else{
				pstmt = con.prepareStatement(K_RESEARCH_SELECT+search_string);
				//pstmt.setString(1, search_string);
			}
			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// pboardArticleVO 也稱為 Domain objects
				pboardArticleVO = new Pboard_ArticleVO();
				pboardArticleVO.setArticle_no(rs.getString("ARTICLE_NO"));
				pboardArticleVO.setAuthor_no(rs.getString("AUTHOR_NO"));
				pboardArticleVO.setTopic_no(rs.getString("TOPIC_NO"));
				pboardArticleVO.setPicnic_no(rs.getString("PICNIC_NO"));
				pboardArticleVO.setArticle_title(rs.getString("ARTICLE_TITLE"));
				pboardArticleVO.setArticle_text(rs.getString("ARTICLE_TEXT"));
				pboardArticleVO.setArticle_post(rs.getTimestamp("ARTICLE_POST"));
				pboardArticleVO.setArticle_edit(rs.getTimestamp("ARTICLE_EDIT"));
				pboardArticleVO.setArticle_views(rs.getInt("ARTICLE_VIEWS"));
				pboardArticleVO.setArticle_sta(rs.getString("ARTICLE_STA"));
				pboardArticleVO.setArticle_kind(rs.getInt("ARTICLE_KIND"));
				pboardArticleVO.setArticle_pw(rs.getString("ARTICLE_PW"));
				list.add(pboardArticleVO); // Store the row in the list
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
	
	public String k_get_search_name(String article_no) {
		String list = "";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_SEARCH_NAME);
			pstmt.setString(1, article_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list = rs.getString("MEM_NAME"); // Store the row in the list
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
