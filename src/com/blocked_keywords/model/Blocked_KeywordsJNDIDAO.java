package com.blocked_keywords.model;

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

public class Blocked_KeywordsJNDIDAO implements Blocked_KeywordsDAO_interface {

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

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, blockedKeywordsVO.getKeyword());
			pstmt.setString(2, blockedKeywordsVO.getReplacement());

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
	public void update(Blocked_KeywordsVO blockedKeywordsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, blockedKeywordsVO.getKeyword());
			pstmt.setString(2, blockedKeywordsVO.getReplacement());
			pstmt.setString(3, blockedKeywordsVO.getKeyword_no());

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
	public void delete(String keyword_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, keyword_no);

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
	public Blocked_KeywordsVO findByPrimaryKey(String keyword_no) {

		Blocked_KeywordsVO blockedKeywordsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, keyword_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// intervlLetterVO 也稱為 Domain objects
				blockedKeywordsVO = new Blocked_KeywordsVO();
				blockedKeywordsVO.setKeyword_no(rs.getString("KEYWORD_NO"));
				blockedKeywordsVO.setKeyword(rs.getString("KEYWORD"));
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

			con = ds.getConnection();
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