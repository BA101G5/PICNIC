package com.administrator.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdministratorJDBCDAO implements Administrator_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "picnic_test";
	String passwd = "1234";
	
	private static final String INSERT_STMT = 
			"INSERT INTO ADMINISTRATOR (ADM_NO,ADM_ACC,ADM_PW,ADM_IDEN,ADM_NAME,ADM_STA) VALUES ('MA' || LPAD(ADM_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?, ?,'N')";
	private static final String GET_ALL_STMT = 
			"SELECT ADM_NO,ADM_ACC,ADM_PW,ADM_IDEN,ADM_NAME,ADM_STA FROM ADMINISTRATOR where ADM_STA='N' order by ADM_NO";
	private static final String GET_ONE_STMT = 
			"SELECT ADM_NO,ADM_ACC,ADM_PW,ADM_IDEN,ADM_NAME,ADM_STA FROM ADMINISTRATOR where ADM_NO = ?";
	private static final String DELETE = 
			"UPDATE ADMINISTRATOR set ADM_STA='U' where ADM_NO = ?";
	private static final String UPDATE = 
			"UPDATE ADMINISTRATOR set ADM_ACC=?, ADM_PW=?, ADM_IDEN=?, ADM_NAME=?, ADM_STA=? where ADM_NO = ?";
	
	@Override
	public void insert(AdministratorVO administratorVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, administratorVO.getAdm_acc());
			pstmt.setString(2, administratorVO.getAdm_pw());
			pstmt.setString(3, administratorVO.getAdm_iden());
			pstmt.setString(4, administratorVO.getAdm_name());

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
	public void update(AdministratorVO administratorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, administratorVO.getAdm_acc());
			pstmt.setString(2, administratorVO.getAdm_pw());
			pstmt.setString(3, administratorVO.getAdm_iden());
			pstmt.setString(4, administratorVO.getAdm_name());
			pstmt.setString(5, administratorVO.getAdm_sta());
			pstmt.setString(6, administratorVO.getAdministrator());
		
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

		final String PURVIEW_DETAIL_DELETE = 
				"DELETE FROM PURVIEW_DETAIL where ADM_NO = ?";
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(PURVIEW_DETAIL_DELETE);
			pstmt.setString(1, adm_no);
			pstmt.executeUpdate();
			 
			pstmt.close();
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
	public AdministratorVO findByPrimaryKey(String adm_no) {
		AdministratorVO admVO = null;
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
				// empVo 也稱為 Domain objects
				admVO = new AdministratorVO();
				admVO.setAdministrator(rs.getString("ADM_NO"));
				admVO.setAdm_acc(rs.getString("ADM_ACC"));
				admVO.setAdm_pw(rs.getString("ADM_PW"));
				admVO.setAdm_iden(rs.getString("ADM_IDEN"));
				admVO.setAdm_name(rs.getString("ADM_NAME"));
				admVO.setAdm_sta(rs.getString("ADM_STA"));
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
		return admVO;
	}
	
	@Override
	public List<AdministratorVO> getAll() {
		List<AdministratorVO> list = new ArrayList<AdministratorVO>();
		AdministratorVO admVO = null;

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
				admVO = new AdministratorVO();
				admVO.setAdministrator(rs.getString("ADM_NO"));
				admVO.setAdm_acc(rs.getString("ADM_ACC"));
				admVO.setAdm_pw(rs.getString("ADM_PW"));
				admVO.setAdm_iden(rs.getString("ADM_IDEN"));
				admVO.setAdm_name(rs.getString("ADM_NAME"));
				admVO.setAdm_sta(rs.getString("ADM_STA"));
				list.add(admVO); // Store the row in the list	
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

		AdministratorJDBCDAO adm = new AdministratorJDBCDAO();
		// 新增
		AdministratorVO admVO1 = new AdministratorVO();
		admVO1.setAdm_acc("adm00009");
		admVO1.setAdm_pw("passwordadm00009");
		admVO1.setAdm_iden("員工管理員");
		admVO1.setAdm_name("吳永志");
		adm.insert(admVO1);
	
		// 修改
		AdministratorVO admVO2 = new AdministratorVO();
		admVO2.setAdministrator("MA00000002");
		admVO2.setAdm_acc("adm0test");
		admVO2.setAdm_pw("passwordadm0test");
		admVO2.setAdm_iden("金流管理員");
		admVO2.setAdm_name("吳xx");
		admVO2.setAdm_sta("U");
		adm.update(admVO2);
		
		// 刪除
		adm.delete("MA00000007");
		
		// 查詢
		AdministratorVO admVO3 = adm.findByPrimaryKey("MA00000002");
		System.out.print(admVO3.getAdministrator() + ",");
		System.out.print(admVO3.getAdm_acc() + ",");
		System.out.print(admVO3.getAdm_pw() + ",");
		System.out.print(admVO3.getAdm_iden() + ",");
		System.out.print(admVO3.getAdm_name() + ",");
		System.out.println(admVO3.getAdm_sta() + ",");
		System.out.println("---------------------");

		List<AdministratorVO> list = adm.getAll();
			for (AdministratorVO aAdm : list) {
				System.out.print(aAdm.getAdministrator() + ",");
				System.out.print(aAdm.getAdm_acc() + ",");
				System.out.print(aAdm.getAdm_pw() + ",");
				System.out.print(aAdm.getAdm_iden() + ",");
				System.out.print(aAdm.getAdm_name() + ",");
				System.out.print(aAdm.getAdm_sta() + ",");
				System.out.println();
			}	
	}
	
}
