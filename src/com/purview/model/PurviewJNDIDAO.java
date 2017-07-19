package com.purview.model;

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

public class PurviewJNDIDAO implements Purview_interface{
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, purviewVO.getPurview_name());
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
	public void update(PurviewVO purviewVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, purviewVO.getPurview_name());
			pstmt.setString(2, purviewVO.getPurview_no());
			
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
	public void delete(String purview_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		final String PURVIEW_DETAIL_DELETE = 
				"DELETE FROM PURVIEW_DETAIL where PURVIEW_NO = ?";
		
		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(PURVIEW_DETAIL_DELETE);
			pstmt.setString(1, purview_no);
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, purview_no);
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
	public PurviewVO findByPrimaryKey(String purview_no) {
		PurviewVO purVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con  = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, purview_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				purVO = new PurviewVO();
				purVO.setPurview_no(rs.getString("PURVIEW_NO"));
				purVO.setPurview_name(rs.getString("PURVIEW_NAME"));
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				purVO = new PurviewVO();
				purVO.setPurview_no(rs.getString("PURVIEW_NO"));
				purVO.setPurview_name(rs.getString("PURVIEW_NAME"));
				list.add(purVO);
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
