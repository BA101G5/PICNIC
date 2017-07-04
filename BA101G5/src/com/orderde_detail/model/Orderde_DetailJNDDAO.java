package com.orderde_detail.model;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class Orderde_DetailJNDDAO implements Orderde_DetailDAO_interface {
	private static DataSource ds = null;
	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/java/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = " insert into ORDERDE_DETAIL (ORDERDE_DETAILNO,PICNIC_NO,P_NO,GR_NO,GS_NO,OD_AMOUNT,OD_PRICE,OD_DELIVER,OD_PLACE,OD_BS)values('OD'||LPAD(ORDERDE_DETAIL_SQ.nextval,8,0),?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = " select * from ORDERDE_DETAIL ORDER BY ORDERDE_DETAILNO";
	private static final String GET_ONE_STMT = " select ORDERDE_DETAILNO,PICNIC_NO,P_NO,GR_NO,GS_NO,OD_AMOUNT,OD_PRICE,OD_DELIVER,OD_PLACE,OD_BS  from ORDERDE_DETAIL where ORDERDE_DETAILNO = ? ";
	private static final String DELETE_STMT = " delete from ORDERDE_DETAIL where ORDERDE_DETAILNO =? ";
	private static final String UPDATE_STMT = " update ORDERDE_DETAIL set PICNIC_NO = ?,P_NO = ?,GR_NO = ? ,GS_NO= ?,OD_AMOUNT =?,OD_PRICE =?,OD_DELIVER = ?,OD_UPDATE=?,OD_BS =? where ORDERDE_DETAILNO = ?";

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
			pstmt.setString(8, orderde_detailVO.getOd_place());
			pstmt.setString(9, orderde_detailVO.getOd_bs());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Orderde_DetailVO orderde_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, orderde_detailVO.getPicnic_no());
			pstmt.setString(2, orderde_detailVO.getP_no());
			pstmt.setString(3, orderde_detailVO.getGr_no());
			pstmt.setString(4, orderde_detailVO.getGs_no());
			pstmt.setInt(5, orderde_detailVO.getOd_amount());
			pstmt.setInt(6, orderde_detailVO.getOd_price());
			pstmt.setTimestamp(7, orderde_detailVO.getOd_deliver());
			pstmt.setString(8, orderde_detailVO.getOd_place());
			pstmt.setString(9, orderde_detailVO.getOd_bs());
			pstmt.setString(10, orderde_detailVO.getOrderde_detailno());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String orderde_detailno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, orderde_detailno);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Orderde_DetailVO findByPrimaryKey(String orderde_detailno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Orderde_DetailVO orderde_detailVO = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, orderde_detailno);
			rs = pstmt.executeQuery();
			rs.next();
			orderde_detailVO = new Orderde_DetailVO();
			orderde_detailVO.setOrderde_detailno(rs.getString("ORDERDE_DETAILNO"));
			orderde_detailVO.setPicnic_no(rs.getString("PICNIC_NO"));
			orderde_detailVO.setP_no(rs.getString("P_NO"));
			orderde_detailVO.setGr_no(rs.getString("GR_NO"));
			orderde_detailVO.setGs_no(rs.getString("GS_NO"));
			orderde_detailVO.setOd_amount(rs.getInt("OD_AMOUNT"));
			orderde_detailVO.setOd_price(rs.getInt("OD_PRICE"));
			orderde_detailVO.setOd_deliver(rs.getTimestamp("OD_DELIVER"));
			orderde_detailVO.setOd_place(rs.getString("OD_PLACE"));
			orderde_detailVO.setOd_bs(rs.getString("OD_BS"));

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

				orderde_detailVO.setOrderde_detailno(rs.getString("ORDERDE_DETAILNO"));
				orderde_detailVO.setPicnic_no(rs.getString("PICNIC_NO"));
				orderde_detailVO.setP_no(rs.getString("P_NO"));
				orderde_detailVO.setGr_no(rs.getString("GR_NO"));
				orderde_detailVO.setGs_no(rs.getString("GS_NO"));
				orderde_detailVO.setOd_amount(rs.getInt("OD_AMOUNT"));
				orderde_detailVO.setOd_price(rs.getInt("OD_PRICE"));
				orderde_detailVO.setOd_deliver(rs.getTimestamp("OD_DELIVER"));
				orderde_detailVO.setOd_place(rs.getString("OD_PLACE"));
				orderde_detailVO.setOd_bs(rs.getString("OD_BS"));

				list.add(orderde_detailVO);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		Orderde_DetailJNDDAO orderde_detailjdbcdao = new Orderde_DetailJNDDAO();
		// insert
		// Orderde_DetailVO orderde_detailVO = new Orderde_DetailVO();
		// orderde_detailVO.setPicnic_no("PG00000001");
		// orderde_detailVO.setP_no("P000000001");
		// orderde_detailVO.setGr_no("");
		// orderde_detailVO.setGs_no("GS00000001");
		// orderde_detailVO.setOd_amount(1);
		// orderde_detailVO.setOd_price(100);
		// orderde_detailVO.setOd_deliver(java.sql.Timestamp.valueOf("2055-01-01
		// 0:0:0"));
		// orderde_detailVO.setOd_bs("A");
		// orderde_detailjdbcdao.insert(orderde_detailVO);
		// update
		// Orderde_DetailVO orderde_detailVO = new Orderde_DetailVO();
		// orderde_detailVO.setOrderde_detailno("OD00000001");
		// orderde_detailVO.setPicnic_no("PG00000001");
		// orderde_detailVO.setP_no("P000000001");
		// orderde_detailVO.setGr_no("");
		// orderde_detailVO.setGs_no("");
		// orderde_detailVO.setOd_amount(1);
		// orderde_detailVO.setOd_price(100);
		// orderde_detailVO.setOd_deliver(java.sql.Timestamp.valueOf("2055-01-01
		// 0:0:0"));
		// orderde_detailVO.setOd_place("somewhere");
		// orderde_detailVO.setOd_bs("A");
		// orderde_detailjdbcdao.update(orderde_detailVO);
		// delete
		// orderde_detailjdbcdao.delete("OD00000001");
		// search one
		Orderde_DetailVO orderde_detailVO = orderde_detailjdbcdao.findByPrimaryKey("OD00000001");
		System.out.println(orderde_detailVO.getOrderde_detailno());
		System.out.println(orderde_detailVO.getPicnic_no());
		System.out.println(orderde_detailVO.getP_no());
		System.out.println(orderde_detailVO.getGr_no());
		System.out.println(orderde_detailVO.getGs_no());
		System.out.println(orderde_detailVO.getOd_amount());
		System.out.println(orderde_detailVO.getOd_price());
		System.out.println(orderde_detailVO.getOd_deliver());
		System.out.println(orderde_detailVO.getOd_place());
		System.out.println(orderde_detailVO.getOd_bs());
		System.out.println("---------------------");
		// search all
		// List<Orderde_DetailVO> list = orderde_detailjdbcdao.getAll();
		// for(Orderde_DetailVO orderde_detailVO : list){
		// System.out.println(orderde_detailVO.getOrderde_detailno());
		// System.out.println(orderde_detailVO.getPicnic_no());
		// System.out.println(orderde_detailVO.getP_no());
		// System.out.println(orderde_detailVO.getGr_no());
		// System.out.println(orderde_detailVO.getGs_no());
		// System.out.println(orderde_detailVO.getOd_amount());
		// System.out.println(orderde_detailVO.getOd_price());
		// System.out.println(orderde_detailVO.getOd_deliver());
		// System.out.println(orderde_detailVO.getOd_place());
		// System.out.println(orderde_detailVO.getOd_bs());
		// System.out.println("---------------------");
		// }
	}

}
