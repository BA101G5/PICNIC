package com.goods_rent.model;

import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Goods_RentJDBCDAO implements Goods_RentDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

	private static final String INSERT_STMT = "insert into GOODS_RENT(GR_NO,MF_NO,P_NO,GR_NAME,GR_DATE,GR_PRICE,GR_COUNT,GR_INFO,GR_IMG,GR_UNTIL,GR_STA,GR_PLACE) values('GR'||LPAD(GR_NO_SQ.nextval,8,0),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from GOODS_RENT order by GR_NO";
	private static final String GET_ONE_STMT = "select * from GOODS_RENT where GR_NO =?";
	private static final String DELETE_STMT = "delete from GOODS_RENT where GR_NO =?";
	private static final String UPDATE_STMT = "update GOODS_RENT set MF_NO=?,P_NO=?,GR_NAME=?,GR_DATE=?,GR_PRICE=?,GR_COUNT=?,GR_INFO=?,GR_IMG=?,GR_UNTIL=?,GR_STA=?,GR_PLACE=? where GR_NO=?";
	private static final String FINDBYPLACE_STMT = "select * from GOODS_RENT where MF_NO=? and GR_PLACE=? order by GR_NO";

	@Override
	public void insert(Goods_RentVO goods_rentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(Goods_RentVO goods_rentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
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
			pstmt.setString(12, goods_rentVO.getGr_no());

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
	public void delete(String gr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, gr_no);
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
	public Goods_RentVO findByPrimaryKey(String gr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Goods_RentVO goods_rentVO = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, gr_no);
			rs = pstmt.executeQuery();
			rs.next() ;
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
			goods_rentVO.setGr_place(rs.getString("GR_PLACE"));

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {
		Goods_RentJDBCDAO goods_rentjdbcdao = new Goods_RentJDBCDAO();
		// insert
//		 Goods_RentVO goods_rentVO = new Goods_RentVO();
//		 goods_rentVO.setMf_no("MM00000001");
//		 goods_rentVO.setP_no("P000000001");
//		 goods_rentVO.setGr_name("aoeuaoeu");
//		 goods_rentVO.setGr_date(java.sql.Timestamp.valueOf("2055-01-01 0:0:0"));
//		 goods_rentVO.setGr_price(10);
//		 goods_rentVO.setGr_count(14);
//		 goods_rentVO.setGr_info("aoeuaoeu");
//		 goods_rentVO.setGr_img(null);
//		 goods_rentVO.setGr_until(java.sql.Timestamp.valueOf("2055-01-01 0:0:0"));
//		 goods_rentVO.setGr_sta("A");
//		 goods_rentVO.setGr_place("оч╢щел");
//		 goods_rentjdbcdao.insert(goods_rentVO);
		// update
//		 Goods_RentVO goods_rentVO = new Goods_RentVO();
//		 goods_rentVO.setGr_no("GR00000012");
//		 goods_rentVO.setMf_no("MM00000003");
//		 goods_rentVO.setP_no("P000000001");
//		 goods_rentVO.setGr_name("123");
//		 goods_rentVO.setGr_date(java.sql.Timestamp.valueOf("2055-01-01 0:0:0"));
//		 goods_rentVO.setGr_price(0);
//		 goods_rentVO.setGr_count(1);
//		 goods_rentVO.setGr_info("132");
//		 goods_rentVO.setGr_img(null);
//		 goods_rentVO.setGr_until(java.sql.Timestamp.valueOf("2055-01-01 0:0:0"));
//		 goods_rentVO.setGr_place("123");
//		 goods_rentVO.setGr_sta("A");
//		 goods_rentjdbcdao.update(goods_rentVO);
		// delete
		// goods_rentjdbcdao.delete("");
		// search one
		 Goods_RentVO goods_rentVO = goods_rentjdbcdao.findByPrimaryKey("GR00000011");
		 System.out.println(goods_rentVO.getGr_no());
		 System.out.println(goods_rentVO.getMf_no());
		 System.out.println(goods_rentVO.getP_no());
		 System.out.println(goods_rentVO.getGr_name());
		 System.out.println(goods_rentVO.getGr_date());
		 System.out.println(goods_rentVO.getGr_price());
		 System.out.println(goods_rentVO.getGr_count());
		 System.out.println(goods_rentVO.getGr_info());
		 System.out.println(goods_rentVO.getGr_img());
		 System.out.println(goods_rentVO.getGr_until());
		 System.out.println(goods_rentVO.getGr_sta());
		 System.out.println(goods_rentVO.getGr_place());
		 System.out.println("---------------------");
		// search all
		// List<Goods_RentVO> list = goods_rentjdbcdao.getAll();
		// for (Goods_RentVO goods_rentVO : list) {
		// System.out.println(goods_rentVO.getGr_no());
		// System.out.println(goods_rentVO.getMf_no());
		// System.out.println(goods_rentVO.getP_no());
		// System.out.println(goods_rentVO.getGr_name());
		// System.out.println(goods_rentVO.getGr_date());
		// System.out.println(goods_rentVO.getGr_price());
		// System.out.println(goods_rentVO.getGr_count());
		// System.out.println(goods_rentVO.getGr_info());
		// System.out.println(goods_rentVO.getGr_img());
		// System.out.println(goods_rentVO.getGr_until());
		// System.out.println(goods_rentVO.getGr_sta());
		// System.out.println("---------------------");
		// }
	}

	private static byte[] getPicture(String path) {
		byte[] data = null;

		FileInputStream fileinput;
		try {
			fileinput = new FileInputStream(new File(path));
			ByteArrayOutputStream fileoutput = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int len = 0;
			while ((len = fileinput.read(buffer)) != -1) {
				fileoutput.write(buffer, 0, len);
			}
			data = fileoutput.toByteArray();
			fileinput.close();
			fileoutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public List<Goods_RentVO> findbyplace(String mf_no, String place) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_RentVO> list = new ArrayList<Goods_RentVO>();
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	//------------------------------------------
	public void k_insert(Goods_RentVO goods_rentVO){}
	public void k_update(Goods_RentVO goods_rentVO){}
	public void k_delete(String gr_no){}
	public Goods_RentVO k_findByPrimaryKey(String gr_no){return null;}
	public List<Goods_RentVO> k_getAll(){return null;}
}
