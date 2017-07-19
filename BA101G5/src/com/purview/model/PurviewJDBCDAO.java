package com.purview.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.administrator.model.AdministratorJDBCDAO;
import com.administrator.model.AdministratorVO;

public class PurviewJDBCDAO implements Purview_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "picnic_test";
	String passwd = "1234";
	
	private static final String INSERT_STMT = 
			"INSERT INTO PURVIEW (PURVIEW_NO,PURVIEW_NAME) VALUES ('PU' || LPAD(PURVIEW_NO_SQ.NEXTVAL, 8, '0'), ?)";
	private static final String GET_ALL_STMT = 
			"SELECT PURVIEW_NO,PURVIEW_NAME FROM PURVIEW order by PURVIEW_NO";
	private static final String GET_ONE_STMT = 
			"SELECT PURVIEW_NO,PURVIEW_NAME FROM PURVIEW where PURVIEW_NO = ?";
	private static final String DELETE = 
			"DELETE FROM PURVIEW where PURVIEW_NO = ?";
	private static final String UPDATE = 
			"UPDATE PURVIEW set PURVIEW_NAME=? where PURVIEW_NO = ?";
	
	@Override
	public void insert(PurviewVO purviewVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, purviewVO.getPurview_name());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(PurviewVO purviewVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, purviewVO.getPurview_name());
			pstmt.setString(2, purviewVO.getPurview_no());
			
			pstmt.executeUpdate();

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
	public void delete(String purview_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		final String PURVIEW_DETAIL_DELETE = 
				"DELETE FROM PURVIEW_DETAIL where PURVIEW_NO = ?";
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(PURVIEW_DETAIL_DELETE);
			pstmt.setString(1, purview_no);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, purview_no);
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
	public PurviewVO findByPrimaryKey(String purview_no) {
		PurviewVO purVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, purview_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				purVO = new PurviewVO();
				purVO.setPurview_no(rs.getString("PURVIEW_NO"));
				purVO.setPurview_name(rs.getString("PURVIEW_NAME"));
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
		return purVO;
	}
	
	@Override
	public List<PurviewVO> getAll() {
		List<PurviewVO> list = new ArrayList<PurviewVO>();
		PurviewVO purVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				purVO = new PurviewVO();
				purVO.setPurview_no(rs.getString("PURVIEW_NO"));
				purVO.setPurview_name(rs.getString("PURVIEW_NAME"));
				list.add(purVO); // Store the row in the list	
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

		PurviewJDBCDAO purJDBCDAO = new PurviewJDBCDAO();
		// 新增
		PurviewVO purVO1 = new PurviewVO();
		
		purVO1.setPurview_name("測試功能權限");
		
		purJDBCDAO.insert(purVO1);
	
		// 修改
		PurviewVO purVO2 = new PurviewVO();
		purVO2.setPurview_no("PU00000002");
		purVO2.setPurview_name("測試功能權限修改");

		purJDBCDAO.update(purVO2);
		
		// 刪除
		purJDBCDAO.delete("PU00000003");
		
		// 查詢
		PurviewVO purVO3 = purJDBCDAO.findByPrimaryKey("PU00000005");
			System.out.print(purVO3.getPurview_no() + ",");
			System.out.println(purVO3.getPurview_name() + ",");
			System.out.println("---------------------");
		

		List<PurviewVO> list = purJDBCDAO.getAll();
			for (PurviewVO purVO : list) {
				System.out.print(purVO.getPurview_no() + ",");
				System.out.print(purVO.getPurview_name() + ",");
				System.out.println();
			}	
	}
}
