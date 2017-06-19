package com.orderde_detail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Orderde_DetailJNDIDAO implements Orderde_Detail_interface {
	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = " insert into ORDERDE_DETAIL (PICNIC_NO,P_NO,GR_NO,GS_NO,OD_AMOUNT,OD_PRICE,OD_DELIVER,OD_BS)values(?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = " select * from ORDERDE_DETAIL ORDER BY PICNIC_NO";
	private static final String GET_ONE_STMT = " select PICNIC_NO,P_NO,GR_NO,GS_NO,OD_AMOUNT,OD_PRICE,OD_DELIVER,OD_BS form ORDERDE_DETAIL where PICNIC_NO= ? ";
	private static final String DELETE_STMT = " delete from ORDERDE_DETAIL where PICNIC_NO =? ";
	private static final String UPDATE_STMT = " update ORDERDE_DETAIL set OD_AMOUNT =?,OD_PRICE =?,OD_DELIVER = ?,OD_BS =? where PICNIC_NO = ? ,P_NO =? ,GR_NO = ?,GS_NO = ?";

	@Override
	public void insert(Orderde_DetailVO orderde_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, orderde_detailVO.getPicnic_no());
			pstmt.setString(2, orderde_detailVO.getP_no());
			pstmt.setString(3, orderde_detailVO.getGr_no());
			pstmt.setString(4, orderde_detailVO.getGs_no());
			pstmt.setInt(5, orderde_detailVO.getOd_amount());
			pstmt.setInt(6, orderde_detailVO.getOd_price());
			pstmt.setTimestamp(7, orderde_detailVO.getOd_deliver());
			pstmt.setString(8, orderde_detailVO.getOd_bs());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public void update(Orderde_DetailVO orderde_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, orderde_detailVO.getOd_amount());
			pstmt.setInt(2, orderde_detailVO.getOd_price());
			pstmt.setTimestamp(3, orderde_detailVO.getOd_deliver());
			pstmt.setString(4, orderde_detailVO.getOd_bs());
			pstmt.setString(5, orderde_detailVO.getPicnic_no());
			pstmt.setString(6, orderde_detailVO.getP_no());
			pstmt.setString(7, orderde_detailVO.getGr_no());
			pstmt.setString(8, orderde_detailVO.getGs_no());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public void delete(String picnic_no, String p_no, String gr_no, String gs_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, picnic_no);
			pstmt.setString(2, p_no);
			pstmt.setString(3, gr_no);
			pstmt.setString(4, gs_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public Orderde_DetailVO findByPrimaryKey(String picnic_no, String p_no, String gr_no, String gs_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Orderde_DetailVO orderde_detailVO = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, picnic_no);
			pstmt.setString(2, p_no);
			pstmt.setString(3, gr_no);
			pstmt.setString(4, gs_no);

			rs = pstmt.executeQuery();

			orderde_detailVO = new Orderde_DetailVO();
			orderde_detailVO.setOd_amount(rs.getInt("OD_AMOUNT"));
			orderde_detailVO.setOd_price(rs.getInt("OD_PRICE"));
			orderde_detailVO.setOd_deliver(rs.getTimestamp("OD_DELIVER"));
			orderde_detailVO.setOd_bs(rs.getString("OD_BS"));
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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

		return orderde_detailVO;
	}

	@Override
	public List<Orderde_DetailVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Orderde_DetailVO> list = new ArrayList<Orderde_DetailVO>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Orderde_DetailVO orderde_detailVO = new Orderde_DetailVO();
				orderde_detailVO.setPicnic_no(rs.getString("PICNIC_NO"));
				orderde_detailVO.setP_no(rs.getString("P_NO"));
				orderde_detailVO.setGr_no(rs.getString("GR_NO"));
				orderde_detailVO.setGs_no(rs.getString("GS_NO"));
				orderde_detailVO.setOd_amount(rs.getInt("OD_AMOUNT"));
				orderde_detailVO.setOd_price(rs.getInt("OD_PRICE"));
				orderde_detailVO.setOd_deliver(rs.getTimestamp("OD_DELIVER"));
				orderde_detailVO.setOd_bs(rs.getString("OD_BS"));

				list.add(orderde_detailVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
