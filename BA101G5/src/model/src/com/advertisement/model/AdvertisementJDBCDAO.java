package com.advertisement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.genereal_member.model.GeneralMemberVO;

public class AdvertisementJDBCDAO implements AdvertisementDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "ba101";
	private static final String PASSWORD = "ba101";
	private static final String INSERT = "INSERT INTO Advertisement(AD_NO,MF_NO,AD_SELF,AD_PHOTO,DAY_START,DAY_END,AD_CASH,AD_STA)"
			+ "VALUES('AD' || LPAD(AD_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Advertisement SET  AD_SELF=?,AD_PHOTO=?,DAY_START=?,DAY_END=?,AD_CASH=?,AD_STA=? WHERE AD_NO=?";
	private static final String DELETE = "DELETE FROM Advertisement WHERE AD_NO=?";
	private static final String FINDBYKEY = "SELECT MF_NO,AD_SELF,AD_PHOTO,DAY_START,DAY_END,AD_CASH,AD_STA FROM Advertisement WHERE AD_NO=?";
	private static final String FINDALL = "SELECT * FROM Advertisement";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(AdvertisementVO AdvertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, AdvertisementVO.getMF_NO());
			pstmt.setString(2, AdvertisementVO.getAD_SELF());
			pstmt.setBytes(3, AdvertisementVO.getAD_PHOTO());
			pstmt.setDate(4, AdvertisementVO.getDAY_START());
			pstmt.setDate(5, AdvertisementVO.getDAY_END());
			pstmt.setInt(6, AdvertisementVO.getAD_CASH());
			pstmt.setString(7, String.valueOf(AdvertisementVO.getAD_STA()));

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public void update(AdvertisementVO AdvertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, AdvertisementVO.getAD_SELF());
			pstmt.setBytes(2, AdvertisementVO.getAD_PHOTO());
			pstmt.setDate(3, AdvertisementVO.getDAY_START());
			pstmt.setDate(4, AdvertisementVO.getDAY_END());
			pstmt.setInt(5, AdvertisementVO.getAD_CASH());
			pstmt.setString(6, String.valueOf(AdvertisementVO.getAD_STA()));
			pstmt.setString(7, AdvertisementVO.getAD_NO());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public void delete(String AD_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, AD_NO);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public AdvertisementVO findByPrimaryKey(String AD_NO) {
		AdvertisementVO aVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(FINDBYKEY);
			pstmt.setString(1, AD_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aVO = new AdvertisementVO();
				aVO.setMF_NO(rs.getString("MF_NO"));
				aVO.setAD_SELF(rs.getString("AD_SELF"));
				aVO.setAD_PHOTO(rs.getBytes("AD_PHOTO"));
				aVO.setDAY_START(rs.getDate("DAY_START"));
				aVO.setDAY_END(rs.getDate("DAY_END"));
				aVO.setAD_CASH(rs.getInt("AD_CASH"));
				aVO.setAD_STA(rs.getString("AD_STA").charAt(0));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return aVO;
	}

	@Override
	public List<AdvertisementVO> getAll() {
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO aVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(FINDALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aVO = new AdvertisementVO();
				aVO.setMF_NO(rs.getString("MF_NO"));
				aVO.setAD_SELF(rs.getString("AD_SELF"));
				aVO.setAD_PHOTO(rs.getBytes("AD_PHOTO"));
				aVO.setDAY_START(rs.getDate("DAY_START"));
				aVO.setDAY_END(rs.getDate("DAY_END"));
				aVO.setAD_CASH(rs.getInt("AD_CASH"));
				aVO.setAD_STA(rs.getString("AD_STA").charAt(0));
				list.add(aVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {

		AdvertisementJDBCDAO dao = new AdvertisementJDBCDAO();
		// 1.insert
		// try {
		// AdvertisementVO aVO =new AdvertisementVO();
		// aVO.setMF_NO("MM00000004");
		// aVO.setAD_SELF("這是廣告這是廣告");
		// aVO.setAD_PHOTO(null);
		// aVO.setDAY_START(java.sql.Date.valueOf("2002-01-01"));
		// aVO.setDAY_END(java.sql.Date.valueOf("2012-01-01"));
		// aVO.setAD_CASH(1000);
		// aVO.setAD_STA('0');
		//
		// dao.insert(aVO);
		// System.out.println("Insert Success!!");
		// } catch (Exception se) {
		// System.out.println("Insert Fail!!");
		//
		// }

		// 2.update

		// AdvertisementVO aVO =new AdvertisementVO();
		//
		// aVO.setAD_SELF("dddd666");
		// aVO.setAD_PHOTO(null);
		// aVO.setDAY_START(java.sql.Date.valueOf("2002-01-01"));
		// aVO.setDAY_END(java.sql.Date.valueOf("2012-01-01"));
		// aVO.setAD_CASH(1000);
		// aVO.setAD_STA('0');
		// aVO.setAD_NO("AD00000002");
		// dao.update(aVO);
		// System.out.println("Update Success!!");

		// 3.delete

		// dao.delete("AD00000002");
		// System.out.println("Delete Success!!");

		// 4.FIND BY KEY

//		AdvertisementVO aVO = dao.findByPrimaryKey("AD00000001");
//		System.out.println("MF_NO : " + aVO.getMF_NO());
//		System.out.println("AD_SELF : " + aVO.getAD_SELF());
//		System.out.println("AD_PHOTO : " + aVO.getAD_PHOTO());
//		System.out.println("DAY_START : " + aVO.getDAY_START());
//		System.out.println("DAY_END : " + aVO.getDAY_END());
//		System.out.println("AD_CASH : " + aVO.getAD_CASH());
//		System.out.println("AD_STA : " + aVO.getAD_STA());

		// 5. FIND ALL
		List<AdvertisementVO> listgVO = dao.getAll();
		for (AdvertisementVO aVO : listgVO) {
			System.out.println("MF_NO : " + aVO.getMF_NO());
			System.out.println("AD_SELF : " + aVO.getAD_SELF());
			System.out.println("AD_PHOTO : " + aVO.getAD_PHOTO());
			System.out.println("DAY_START : " + aVO.getDAY_START());
			System.out.println("DAY_END : " + aVO.getDAY_END());
			System.out.println("AD_CASH : " + aVO.getAD_CASH());
			System.out.println("AD_STA : " + aVO.getAD_STA());
			System.out.println("-----------------");
		}

	}

}
