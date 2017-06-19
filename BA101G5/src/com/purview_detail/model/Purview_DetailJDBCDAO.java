package com.purview_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Purview_DetailJDBCDAO implements Purview_Detail_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "picnic_test";
	String passwd = "1234";
	
	private static final String INSERT_STMT = 
			"INSERT INTO PURVIEW_DETAIL (ADM_NO,PURVIEW_NO) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT ADM_NO,PURVIEW_NO FROM PURVIEW_DETAIL order by ADM_NO";
	private static final String GET_ONE_STMT = 
			"SELECT ADM_NO,PURVIEW_NO FROM PURVIEW_DETAIL where ADM_NO || PURVIEW_NO = ?";
	private static final String DELETE = 
			"DELETE FROM PURVIEW_DETAIL where ADM_NO || PURVIEW_NO = ?";
	private static final String UPDATE = 
			"UPDATE PURVIEW_DETAIL set PURVIEW_NO=? where ADM_NO || PURVIEW_NO = ?";
	
	@Override
	public void insert(Purview_DetailVO purview_detailVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, purview_detailVO.getAdm_no());
			pstmt.setString(2, purview_detailVO.getPurview_no());

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
	public void update(Purview_DetailVO purview_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, purview_detailVO.getPurview_no());
			pstmt.setString(2, purview_detailVO.getAdm_no());
			

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
	public void delete(String adm_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, adm_no);

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
	public Purview_DetailVO findByPrimaryKey(String adm_no) {
		Purview_DetailVO pudVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adm_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				pudVO = new Purview_DetailVO();
				pudVO.setAdm_no(rs.getString("ADM_NO"));
				pudVO.setPurview_no(rs.getString("PURVIEW_NO"));

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
		return pudVO;
	}
	
	@Override
	public List<Purview_DetailVO> getAll() {
		List<Purview_DetailVO> list = new ArrayList<Purview_DetailVO>();
		Purview_DetailVO pudVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				pudVO = new Purview_DetailVO();
				pudVO.setAdm_no(rs.getString("ADM_NO"));
				pudVO.setPurview_no(rs.getString("PURVIEW_NO"));
				list.add(pudVO); // Store the row in the list	
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

		Purview_DetailJDBCDAO pudJD = new Purview_DetailJDBCDAO();
		// �s�W
		Purview_DetailVO pudVO1 = new Purview_DetailVO();
		pudVO1.setAdm_no("MA00000008");
		pudVO1.setPurview_no("PU00000011");

		pudJD.insert(pudVO1);
	
		// �ק�
		Purview_DetailVO pudVO2 = new Purview_DetailVO();
		pudVO2.setAdm_no("MA00000006PU00000005");
		pudVO2.setPurview_no("PU00000010");
	
		pudJD.update(pudVO2);
		
		// �R��
		pudJD.delete("MA00000004PU00000008");
		
		// �d��
		Purview_DetailVO pudVO3 = pudJD.findByPrimaryKey("MA00000005PU00000003");
		System.out.print(pudVO3.getAdm_no() + ",");
		System.out.println(pudVO3.getPurview_no() + ",");
	
		System.out.println("---------------------");

		List<Purview_DetailVO> list = pudJD.getAll();
			for (Purview_DetailVO purd : list) {
				System.out.print(purd.getAdm_no() + ",");
				System.out.print(purd.getPurview_no() + ",");
				System.out.println();
			}	
	}
}
