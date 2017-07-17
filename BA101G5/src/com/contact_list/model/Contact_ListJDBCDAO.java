package com.contact_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contact_ListJDBCDAO implements Contact_ListDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

	private static final String INSERT_STMT = 
		"INSERT INTO CONTACT_LIST (MEM_NO, CONTACT_NO, RELATIONSHIP) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT MEM_NO, CONTACT_NO, RELATIONSHIP FROM CONTACT_LIST order by MEM_NO";
	private static final String GET_ALL_STMT_COND = 
		"SELECT MEM_NO, CONTACT_NO, RELATIONSHIP FROM CONTACT_LIST WHERE MEM_NO= ? and RELATIONSHIP=? order by MEM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT MEM_NO,CONTACT_NO,RELATIONSHIP FROM CONTACT_LIST where MEM_NO= ? and CONTACT_NO=?";
	private static final String DELETE = 
		"DELETE FROM CONTACT_LIST where MEM_NO=? and CONTACT_NO=?";
	private static final String UPDATE = 
		"UPDATE CONTACT_LIST set RELATIONSHIP=? where MEM_NO=? and CONTACT_NO = ?";

	@Override
	public void insert(Contact_ListVO contact_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, contact_listVO.getMem_no());
			pstmt.setString(2, contact_listVO.getContact_no());
			pstmt.setString(3, contact_listVO.getRelationship().toString());
			
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
	public void update(Contact_ListVO contact_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, contact_listVO.getRelationship().toString());
			pstmt.setString(2, contact_listVO.getMem_no());
			pstmt.setString(3, contact_listVO.getContact_no());

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
	public void delete(String MEM_NO, String CONTACT_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, MEM_NO);
			pstmt.setString(2, CONTACT_NO);
			
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
	public List<Contact_ListVO> findByPrimaryKey(String mem_no, String contact_no) {
		List<Contact_ListVO> list = new ArrayList<Contact_ListVO>();

		Contact_ListVO contact_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);
			pstmt.setString(2, contact_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嘿 Domain objects
				contact_listVO = new Contact_ListVO();
				contact_listVO.setMem_no(rs.getString("MEM_NO"));
				contact_listVO.setContact_no(rs.getString("CONTACT_NO"));
				contact_listVO.setRelationship(rs.getString("Relationship"));
				list.add(contact_listVO);
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

	@Override
	public List<Contact_ListVO> getAll() {
		List<Contact_ListVO> list = new ArrayList<Contact_ListVO>();
		Contact_ListVO contact_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嘿 Domain objects
				contact_listVO = new Contact_ListVO();
				contact_listVO.setMem_no(rs.getString("MEM_NO"));
				contact_listVO.setContact_no(rs.getString("CONTACT_NO"));
				contact_listVO.setRelationship(rs.getString("RELATIONSHIP"));
				list.add(contact_listVO); // Store the row in the list
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
	
	public List<Contact_ListVO> getAll(String mem_no, String relationship) {
		List<Contact_ListVO> list = new ArrayList<Contact_ListVO>();
		Contact_ListVO contact_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_COND);
			pstmt.setString(1, mem_no);
			pstmt.setString(2, relationship);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嘿 Domain objects
				contact_listVO = new Contact_ListVO();
				contact_listVO.setMem_no(rs.getString("MEM_NO"));
				contact_listVO.setContact_no(rs.getString("CONTACT_NO"));
				contact_listVO.setRelationship(rs.getString("RELATIONSHIP"));
				list.add(contact_listVO); // Store the row in the list
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

		Contact_ListJDBCDAO dao = new Contact_ListJDBCDAO();

		// 穝糤
		Contact_ListVO contact_listVO1 = new Contact_ListVO();
		contact_listVO1.setMem_no("MG00000011");
		contact_listVO1.setContact_no("MG00000004");
		contact_listVO1.setRelationship("F");

		dao.insert(contact_listVO1);
//
//		// э
//		Contact_ListVO contact_listVO2 = new Contact_ListVO();
//		contact_listVO2.setMem_no("MG00000001");
//		contact_listVO2.setContact_no("MG00000002");
//		contact_listVO2.setRelationship("B");
//		
//		dao.update(contact_listVO2);

		// 埃
//		dao.delete("MG00000001", "MG00000002");

		// 琩高
//		List<Contact_ListVO> list = dao.findByPrimaryKey("MG00000001", "MG00000002");
//		for (Contact_ListVO aEmp : list) {
//			System.out.print(aEmp.getMem_no() + ",");
//			System.out.print(aEmp.getContact_no() + ",");
//			System.out.print(aEmp.getRelationship() + ",");
//		}
//
//		System.out.println();
//		System.out.println("---------------------");
//		System.out.println();

		// 琩高
//		List<Contact_ListVO> list2 = dao.getAll();
//		for (Contact_ListVO aEmp : list2) {
//			System.out.print(aEmp.getMem_no() + ",");
//			System.out.print(aEmp.getContact_no() + ",");
//			System.out.print(aEmp.getRelationship() + ",");
//			System.out.println();
//		}

		// 琩高
//		List<Contact_ListVO> list3 = dao.getAll("MG00000002", "F");
//		for (Contact_ListVO aEmp : list3) {
//			System.out.print(aEmp.getMem_no() + ",");
//			System.out.print(aEmp.getContact_no() + ",");
//			System.out.print(aEmp.getRelationship() + ",");
//			System.out.println();
//		}
	}
}