package com.administrator.model;

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

public class AdministratorDAO implements AdministratorDAO_interface{
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
			"INSERT INTO ADMINISTRATOR (ADM_NO,ADM_ACC,ADM_PW,ADM_IDEN,ADM_NAME,ADM_STA) VALUES ('MA' || LPAD(ADM_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?, ?,'N')";
	private static final String GET_ALL_STMT = 
			"SELECT ADM_NO,ADM_ACC,ADM_PW,ADM_IDEN,ADM_NAME,ADM_STA FROM ADMINISTRATOR where ADM_STA='N' order by ADM_NO";
	private static final String GET_ONE_STMT = 
			"SELECT ADM_NO,ADM_ACC,ADM_PW,ADM_IDEN,ADM_NAME,ADM_STA FROM ADMINISTRATOR where ADM_NO = ?";
	private static final String DELETE = 
			"UPDATE ADMINISTRATOR set ADM_STA='U' where ADM_NO = ?";
	private static final String UPDATE = 
			"UPDATE ADMINISTRATOR set ADM_ACC=?, ADM_PW=?, ADM_IDEN=?, ADM_NAME=? where ADM_NO = ?";
	
	@Override
	public void insert(AdministratorVO administratorVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, administratorVO.getAdm_acc());
			pstmt.setString(2, administratorVO.getAdm_pw());
			pstmt.setString(3, administratorVO.getAdm_iden());
			pstmt.setString(4, administratorVO.getAdm_name());

			pstmt.executeUpdate();

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
	public void update(AdministratorVO administratorVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, administratorVO.getAdm_acc());
			pstmt.setString(2, administratorVO.getAdm_pw());
			pstmt.setString(3, administratorVO.getAdm_iden());
			pstmt.setString(4, administratorVO.getAdm_name());
			pstmt.setString(5, administratorVO.getAdministrator());
		
			pstmt.executeUpdate();

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
	public void delete(String adm_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		final String PURVIEW_DETAIL_DELETE = 
				"DELETE FROM PURVIEW_DETAIL where ADM_NO = ?";
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(PURVIEW_DETAIL_DELETE);
			pstmt.setString(1, adm_no);
			pstmt.executeUpdate();
			 
			pstmt.close();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, adm_no);
			pstmt.executeUpdate();

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
	public AdministratorVO findByPrimaryKey(String adm_no) {
		AdministratorVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, adm_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				admVO = new AdministratorVO();
				admVO.setAdministrator(rs.getString("ADM_NO"));
				admVO.setAdm_acc(rs.getString("ADM_ACC"));
				admVO.setAdm_pw(rs.getString("ADM_PW"));
				admVO.setAdm_iden(rs.getString("ADM_IDEN"));
				admVO.setAdm_name(rs.getString("ADM_NAME"));
				admVO.setAdm_sta(rs.getString("ADM_STA"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				admVO = new AdministratorVO();
				admVO.setAdministrator(rs.getString("ADM_NO"));
				admVO.setAdm_acc(rs.getString("ADM_ACC"));
				admVO.setAdm_pw(rs.getString("ADM_PW"));
				admVO.setAdm_iden(rs.getString("ADM_IDEN"));
				admVO.setAdm_name(rs.getString("ADM_NAME"));
				admVO.setAdm_sta(rs.getString("ADM_STA"));
				list.add(admVO);
			}
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
