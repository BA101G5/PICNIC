package com.purview_detail.model;

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

public class Purview_DetailDAO implements Purview_DetailDAO_interface{
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
			"INSERT INTO PURVIEW_DETAIL (ADM_NO,PURVIEW_NO) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT pur.ADM_NO, PURVIEW.PURVIEW_NAME ,PURVIEW.PURVIEW_NO "
			+ "FROM PURVIEW left outer JOIN (SELECT PURVIEW_DETAIL.ADM_NO, PURVIEW_NO FROM PURVIEW_DETAIL Where ADM_NO = ?) pur ON pur.PURVIEW_NO=PURVIEW.PURVIEW_NO "
			+ "order by PURVIEW.PURVIEW_NO";
	private static final String GET_ONE_STMT = 
			"SELECT ADM_NO,PURVIEW_NO FROM PURVIEW_DETAIL where ADM_NO = ?";
	private static final String DELETE = 
			"DELETE FROM PURVIEW_DETAIL where ADM_NO = ?";
	private static final String UPDATE = 
			"UPDATE PURVIEW_DETAIL set PURVIEW_NO=? where ADM_NO || PURVIEW_NO = ?";
	
	@Override
	public void insert(Purview_DetailVO purview_detailVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, purview_detailVO.getAdm_no());
			pstmt.setString(2, purview_detailVO.getPurview_no());

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
	public void update(Purview_DetailVO purview_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, purview_detailVO.getPurview_no());
			pstmt.setString(2, purview_detailVO.getAdm_no());
			
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
		try {

			con = ds.getConnection();
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
	public List<Purview_DetailVO> findByPrimaryKey(String adm_no) {
		
		List<Purview_DetailVO> list = new ArrayList<Purview_DetailVO>();
		Purview_DetailVO pudVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, adm_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pudVO = new Purview_DetailVO();
				pudVO.setAdm_no(rs.getString("ADM_NO"));
				pudVO.setPurview_no(rs.getString("PURVIEW_NO"));
				list.add(pudVO);
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
		return list;
	}
	
	@Override
	public List<Purview_DetailVO> getAll(String adm_no) {
		List<Purview_DetailVO> list = new ArrayList<Purview_DetailVO>();
		Purview_DetailVO pudVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setString(1,adm_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pudVO = new Purview_DetailVO();
				pudVO.setAdm_no(rs.getString("ADM_NO"));
				pudVO.setPurview_name(rs.getString("PURVIEW_NAME"));
				pudVO.setPurview_no(rs.getString("PURVIEW_NO"));
				list.add(pudVO); 
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
		return list;
	}
}
