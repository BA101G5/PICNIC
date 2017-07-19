package com.contact_list.model;

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

public class Contact_ListJNDIDAO implements Contact_ListDAO_interface {

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, contact_listVO.getMem_no());
			pstmt.setString(2, contact_listVO.getContact_no());
			pstmt.setString(3, contact_listVO.getRelationship().toString());
			
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
	public void update(Contact_ListVO contact_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, contact_listVO.getRelationship().toString());
			pstmt.setString(2, contact_listVO.getMem_no());
			pstmt.setString(3, contact_listVO.getContact_no());

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
	public void delete(String MEM_NO, String CONTACT_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, MEM_NO);
			pstmt.setString(2, CONTACT_NO);
			
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
	public List<Contact_ListVO> findByPrimaryKey(String MEM_NO, String CONTACT_NO) {
		List<Contact_ListVO> list = new ArrayList<Contact_ListVO>();

		Contact_ListVO contact_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, MEM_NO);
			pstmt.setString(2, CONTACT_NO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				contact_listVO = new Contact_ListVO();
				contact_listVO.setMem_no(rs.getString("MEM_NO"));
				contact_listVO.setContact_no(rs.getString("CONTACT_NO"));
				contact_listVO.setRelationship(rs.getString("Relationship"));
				list.add(contact_listVO);
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

	@Override
	public List<Contact_ListVO> getAll() {
		List<Contact_ListVO> list = new ArrayList<Contact_ListVO>();
		Contact_ListVO contact_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				contact_listVO = new Contact_ListVO();
				contact_listVO.setMem_no(rs.getString("MEM_NO"));
				contact_listVO.setContact_no(rs.getString("CONTACT_NO"));
				contact_listVO.setRelationship(rs.getString("RELATIONSHIP"));
				list.add(contact_listVO); // Store the row in the list
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
	
	public List<Contact_ListVO> getAll(String mem_no, String relationship) {
		List<Contact_ListVO> list = new ArrayList<Contact_ListVO>();
		Contact_ListVO contact_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_COND);
			pstmt.setString(1, mem_no);
			pstmt.setString(2, relationship);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				contact_listVO = new Contact_ListVO();
				contact_listVO.setMem_no(rs.getString("MEM_NO"));
				contact_listVO.setContact_no(rs.getString("CONTACT_NO"));
				contact_listVO.setRelationship(rs.getString("RELATIONSHIP"));
				list.add(contact_listVO); // Store the row in the list
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
