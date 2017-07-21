package com.interval_letter.model;

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

public class Interval_LetterJNDIDAO implements Interval_LetterDAO_interface {

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
		"INSERT INTO INTERNAL_LETTER (LETTER_NO, SENDER_NO, RECIPIENT_NO, LETTER_DATE, LETTER_TEXT, LETTER_TITLE, LETTER_STA) VALUES ('IL' || LPAD(LETTER_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT LETTER_NO, SENDER_NO, RECIPIENT_NO, LETTER_DATE, LETTER_TEXT, LETTER_TITLE, LETTER_STA FROM INTERNAL_LETTER order by LETTER_NO";
	private static final String GET_ONE_STMT = 
		"SELECT LETTER_NO, SENDER_NO, RECIPIENT_NO, LETTER_DATE, LETTER_TEXT, LETTER_TITLE, LETTER_STA FROM INTERNAL_LETTER where LETTER_NO = ?";
	private static final String DELETE = 
		"DELETE FROM INTERNAL_LETTER where LETTER_NO = ?";
	private static final String UPDATE = 
		"UPDATE INTERNAL_LETTER set SENDER_NO=?, RECIPIENT_NO=?, LETTER_DATE=?, LETTER_TEXT=?, LETTER_TITLE=?, LETTER_STA=? where LETTER_NO = ?";

	@Override
	public void insert(Interval_LetterVO intervalLetterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, intervalLetterVO.getSender_no());
			pstmt.setString(2, intervalLetterVO.getRecipient_no());
//			pstmt.setDate(3, intervalLetterVO.getLetter_date());
			pstmt.setTimestamp(3, intervalLetterVO.getLetter_date());
			pstmt.setString(4, intervalLetterVO.getLetter_text());
			pstmt.setString(5, intervalLetterVO.getLetter_title());
			pstmt.setString(6, intervalLetterVO.getLetter_sta());

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
	public void update(Interval_LetterVO intervalLetterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, intervalLetterVO.getSender_no());
			pstmt.setString(2, intervalLetterVO.getRecipient_no());
//			pstmt.setDate(3, intervalLetterVO.getLetter_date());
			pstmt.setTimestamp(3, intervalLetterVO.getLetter_date());
			pstmt.setString(4, intervalLetterVO.getLetter_text());
			pstmt.setString(5, intervalLetterVO.getLetter_title());
			pstmt.setString(6, intervalLetterVO.getLetter_sta());
			pstmt.setString(7, intervalLetterVO.getLetter_no());

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
	public void delete(String letter_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, letter_no);

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
	public Interval_LetterVO findByPrimaryKey(String letter_no) {

		Interval_LetterVO intervalLetterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, letter_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// intervlLetterVO 也稱為 Domain objects
				intervalLetterVO = new Interval_LetterVO();
				intervalLetterVO.setLetter_no(rs.getString("LETTER_NO"));
				intervalLetterVO.setSender_no(rs.getString("SENDER_NO"));
				intervalLetterVO.setRecipient_no(rs.getString("RECIPIENT_NO"));
//				intervalLetterVO.setLetter_date(rs.getDate("LETTER_DATE"));
				intervalLetterVO.setLetter_date(rs.getTimestamp("LETTER_DATE"));
				intervalLetterVO.setLetter_text(rs.getString("LETTER_TEXT"));
				intervalLetterVO.setLetter_title(rs.getString("LETTER_TITLE"));
				intervalLetterVO.setLetter_sta(rs.getString("LETTER_STA"));
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
		return intervalLetterVO;
	}

	@Override
	public List<Interval_LetterVO> getAll() {
		List<Interval_LetterVO> list = new ArrayList<Interval_LetterVO>();
		Interval_LetterVO intervalLetterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// intervalLetterVO 也稱為 Domain objects
				intervalLetterVO = new Interval_LetterVO();
				intervalLetterVO.setLetter_no(rs.getString("LETTER_NO"));
				intervalLetterVO.setSender_no(rs.getString("SENDER_NO"));
				intervalLetterVO.setRecipient_no(rs.getString("RECIPIENT_NO"));
//				intervalLetterVO.setLetter_date(rs.getDate("LETTER_DATE"));
				intervalLetterVO.setLetter_date(rs.getTimestamp("LETTER_DATE"));
				intervalLetterVO.setLetter_text(rs.getString("LETTER_TEXT"));
				intervalLetterVO.setLetter_title(rs.getString("LETTER_TITLE"));
				intervalLetterVO.setLetter_sta(rs.getString("LETTER_STA"));
				list.add(intervalLetterVO); // Store the row in the list
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
