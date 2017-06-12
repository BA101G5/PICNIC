package com.interval_letter.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class IntervalLetterJDBCDAO implements IntervalLetter_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ALLEN";
	String passwd = "ALLEN";

	private static final String INSERT_STMT = 
		"INSERT INTO INTERNAL_LETTER (LETTER_NO, SENDER_NO, RECIPIENT_NO, LETTER_DATE, LETTER_TEXT, LETTER_TITLE, LETTER_STATUS) VALUES ('IL' || LPAD(LETTER_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = 
//		"SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM INTERNAL_LETTER order by empno";
//	private static final String GET_ONE_STMT = 
//		"SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM INTERNAL_LETTER where empno = ?";
//	private static final String DELETE = 
//		"DELETE FROM INTERNAL_LETTER where empno = ?";
//	private static final String UPDATE = 
//		"UPDATE INTERNAL_LETTER set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=? where empno = ?";

	@Override
	public void insert(IntervalLetterVO intervalLetterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, intervalLetterVO.getSender_no());
			pstmt.setString(2, intervalLetterVO.getRecipient_no());
			pstmt.setDate(3, intervalLetterVO.getLetter_date());
			pstmt.setString(4, intervalLetterVO.getLetter_text());
			pstmt.setString(5, intervalLetterVO.getLetter_title());
			pstmt.setString(6, intervalLetterVO.getLetter_sta());

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
	public void update(IntervalLetterVO intervalLetterVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer intervalLetterVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IntervalLetterVO findByPrimaryKey(Integer intervalLetterVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IntervalLetterVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static void main(String[] args) {

		IntervalLetterJDBCDAO dao = new IntervalLetterJDBCDAO();

		// 新增
		IntervalLetterVO intervalLetterVO1 = new IntervalLetterVO();
		intervalLetterVO1.setSender_no("MG00000001");
		intervalLetterVO1.setRecipient_no("MG00000003");
		intervalLetterVO1.setLetter_date(java.sql.Date.valueOf("2005-01-01"));
		intervalLetterVO1.setLetter_text("內文: 站內信");
		intervalLetterVO1.setLetter_title("站內信標題");
		intervalLetterVO1.setLetter_sta("U");
		dao.insert(intervalLetterVO1);
	}
}
