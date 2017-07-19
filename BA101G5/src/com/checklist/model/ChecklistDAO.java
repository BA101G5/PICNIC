package com.checklist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChecklistDAO implements ChecklistDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String K_INSERT_STMT =
			"INSERT INTO checklist (chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta) VALUES ('CH' || LPAD(CHLI_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String K_GET_ALL_STMT =
			"SELECT chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta FROM checklist order by chli_no";
	private static final String K_GET_ONE_STMT =
			"SELECT chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta FROM checklist where chli_no=?";
	private static final String K_DELETE =
			"DELETE FROM checklist where chli_no = ?";
	private static final String K_UPDATE =
			"UPDATE CHECKLIST set  CHLI_CATE=?, CHLI_BE_NUM=?, CHLI_MEMNO=?, CHLI_START=?, CHLI_END=? ,CHLI_DAY=?,CHLI_PUN=?,CHLI_REASON=?,CHLI_DATE=?,CHLI_STA=? "
			+ " where CHLI_NO=? ";
	
	
	@Override
	public void k_insert(ChecklistVO checklistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{

			con = ds.getConnection();
			pstmt = con.prepareStatement(K_INSERT_STMT);
			
			pstmt.setInt(1,checklistVO.getChli_cate());
			pstmt.setString(2, checklistVO.getChli_be_num());
			pstmt.setString(3, checklistVO.getChli_memno());
			pstmt.setTimestamp(4, checklistVO.getChli_start());
			pstmt.setTimestamp(5, checklistVO.getChli_end());
			pstmt.setInt(6, checklistVO.getChli_day());
			pstmt.setInt(7, checklistVO.getChli_pun());
			pstmt.setString(8, checklistVO.getChli_reason());
			pstmt.setTimestamp(9,checklistVO.getChli_date());
			pstmt.setString(10, checklistVO.getChli_sta());
			
			pstmt.executeUpdate();
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
	public void k_update(ChecklistVO checklistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{

			con = ds.getConnection();
			pstmt = con.prepareStatement(K_UPDATE);
			
			pstmt.setInt(1, checklistVO.getChli_cate());
			pstmt.setString(2, checklistVO.getChli_be_num());
			pstmt.setString(3,checklistVO.getChli_memno());
			pstmt.setTimestamp(4, checklistVO.getChli_start());
			pstmt.setTimestamp(5,checklistVO.getChli_end());
			pstmt.setInt(6, checklistVO.getChli_day());
			pstmt.setInt(7, checklistVO.getChli_pun());
			pstmt.setString(8, checklistVO.getChli_reason());
			pstmt.setTimestamp(9, checklistVO.getChli_date());
			pstmt.setString(10, checklistVO.getChli_sta());
			pstmt.setString(11, checklistVO.getChli_no());
	
			pstmt.executeUpdate();
			
			
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
	public void k_delete(String chli_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_DELETE);
			
			pstmt.setString(1, chli_no);
			
			pstmt.executeUpdate();
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
	public ChecklistVO k_findByPrimaryKey(String chli_no) {
		
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ONE_STMT);
			
			pstmt.setString(1, chli_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				
				checklistVO.setChli_no(rs.getString("chli_no"));
				checklistVO.setChli_cate(rs.getInt("chli_cate"));
				checklistVO.setChli_be_num(rs.getString("chli_be_num"));
				checklistVO.setChli_memno(rs.getString("chli_memno"));
				checklistVO.setChli_start(rs.getTimestamp("chli_start"));
				checklistVO.setChli_end(rs.getTimestamp("chli_end"));
				checklistVO.setChli_day(rs.getInt("chli_day"));
				checklistVO.setChli_pun(rs.getInt("chli_pun"));
				checklistVO.setChli_reason(rs.getString("chli_reason"));
				checklistVO.setChli_date(rs.getTimestamp("chli_date"));
				checklistVO.setChli_sta(rs.getString("chli_sta"));
			}
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
		return checklistVO;
	}

	@Override
	public List<ChecklistVO> k_getAll() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				
				checklistVO.setChli_no(rs.getString("chli_no"));
				checklistVO.setChli_cate(rs.getInt("chli_cate"));
				checklistVO.setChli_be_num(rs.getString("chli_be_num"));
				checklistVO.setChli_memno(rs.getString("chli_memno"));
				checklistVO.setChli_start(rs.getTimestamp("chli_start"));
				checklistVO.setChli_end(rs.getTimestamp("chli_end"));
				checklistVO.setChli_day(rs.getInt("chli_day"));
				checklistVO.setChli_pun(rs.getInt("chli_pun"));
				checklistVO.setChli_reason(rs.getString("chli_reason"));
				checklistVO.setChli_date(rs.getTimestamp("chli_date"));
				checklistVO.setChli_sta(rs.getString("chli_sta"));
				list.add(checklistVO);
				
			}
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
		return list;
	}
}
