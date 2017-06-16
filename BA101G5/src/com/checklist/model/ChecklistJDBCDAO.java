package com.checklist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChecklistJDBCDAO implements Checklist_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";
	
	private static final String INSERT_STMT =
			"INSERT INTO checklist (chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta) VALUES (LPAD(CHLI_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta FROM checklist order by chli_no";
	private static final String GET_ONE_STMT =
			"SELECT chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta FROM checklist where chli_no=?";
	private static final String DELETE =
			"DELETE FROM checklist where chli_no = ?";
	private static final String UPDATE =
			"UPDATE checklist set  chli_cate=?, chli_end=?, chli_day=?, chli_pun=?, chli_sta=? where chli_no=?";
	@Override
	public void insert(ChecklistVO checklistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
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
		}catch (ClassNotFoundException e) {
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
	public void update(ChecklistVO checklistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,checklistVO.getChli_cate());
			pstmt.setTimestamp(2, checklistVO.getChli_end());
			pstmt.setInt(3, checklistVO.getChli_day());
			pstmt.setInt(4, checklistVO.getChli_pun());
			pstmt.setString(5, checklistVO.getChli_sta());
			pstmt.setString(6, checklistVO.getChli_no());
			
			pstmt.executeUpdate();
			
			
		}catch (ClassNotFoundException e) {
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
	public void delete(String chli_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, chli_no);
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
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
	public ChecklistVO findByPrimaryKey(String chli_no) {
		
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
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
		}catch (ClassNotFoundException e) {
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
		return checklistVO;
	}

	@Override
	public List<ChecklistVO> getAll() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
		}catch (ClassNotFoundException e) {
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
		return list;
	}
	
	public static void main(String[] args){
		ChecklistJDBCDAO dao = new ChecklistJDBCDAO();
		
		//新增
//		ChecklistVO checklistVO1 = new ChecklistVO();
//		checklistVO1.setChli_cate(1);
//		checklistVO1.setChli_be_num("00001");
//		checklistVO1.setChli_memno("MG00000032");
//		checklistVO1.setChli_start(java.sql.Date.valueOf("2017-06-14"));
//		checklistVO1.setChli_end(java.sql.Date.valueOf("2017-06-21"));
//		checklistVO1.setChli_day(7);
//		checklistVO1.setChli_pun(2);
//		checklistVO1.setChli_reason("TES_JDBC");
//		checklistVO1.setChli_date(java.sql.Date.valueOf("2017-06-14"));
//		checklistVO1.setChli_sta("F");
//		
//		dao.insert(checklistVO1);
		
		//修改
//		ChecklistVO checklistVO2 = new ChecklistVO();
//		checklistVO2.setChli_no("00000015");
//		checklistVO2.setChli_cate(2);
//		checklistVO2.setChli_end(java.sql.Date.valueOf("2017-06-24"));
//		checklistVO2.setChli_day(10);
//		checklistVO2.setChli_pun(1);
//		checklistVO2.setChli_sta("N");
//		
//		dao.update(checklistVO2);
		
		//刪除
//		dao.delete("00000015");
		
		//單一查詢
		ChecklistVO checklistVO3 = dao.findByPrimaryKey("00000014");
		
		System.out.print(checklistVO3.getChli_no()+",");
		System.out.print(checklistVO3.getChli_cate()+",");
		System.out.print(checklistVO3.getChli_be_num()+",");
		System.out.print(checklistVO3.getChli_memno()+",");
		System.out.print(checklistVO3.getChli_start()+",");
		System.out.print(checklistVO3.getChli_end()+",");
		System.out.print(checklistVO3.getChli_day()+",");
		System.out.print(checklistVO3.getChli_pun()+",");
		System.out.print(checklistVO3.getChli_reason()+",");
		System.out.print(checklistVO3.getChli_date()+",");
		System.out.print(checklistVO3.getChli_sta());
		System.out.println("-------------------------------------------");
		
		//查詢所有
//		List<ChecklistVO> list = dao.getAll();
//		for(ChecklistVO checklistVO : list){
//			System.out.print(checklistVO.getChli_no()+",");
//			System.out.print(checklistVO.getChli_cate()+",");
//			System.out.print(checklistVO.getChli_be_num()+",");
//			System.out.print(checklistVO.getChli_memno()+",");
//			System.out.print(checklistVO.getChli_start()+",");
//			System.out.print(checklistVO.getChli_end()+",");
//			System.out.print(checklistVO.getChli_day()+",");
//			System.out.print(checklistVO.getChli_pun()+",");
//			System.out.print(checklistVO.getChli_reason()+",");
//			System.out.print(checklistVO.getChli_date()+",");
//			System.out.print(checklistVO.getChli_sta());
//			System.out.println();
//		}
	}
}
