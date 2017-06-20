package com.blocked_keywords.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Blocked_KeywordsJDBCDAO implements Blocked_KeywordsDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

	private static final String INSERT_STMT = 
		"INSERT INTO BLOCKED_KEYWORDS (KEYWORD_NO, KEYWORD, REPLACEMENT) VALUES ('BK' || LPAD(KEYWORD_NO_SQ.NEXTVAL, 8, '0'), ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT KEYWORD_NO, KEYWORD, REPLACEMENT FROM BLOCKED_KEYWORDS order by KEYWORD_NO";
	private static final String GET_ONE_STMT = 
		"SELECT KEYWORD_NO, KEYWORD, REPLACEMENT FROM BLOCKED_KEYWORDS where KEYWORD_NO = ?";
	private static final String DELETE = 
		"DELETE FROM BLOCKED_KEYWORDS where KEYWORD_NO = ?";
	private static final String UPDATE = 
		"UPDATE BLOCKED_KEYWORDS set KEYWORD=?, REPLACEMENT=? where KEYWORD_NO = ?";

	@Override
	public void insert(Blocked_KeywordsVO blockedKeywordsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, blockedKeywordsVO.getKeyword());
			pstmt.setString(2, blockedKeywordsVO.getReplacement());

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
	public void update(Blocked_KeywordsVO blockedKeywordsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blockedKeywordsVO.getKeyword());
			pstmt.setString(2, blockedKeywordsVO.getReplacement());
			pstmt.setString(3, blockedKeywordsVO.getKeyword_no());

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
	public void delete(String keyword_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, keyword_no);

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
	public Blocked_KeywordsVO findByPrimaryKey(String keyword_no) {

		Blocked_KeywordsVO blockedKeywordsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, keyword_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// intervlLetterVO 也稱為 Domain objects
				blockedKeywordsVO = new Blocked_KeywordsVO();
				blockedKeywordsVO.setKeyword_no(rs.getString("KEYWORD_NO"));
				blockedKeywordsVO.setKeyword(rs.getString("KEYWORD"));
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
		return blockedKeywordsVO;
	}

	@Override
	public List<Blocked_KeywordsVO> getAll() {
		List<Blocked_KeywordsVO> list = new ArrayList<Blocked_KeywordsVO>();
		Blocked_KeywordsVO blockedKeywordsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// blockedKeywordsVO 也稱為 Domain objects
				blockedKeywordsVO = new Blocked_KeywordsVO();
				blockedKeywordsVO.setKeyword_no(rs.getString("KEYWORD_NO"));
				blockedKeywordsVO.setKeyword(rs.getString("KEYWORD"));
				blockedKeywordsVO.setReplacement(rs.getString("REPLACEMENT"));
				list.add(blockedKeywordsVO); // Store the row in the list
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

		Blocked_KeywordsJDBCDAO dao = new Blocked_KeywordsJDBCDAO();

//		// 新增
		Blocked_KeywordsVO blockedKeywordsVO1 = new Blocked_KeywordsVO();
		blockedKeywordsVO1.setKeyword("髒話OOXX");
		blockedKeywordsVO1.setReplacement("***被屏蔽的字***");
		dao.insert(blockedKeywordsVO1);
		
//		// 修改
		Blocked_KeywordsVO blockedKeywordsVO2 = new Blocked_KeywordsVO();
		blockedKeywordsVO2.setKeyword_no("BK00000001");
		blockedKeywordsVO2.setKeyword("**已修改**XXOO髒話");
		blockedKeywordsVO2.setReplacement("**已修改**--被屏蔽的字");
		dao.update(blockedKeywordsVO2);
		
//		// 刪除
//		dao.delete("BK00000003");
		
		// 查詢 單筆.
		Blocked_KeywordsVO blockedKeywordsVO3 = dao.findByPrimaryKey("BK00000001");
		System.out.print(blockedKeywordsVO3.getKeyword_no() + ",");
		System.out.print(blockedKeywordsVO3.getKeyword() + ",");
		System.out.print(blockedKeywordsVO3.getReplacement() + ",");
		System.out.println("---------------------");
		
		// 查詢 全部
		List<Blocked_KeywordsVO> list = dao.getAll();
		for (Blocked_KeywordsVO aEmp : list) {
			System.out.print(aEmp.getKeyword_no() + ",");
			System.out.print(aEmp.getKeyword() + ",");
			System.out.print(aEmp.getReplacement() + ",");
			System.out.println();
		}
	
	}

}