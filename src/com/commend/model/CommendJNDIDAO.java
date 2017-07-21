package com.commend.model;

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

public class CommendJNDIDAO implements Commend_interface{
	
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
			"INSERT INTO COMMEND (COMM_MEMNO,COMM_BE_NO,COMM_CATE,COMM_GRADE,COMM_DATE) VALUES (?,?,?,?,?) ";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM COMMEND order by COMM_MEMNO ";
	private static final String GET_ONE_STMT = 
			"SELECT COMM_MEMNO,COMM_BE_NO,COMM_CATE,COMM_GRADE,COMM_DATE FROM COMMEND where COMM_MEMNO = ? and COMM_BE_NO =? ";
	private static final String DELETE = 
			"DELETE from COMMEND where COMM_MEMNO = ? and COMM_BE_NO = ? ";
	private static final String UPDATE = 
			"UPDATE COMMEND set COMM_CATE=?,COMM_GRADE=?,COMM_DATE=? where COMM_MEMNO =? and COMM_BE_NO =? ";

	@Override
	public void insert(CommendVO commendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, commendVO.getComm_memno());
			pstmt.setString(2, commendVO.getComm_be_no());
			pstmt.setInt(3, commendVO.getComm_cate());
			pstmt.setInt(4, commendVO.getComm_grade());
			pstmt.setTimestamp(5, commendVO.getComm_date());
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void update(CommendVO commendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, commendVO.getComm_cate());
			pstmt.setInt(2, commendVO.getComm_grade());
			pstmt.setTimestamp(3, commendVO.getComm_date());
			pstmt.setString(4, commendVO.getComm_memno());
			pstmt.setString(5, commendVO.getComm_be_no());
		
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String comm_memno, String comm_be_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, comm_memno);
			pstmt.setString(2, comm_be_no);
			pstmt.executeUpdate();
			

			// Handle any driver errors
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
	public CommendVO findByPrimaryKey(String comm_memno, String comm_be_no) {
		CommendVO commVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, comm_memno);
			pstmt.setString(2,comm_be_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				commVO = new CommendVO();
				commVO.setComm_memno(rs.getString("COMM_MEMNO"));
				commVO.setComm_be_no(rs.getString("COMM_BE_NO"));
				commVO.setComm_cate(rs.getInt("COMM_CATE"));
				commVO.setComm_grade(rs.getInt("COMM_GRADE"));
				commVO.setComm_date(rs.getTimestamp("COMM_DATE"));
			}

			// Handle any driver errors
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
		return commVO;
	}

	@Override
	public List<CommendVO> getAll() {
		List<CommendVO> list = new ArrayList<CommendVO>();
		CommendVO commVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				commVO = new CommendVO();
				commVO.setComm_memno(rs.getString("COMM_MEMNO"));
				commVO.setComm_be_no(rs.getString("COMM_BE_NO"));
				commVO.setComm_cate(rs.getInt("COMM_CATE"));
				commVO.setComm_grade(rs.getInt("COMM_GRADE"));
				commVO.setComm_date(rs.getTimestamp("COMM_DATE"));
				list.add(commVO); // Store the row in the list	
			}
		
			// Handle any driver errors
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
