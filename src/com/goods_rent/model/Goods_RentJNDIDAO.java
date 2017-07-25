package com.goods_rent.model;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.sql.*;

public class Goods_RentJNDIDAO implements Goods_RentDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into GOODS_RENT(GR_NO,MF_NO,P_NO,GR_NAME,GR_DATE,GR_PRICE,GR_COUNT,GR_INFO,GR_IMG,GR_UNTIL,GR_STA,GR_PLACE) values('GR'||LPAD(GR_NO_SQ.nexval,8,0),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from GOODS_RENT order by GR_NO";
	private static final String GET_ONE_STMT = "select GR_NO,MF_NO,P_NO,GR_NAME,GR_DATE,GR_PRICE,GR_COUNT,GR_INFO,GR_IMG,GR_UNTIL,GR_STA from GOODS_RENT where GR_NO =?";
	private static final String DELETE_STMT = "delete from GOODS_RENT where GR_NO =?";
	private static final String UPDATE_STMT = "update GOODS_RENT set P_NO=?,GR_NAME=?,GR_DATE=?,GR_PRICE=?,GR_COUNT=?,GR_INFO=?,GR_IMG=?,GR_UNTIL=?,GR_STA=? where GR_NO=?";
	private static final String FINDBYPLACE_STMT = "select * from GOODS_RENT where MF_NO=? and GR_PLACE=? order by GR_NO";

	@Override
	public void insert(Goods_RentVO goods_rentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, goods_rentVO.getMf_no());
			pstmt.setString(2, goods_rentVO.getP_no());
			pstmt.setString(3, goods_rentVO.getGr_name());
			pstmt.setTimestamp(4, goods_rentVO.getGr_date());
			pstmt.setInt(5, goods_rentVO.getGr_price());
			pstmt.setInt(6, goods_rentVO.getGr_count());
			pstmt.setString(7, goods_rentVO.getGr_info());
			pstmt.setBytes(8, goods_rentVO.getGr_img());
			pstmt.setTimestamp(9, goods_rentVO.getGr_date());
			pstmt.setString(10, goods_rentVO.getGr_sta());
			pstmt.setString(11, goods_rentVO.getGr_place());
			pstmt.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Goods_RentVO goods_rentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, goods_rentVO.getMf_no());
			pstmt.setString(2, goods_rentVO.getP_no());
			pstmt.setString(3, goods_rentVO.getGr_name());
			pstmt.setTimestamp(4, goods_rentVO.getGr_date());
			pstmt.setInt(5, goods_rentVO.getGr_price());
			pstmt.setInt(6, goods_rentVO.getGr_count());
			pstmt.setString(7, goods_rentVO.getGr_info());
			pstmt.setBytes(8, goods_rentVO.getGr_img());
			pstmt.setTimestamp(9, goods_rentVO.getGr_until());
			pstmt.setString(10, goods_rentVO.getGr_sta());
			pstmt.setString(11, goods_rentVO.getGr_no());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String gr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, gr_no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Goods_RentVO findByPrimaryKey(String gr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Goods_RentVO goods_rentVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, gr_no);
			rs = pstmt.executeQuery();

			rs.next();
			goods_rentVO = new Goods_RentVO();
			goods_rentVO.setGr_no(rs.getString("GR_NO"));
			goods_rentVO.setMf_no(rs.getString("MF_NO"));
			goods_rentVO.setP_no(rs.getString("P_NO"));
			goods_rentVO.setGr_name(rs.getString("GR_NAME"));
			goods_rentVO.setGr_date(rs.getTimestamp("GR_DATE"));
			goods_rentVO.setGr_price(rs.getInt("GR_PRICE"));
			goods_rentVO.setGr_count(rs.getInt("GR_COUNT"));
			goods_rentVO.setGr_info(rs.getString("GR_INFO"));
			goods_rentVO.setGr_img(rs.getBytes("GR_IMG"));
			goods_rentVO.setGr_until(rs.getTimestamp("GR_UNTIL"));
			goods_rentVO.setGr_sta(rs.getString("GR_STA"));

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return goods_rentVO;
	}

	@Override
	public List<Goods_RentVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_RentVO> list = new ArrayList<Goods_RentVO>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods_RentVO goods_rentVO = new Goods_RentVO();
				goods_rentVO.setGr_no(rs.getString("GR_NO"));
				goods_rentVO.setMf_no(rs.getString("MF_NO"));
				goods_rentVO.setP_no(rs.getString("P_NO"));
				goods_rentVO.setGr_name(rs.getString("GR_NAME"));
				goods_rentVO.setGr_date(rs.getTimestamp("GR_DATE"));
				goods_rentVO.setGr_price(rs.getInt("GR_PRICE"));
				goods_rentVO.setGr_count(rs.getInt("GR_COUNT"));
				goods_rentVO.setGr_info(rs.getString("GR_INFO"));
				goods_rentVO.setGr_img(rs.getBytes("GR_IMG"));
				goods_rentVO.setGr_until(rs.getTimestamp("GR_UNTIL"));
				goods_rentVO.setGr_sta(rs.getString("GR_STA"));

				list.add(goods_rentVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<Goods_RentVO> findbyplace(String mf_no, String place) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_RentVO> list = new ArrayList<Goods_RentVO>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYPLACE_STMT);
			pstmt.setString(1, mf_no);
			pstmt.setString(2, place);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods_RentVO goods_rentVO = new Goods_RentVO();
				goods_rentVO.setGr_no(rs.getString("GR_NO"));
				goods_rentVO.setMf_no(rs.getString("MF_NO"));
				goods_rentVO.setP_no(rs.getString("P_NO"));
				goods_rentVO.setGr_name(rs.getString("GR_NAME"));
				goods_rentVO.setGr_date(rs.getTimestamp("GR_DATE"));
				goods_rentVO.setGr_price(rs.getInt("GR_PRICE"));
				goods_rentVO.setGr_count(rs.getInt("GR_COUNT"));
				goods_rentVO.setGr_info(rs.getString("GR_INFO"));
				goods_rentVO.setGr_img(rs.getBytes("GR_IMG"));
				goods_rentVO.setGr_until(rs.getTimestamp("GR_UNTIL"));
				goods_rentVO.setGr_sta(rs.getString("GR_STA"));

				list.add(goods_rentVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	//----------------------------------------------------------------
	private static final String K_INSERT_STMT = "insert into GOODS_RENT(GR_NO,MF_NO,P_NO,GR_NAME,GR_DATE,GR_PRICE,GR_COUNT,GR_INFO,GR_IMG,GR_UNTIL,GR_STA) values('GR'||LPAD(GR_NO_SQ.nexval,8,0),?,?,?,?,?,?,?,?,?,?)";
	private static final String K_GET_ALL_STMT = "select * from GOODS_RENT order by GR_NO";
	private static final String K_GET_ONE_STMT = "select GR_NO,MF_NO,P_NO,GR_NAME,GR_DATE,GR_PRICE,GR_COUNT,GR_INFO,GR_IMG,GR_UNTIL,GR_STA from GOODS_RENT where GR_NO =?";
	private static final String K_DELETE_STMT = "delete from GOODS_RENT where GR_NO =?";
	private static final String K_UPDATE_STMT = "update GOODS_RENT set P_NO=?,GR_NAME=?,GR_DATE=?,GR_PRICE=?,GR_COUNT=?,GR_INFO=?,GR_IMG=?,GR_UNTIL=?,GR_STA=? where GR_NO=?";

	@Override
	public void k_insert(Goods_RentVO goods_rentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(K_INSERT_STMT);
			pstmt.setString(1, goods_rentVO.getMf_no());
			pstmt.setString(2, goods_rentVO.getP_no());
			pstmt.setString(3, goods_rentVO.getGr_name());
			pstmt.setTimestamp(4, goods_rentVO.getGr_date());
			pstmt.setInt(5, goods_rentVO.getGr_price());
			pstmt.setInt(6, goods_rentVO.getGr_count());
			pstmt.setString(7, goods_rentVO.getGr_info());
			pstmt.setBytes(8, goods_rentVO.getGr_img());
			pstmt.setTimestamp(9, goods_rentVO.getGr_date());
			pstmt.setString(10, goods_rentVO.getGr_sta());

			pstmt.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void k_update(Goods_RentVO goods_rentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(K_UPDATE_STMT);
			pstmt.setString(1, goods_rentVO.getMf_no());
			pstmt.setString(2, goods_rentVO.getP_no());
			pstmt.setString(3, goods_rentVO.getGr_name());
			pstmt.setTimestamp(4, goods_rentVO.getGr_date());
			pstmt.setInt(5, goods_rentVO.getGr_price());
			pstmt.setInt(6, goods_rentVO.getGr_count());
			pstmt.setString(7, goods_rentVO.getGr_info());
			pstmt.setBytes(8, goods_rentVO.getGr_img());
			pstmt.setTimestamp(9, goods_rentVO.getGr_date());
			pstmt.setString(10, goods_rentVO.getGr_sta());
			pstmt.setString(11, goods_rentVO.getGr_no());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void k_delete(String gr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(K_DELETE_STMT);
			pstmt.setString(1, gr_no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Goods_RentVO k_findByPrimaryKey(String gr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Goods_RentVO goods_rentVO = null;
		ResultSet rs = null;

		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ONE_STMT);
			pstmt.setString(1, gr_no);
			rs = pstmt.executeQuery();
			rs.next();
			goods_rentVO = new Goods_RentVO();
			goods_rentVO.setGr_no(rs.getString("GR_NO"));
			goods_rentVO.setMf_no(rs.getString("MF_NO"));
			goods_rentVO.setP_no(rs.getString("P_NO"));
			goods_rentVO.setGr_name(rs.getString("GR_NAME"));
			goods_rentVO.setGr_date(rs.getTimestamp("GR_DATE"));
			goods_rentVO.setGr_price(rs.getInt("GR_PRICE"));
			goods_rentVO.setGr_count(rs.getInt("GR_COUNT"));
			goods_rentVO.setGr_info(rs.getString("GR_INFO"));
			goods_rentVO.setGr_img(rs.getBytes("GR_IMG"));
			goods_rentVO.setGr_until(rs.getTimestamp("GR_UNTIL"));
			goods_rentVO.setGr_sta(rs.getString("GR_STA"));

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {

					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return goods_rentVO;
	}

	@Override
	public List<Goods_RentVO> k_getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_RentVO> list = new ArrayList<Goods_RentVO>();
		ResultSet rs = null;

		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods_RentVO goods_rentVO = new Goods_RentVO();
				goods_rentVO.setGr_no(rs.getString("GR_NO"));
				goods_rentVO.setMf_no(rs.getString("MF_NO"));
				goods_rentVO.setP_no(rs.getString("P_NO"));
				goods_rentVO.setGr_name(rs.getString("GR_NAME"));
				goods_rentVO.setGr_date(rs.getTimestamp("GR_DATE"));
				goods_rentVO.setGr_price(rs.getInt("GR_PRICE"));
				goods_rentVO.setGr_count(rs.getInt("GR_COUNT"));
				goods_rentVO.setGr_info(rs.getString("GR_INFO"));
				goods_rentVO.setGr_img(rs.getBytes("GR_IMG"));
				goods_rentVO.setGr_until(rs.getTimestamp("GR_UNTIL"));
				goods_rentVO.setGr_sta(rs.getString("GR_STA"));

				list.add(goods_rentVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
