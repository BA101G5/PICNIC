package com.goods_sell.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.manufacturers.model.ManufacturersVO;

import java.io.*;

public class Goods_SellJDBCDAO implements Goods_SellDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

	private static final String INSERT_STMT = "insert into GOODS_SELL(GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA) values ('GS'||LPAD(GS_NO_SQ.nextval,8,0),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from GOODS_SELL ORDER BY GS_NO";
	private static final String GET_ONE_STMT = "select GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA from GOODS_SELL where GS_NO =?";
	private static final String DELETE_STMT = "delete from GOODS_SELL where GS_NO = ?";
	private static final String UPDATE_STMT = "update GOODS_SELL set MF_NO = ? ,GS_NAME = ? ,GS_DATE = ? ,GS_PRICE = ? ,GS_INFO = ? ,GS_IMG = ? ,GS_STA = ? where GS_NO = ? ";
	private static final String GET_COUNT_BYMF_STMT = "select count(GS_TYPE)  from GOODS_SELL where MF_NO  = ? ";
	private static final String GET_ALL_BYMF_STMT = "select *  from GOODS_SELL where  GS_TYPE = ? and MF_NO = ?";
	
	@Override
	public void insert(Goods_SellVO goods_sellVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, goods_sellVO.getMf_no());
			pstmt.setString(2, goods_sellVO.getGs_name());
			pstmt.setTimestamp(3, goods_sellVO.getGs_date());
			pstmt.setInt(4, goods_sellVO.getGs_price());
			pstmt.setString(5, goods_sellVO.getGs_info());
			pstmt.setBytes(6, goods_sellVO.getGs_img());
			pstmt.setString(7, goods_sellVO.getGs_sta());
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

			rs.next() ;
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

	public static void main(String[] args) {
		Goods_SellJDBCDAO goods_selljdbcdao = new Goods_SellJDBCDAO();
// insert
		 Goods_SellVO goods_sellVO = new Goods_SellVO();
		 goods_sellVO.setMf_no("MM00000001");
		 goods_sellVO.setGs_name("aoeu");
		 goods_sellVO.setGs_date(java.sql.Timestamp.valueOf("2055-01-01 0:0:0"));
		 goods_sellVO.setGs_price(10);
		 goods_sellVO.setGs_info("aeou");
		 goods_sellVO.setGs_img(getPicture("WebContent/nothing-here.jpg"));
		 goods_sellVO.setGs_sta("A");
		 goods_selljdbcdao.insert(goods_sellVO);
// update
		// Goods_SellVO goods_sellVO = new Goods_SellVO();
		// goods_sellVO.setGs_no("GS00000001");
		// goods_sellVO.setMf_no("MM00000001");
		// goods_sellVO.setGs_name(" aoeuaoeu");
		// goods_sellVO.setGs_date(java.sql.Timestamp.valueOf("2055-01-01 0:0:0"));
		// goods_sellVO.setGs_price(140);
		// goods_sellVO.setGs_info("aoeuaoeuaoeu");
		// goods_sellVO.setGs_img(getPicture("WebContent/nothing-here.jpg"));
		// goods_sellVO.setGs_sta("B");
		// goods_selljdbcdao.update(goods_sellVO);
// delete
//		 goods_selljdbcdao.delete("GS00000001");
// search one
		// Goods_SellVO goods_sellVO =
		// goods_selljdbcdao.findByPrimaryKey("GS00000002");
		// System.out.println(goods_sellVO.getGs_no());
		// System.out.println(goods_sellVO.getGs_no());
		// System.out.println(goods_sellVO.getMf_no());
		// System.out.println(goods_sellVO.getGs_name());
		// System.out.println(goods_sellVO.getGs_date());
		// System.out.println(goods_sellVO.getGs_price());
		// System.out.println(goods_sellVO.getGs_info());
		// System.out.println(goods_sellVO.getGs_img());
		// System.out.println(goods_sellVO.getGs_date());
		// System.out.println("---------------------");
//search all
		// List<Goods_SellVO> list = goods_selljdbcdao.getAll();
		// for(Goods_SellVO goods_sellVO : list){
		// System.out.println(goods_sellVO.getGs_no());
		// System.out.println(goods_sellVO.getGs_no());
		// System.out.println(goods_sellVO.getMf_no());
		// System.out.println(goods_sellVO.getGs_name());
		// System.out.println(goods_sellVO.getGs_date());
		// System.out.println(goods_sellVO.getGs_price());
		// System.out.println(goods_sellVO.getGs_info());
		// System.out.println(goods_sellVO.getGs_img());
		// System.out.println(goods_sellVO.getGs_date());
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
	public List<Goods_SellVO> findByType(String type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_SellVO> list = new ArrayList<Goods_SellVO>();
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_COUNT_BYMF_STMT);
			pstmt.setString(1, type);
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

	@Override
	public List<String> getcountbymf(List<ManufacturersVO> list2) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		    for(ManufacturersVO ManufacturersVO:list2){
			pstmt = con.prepareStatement(GET_COUNT_BYMF_STMT);	
			pstmt.setString(1, ManufacturersVO.getMF_NO());
			rs = pstmt.executeQuery();
		        rs.next();
		       String count=String.format("%s(%s)",ManufacturersVO.getMF_NO(), rs.getString("COUNT(GS_TYPE)"));
		        System.out.println(count);
		        list.add(count);
		        }

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
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

	
	@Override
	public List<Goods_SellVO> finBymf(String type, String mf) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_SellVO> list = new ArrayList<Goods_SellVO>();
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BYMF_STMT);
			pstmt.setString(1, type);
			pstmt.setString(2,mf);
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
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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

	@Override
	public String findByType(String type, String mf_no) {
		// TODO Auto-generated method stub
		return null;
	}
//------------------------------------------------------------------------------
	public void k_insert(Goods_SellVO goods_sellVO){}
	public void k_update(Goods_SellVO goods_sellVO){}
	public void k_delete(String gs_no){}
	public Goods_SellVO k_findByPrimaryKey(String gs_no){return null;}
	public List<Goods_SellVO> k_getAll(){return null;}
	}

