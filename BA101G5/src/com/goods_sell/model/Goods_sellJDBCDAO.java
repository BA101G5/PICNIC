package com.goods_sell.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Goods_sellJDBCDAO implements Goods_Sell_interface {
	String driver = "Oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin@localhost:1521:XE";
	String userid = "ba101";
	String passwd = "ba101";

	private static final String INSERT_STMT = "insert into GOODS_SELL(GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA) values(?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from GOODS_SELL ORDER BY GS_NO";
	private static final String GET_ONE_STMT = "select GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA from GOODS_SELL where GS_NO =?";
	private static final String DELETE_STMT = "delete from GOODS_SELL where GS_NO = ?";
	private static final String UPDATE_STMT = "update GOODS_SELL set MF_NO = ? ,GS_NAME = ? ,GS_DATE = ? ,GS_PRICE = ? ,GS_INFO = ? ,GS_IMG = ? ,GS_STA = ? where GS_NO = ? ";

	@Override
	public void insert(Goods_SellVO goods_sellVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, goods_sellVO.getGs_no());
			pstmt.setString(2, goods_sellVO.getMf_no());
			pstmt.setString(3, goods_sellVO.getGs_name());
			pstmt.setTimestamp(4, goods_sellVO.getGs_date());
			pstmt.setInt(5, goods_sellVO.getGs_price());
			pstmt.setString(6, goods_sellVO.getGs_info());
			pstmt.setBytes(7, goods_sellVO.getGs_img());
			pstmt.setString(8, goods_sellVO.getGs_sta());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(Goods_SellVO goods_sellVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, goods_sellVO.getMf_no());
			pstmt.setString(2, goods_sellVO.getGs_name());
			pstmt.setTimestamp(3, goods_sellVO.getGs_date());
			pstmt.setInt(4, goods_sellVO.getGs_price());
			pstmt.setString(5, goods_sellVO.getGs_info());
			pstmt.setBytes(6, goods_sellVO.getGs_img());
			pstmt.setString(7, goods_sellVO.getGs_sta());
			pstmt.setString(8, goods_sellVO.getGs_no());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
	public void delete(String gs_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, gs_no);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
	public Goods_SellVO findByPrimaryKey(String gs_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Goods_SellVO goods_sellVO = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, gs_no);
			rs = pstmt.executeQuery();

			goods_sellVO = new Goods_SellVO();
			goods_sellVO.setGs_no(rs.getString("GS_NO"));
			goods_sellVO.setMf_no(rs.getString("MF_NO"));
			goods_sellVO.setGs_name(rs.getString("GS_NAME"));
			goods_sellVO.setGs_date(rs.getTimestamp("GS_DATE"));
			goods_sellVO.setGs_price(rs.getInt("GS_PRICE"));
			goods_sellVO.setGs_info(rs.getString("GS_INFO"));
			goods_sellVO.setGs_img(rs.getBytes("GS_IMG"));
			goods_sellVO.setGs_sta(rs.getString("GS_STA"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
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
		return goods_sellVO;
	}

	@Override
	public List<Goods_SellVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_SellVO> list = new ArrayList<Goods_SellVO>();
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Goods_SellVO goods_sellVO = new Goods_SellVO();
				goods_sellVO.setGs_no(rs.getString("GS_NO"));
				goods_sellVO.setMf_no(rs.getString("MF_NO"));
				goods_sellVO.setGs_name(rs.getString("GS_NAME"));
				goods_sellVO.setGs_date(rs.getTimestamp("GS_DATE"));
				goods_sellVO.setGs_price(rs.getInt("GS_PRICE"));
				goods_sellVO.setGs_info(rs.getString("GS_INFO"));
				goods_sellVO.setGs_img(rs.getBytes("GS_IMG"));
				goods_sellVO.setGs_sta(rs.getString("GS_STA"));
				list.add(goods_sellVO);

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return list;
	}
	public static void main(String[] args){
		
	}

}
