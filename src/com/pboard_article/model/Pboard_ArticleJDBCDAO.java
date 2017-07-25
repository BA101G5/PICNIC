package com.pboard_article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chatroom_members.model.Chatroom_MembersVO2;

public class Pboard_ArticleJDBCDAO implements Pboard_ArticleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

	private static final String INSERT_STMT = 
		"INSERT INTO PBOARD_ARTICLE (ARTICLE_NO, AUTHOR_NO, TOPIC_NO, PICNIC_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW) VALUES ('AB' || LPAD(PBOARD_ARTICLE_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, PICNIC_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM PBOARD_ARTICLE order by ARTICLE_NO";
	private static final String GET_ALL_STMT_COND_P = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, PICNIC_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM PBOARD_ARTICLE WHERE PICNIC_NO = ?";
	private static final String GET_ONE_STMT = 
		"SELECT ARTICLE_NO, AUTHOR_NO, TOPIC_NO, PICNIC_NO, ARTICLE_TITLE, ARTICLE_TEXT, ARTICLE_POST, ARTICLE_EDIT, ARTICLE_VIEWS, ARTICLE_STA, ARTICLE_KIND, ARTICLE_PW FROM PBOARD_ARTICLE where ARTICLE_NO = ?";
	private static final String DELETE = 
		"DELETE FROM PBOARD_ARTICLE where ARTICLE_NO = ?";
	private static final String UPDATE = 
		"UPDATE PBOARD_ARTICLE set AUTHOR_NO=?, TOPIC_NO=?, PICNIC_NO=?, ARTICLE_TITLE=?, ARTICLE_TEXT=?, ARTICLE_POST=?, ARTICLE_EDIT=?, ARTICLE_VIEWS=?, ARTICLE_STA=?, ARTICLE_KIND=?, ARTICLE_PW=? where ARTICLE_NO = ?";

	@Override
	public void insert(Pboard_ArticleVO pboardArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(Pboard_ArticleVO pboardArticleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public Pboard_ArticleVO findByPrimaryKey(String article_no) {

		Pboard_ArticleVO pboardArticleVO = null;
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
		return pboardArticleVO;
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	@Override
	public List<Pboard_ArticleVO> getAll() {
		List<Pboard_ArticleVO> list = new ArrayList<Pboard_ArticleVO>();
		Pboard_ArticleVO pboardArticleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		Pboard_ArticleJDBCDAO dao = new Pboard_ArticleJDBCDAO();

//		// 新增
		Pboard_ArticleVO pboardArticleVO1 = new Pboard_ArticleVO();
		pboardArticleVO1.setAuthor_no("MG00000001");
		pboardArticleVO1.setTopic_no(null);
		pboardArticleVO1.setPicnic_no("PG00000001");
		pboardArticleVO1.setArticle_title("標題: 新增野餐團的留言板留言");
		pboardArticleVO1.setArticle_text("新增野餐團的留言板留言");
		pboardArticleVO1.setArticle_post(java.sql.Timestamp.valueOf("2017-06-20 12:38:40"));
		pboardArticleVO1.setArticle_edit(null);
		pboardArticleVO1.setArticle_views(0);
		pboardArticleVO1.setArticle_sta("V");
		pboardArticleVO1.setArticle_kind(1);
		pboardArticleVO1.setArticle_pw(null);
		dao.insert(pboardArticleVO1);
		
//		// 修改
//		Pboard_ArticleVO pboardArticleVO2 = new Pboard_ArticleVO();
//		pboardArticleVO2.setArticle_no("AB00000001");
//		pboardArticleVO2.setAuthor_no("MG00000001");
//		pboardArticleVO2.setTopic_no(null);
//		pboardArticleVO2.setPicnic_no("PG00000001");
//		pboardArticleVO2.setArticle_title("***已修改***標題: 新增野餐團的留言板留言");
//		pboardArticleVO2.setArticle_text("***已修改***新增野餐團的留言板留言");
//		pboardArticleVO2.setArticle_post(java.sql.Timestamp.valueOf("2017-06-20 12:38:40"));
//		pboardArticleVO2.setArticle_edit(null);
//		pboardArticleVO2.setArticle_views(0);
//		pboardArticleVO2.setArticle_sta("V");
//		pboardArticleVO2.setArticle_kind(1);
//		pboardArticleVO2.setArticle_pw(null);
//		dao.update(pboardArticleVO2);
		
//		// 刪除
//		dao.delete("AB00000002");
		
		// 查詢 單筆.
		Pboard_ArticleVO pboardArticleVO3 = dao.findByPrimaryKey("AB00000001");
		System.out.print(pboardArticleVO3.getArticle_no() + ",");
		System.out.print(pboardArticleVO3.getTopic_no() + ",");
		System.out.print(pboardArticleVO3.getPicnic_no() + ",");
		System.out.print(pboardArticleVO3.getArticle_title() + ",");
		System.out.print(pboardArticleVO3.getArticle_text() + ",");
		System.out.print(pboardArticleVO3.getArticle_post() + ",");
		System.out.print(pboardArticleVO3.getArticle_edit() + ",");
		System.out.print(pboardArticleVO3.getArticle_views() + ",");
		System.out.print(pboardArticleVO3.getArticle_sta() + ",");
		System.out.print(pboardArticleVO3.getArticle_kind() + ",");
		System.out.println(pboardArticleVO3.getArticle_pw());
		System.out.println("---------------------");
		
		// 查詢 全部
//		List<Pboard_ArticleVO> list = dao.getAll();
//		for (Pboard_ArticleVO aEmp : list) {
//			System.out.print(aEmp.getArticle_no() + ",");
//			System.out.print(aEmp.getTopic_no() + ",");
//			System.out.print(aEmp.getPicnic_no() + ",");
//			System.out.print(aEmp.getArticle_title() + ",");
//			System.out.print(aEmp.getArticle_text() + ",");
//			System.out.print(aEmp.getArticle_post() + ",");
//			System.out.print(aEmp.getArticle_edit() + ",");
//			System.out.print(aEmp.getArticle_views() + ",");
//			System.out.print(aEmp.getArticle_sta() + ",");
//			System.out.print(aEmp.getArticle_kind() + ",");
//			System.out.print(aEmp.getArticle_pw());
//			System.out.println();
//		}

		// 查詢 全部
				List<Pboard_ArticleVO> list2 = dao.getAll("PG00000001");
				for (Pboard_ArticleVO aEmp : list2) {
					System.out.print(aEmp.getArticle_no() + ",");
					System.out.print(aEmp.getTopic_no() + ",");
					System.out.print(aEmp.getPicnic_no() + ",");
					System.out.print(aEmp.getArticle_title() + ",");
					System.out.print(aEmp.getArticle_text() + ",");
					System.out.print(aEmp.getArticle_post() + ",");
					System.out.print(aEmp.getArticle_edit() + ",");
					System.out.print(aEmp.getArticle_views() + ",");
					System.out.print(aEmp.getArticle_sta() + ",");
					System.out.print(aEmp.getArticle_kind() + ",");
					System.out.print(aEmp.getArticle_pw());
					System.out.println();
				}
			
	}
	//--------------------------------------------------------------
	public List<Pboard_ArticleVO> k_research(String search_string){return null;}
	public String k_get_search_name(String article_no){return null;}

}
