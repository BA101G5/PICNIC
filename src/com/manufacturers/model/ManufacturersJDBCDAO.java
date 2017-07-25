package com.manufacturers.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManufacturersJDBCDAO implements ManufacturersDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "BA101G5";
	private static final String PASSWORD = "BA101G5";
	private static final String INSERT = "INSERT INTO MANUFACTURERS(MF_NO,MF_NAME,MF_PHONE,MF_MAIL,MF_ACCO,MF_PSW,MF_LOGO,MF_SELF,MF_BS,MF_ADDR,MF_FAX,MF_STA,MF_REPORTNUM)"
			+ "VALUES('MM' || LPAD(MF_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE MANUFACTURERS SET MF_NAME=?,MF_PHONE=?,MF_MAIL=?, MF_ACCO=?, MF_PSW=? ,MF_LOGO=? ,MF_SELF=?, MF_BS=?, MF_ADDR=? ,MF_FAX=?,MF_STA=?,MF_REPORTNUM=? WHERE MF_NO=?";
	private static final String DELETE = "DELETE FROM MANUFACTURERS WHERE MF_NO=?";
	private static final String FINDBYKEY = "SELECT MF_NAME, MF_PHONE, MF_MAIL, MF_ACCO, MF_PSW, MF_SELF,MF_LOGO, MF_BS, MF_ADDR,MF_FAX,MF_STA,MF_REPORTNUM FROM MANUFACTURERS WHERE MF_NO=?";
	private static final String FINDALL = "SELECT * FROM MANUFACTURERS";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ManufacturersVO ManufacturersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, ManufacturersVO.getMF_NAME());
			pstmt.setString(2, String.valueOf(ManufacturersVO.getMF_PHONE()));
			pstmt.setString(3, ManufacturersVO.getMF_MAIL());
			pstmt.setString(4, ManufacturersVO.getMF_ACCO());
			pstmt.setString(5, ManufacturersVO.getMF_PSW());
			pstmt.setBytes(6, ManufacturersVO.getMF_LOGO());
			pstmt.setString(7, ManufacturersVO.getMF_SELF());
			pstmt.setString(8, String.valueOf(ManufacturersVO.getMF_BS()));
			pstmt.setString(9, ManufacturersVO.getMF_ADDR());
			pstmt.setString(10, String.valueOf(ManufacturersVO.getMF_FAX()));
			pstmt.setString(11, String.valueOf(ManufacturersVO.getMF_STA()));
			pstmt.setInt(12,ManufacturersVO.getMF_REPORTNUM());
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
	public void update(ManufacturersVO ManufacturersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, ManufacturersVO.getMF_NAME());
			pstmt.setString(2, ManufacturersVO.getMF_PHONE());
			pstmt.setString(3, ManufacturersVO.getMF_MAIL());
			pstmt.setString(4, ManufacturersVO.getMF_ACCO());
			pstmt.setString(5, ManufacturersVO.getMF_PSW());
			pstmt.setBytes(6, ManufacturersVO.getMF_LOGO());
			pstmt.setString(7, ManufacturersVO.getMF_SELF());
			pstmt.setString(8, ManufacturersVO.getMF_BS());
			pstmt.setString(9, ManufacturersVO.getMF_ADDR());
			pstmt.setString(10, ManufacturersVO.getMF_FAX());
			pstmt.setString(11, String.valueOf(ManufacturersVO.getMF_STA()));
			pstmt.setInt(12, ManufacturersVO.getMF_REPORTNUM());
			pstmt.setString(13, ManufacturersVO.getMF_NO());
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
	public void delete(String MF_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, MF_NO);

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
	public ManufacturersVO findByPrimaryKey(String MF_NO) {
		ManufacturersVO mVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(FINDBYKEY);
			pstmt.setString(1, MF_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mVO = new ManufacturersVO();
				mVO.setMF_NAME(rs.getString("MF_NAME"));
				mVO.setMF_PHONE(rs.getString("MF_PHONE"));
				mVO.setMF_MAIL(rs.getString("MF_MAIL"));
				mVO.setMF_ACCO(rs.getString("MF_ACCO"));
				mVO.setMF_PSW(rs.getString("MF_PSW"));
				mVO.setMF_LOGO(rs.getBytes("MF_LOGO"));
				mVO.setMF_SELF(rs.getString("MF_SELF"));
				mVO.setMF_BS(rs.getString("MF_BS"));
				mVO.setMF_ADDR(rs.getString("MF_ADDR"));
				mVO.setMF_FAX(rs.getString("MF_FAX"));
				mVO.setMF_STA(rs.getString("MF_STA").charAt(0));
				mVO.setMF_REPORTNUM(rs.getInt("MF_REPORTNUM"));
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
		return mVO;
	}

	@Override
	public List<ManufacturersVO> getAll() {
		List<ManufacturersVO> list = new ArrayList<ManufacturersVO>();
		ManufacturersVO mVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(FINDALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mVO = new ManufacturersVO();
				mVO.setMF_NAME(rs.getString("MF_NAME"));
				mVO.setMF_PHONE(rs.getString("MF_PHONE"));
				mVO.setMF_MAIL(rs.getString("MF_MAIL"));
				mVO.setMF_ACCO(rs.getString("MF_ACCO"));
				mVO.setMF_PSW(rs.getString("MF_PSW"));
				mVO.setMF_LOGO(rs.getBytes("MF_LOGO"));
				mVO.setMF_SELF(rs.getString("MF_SELF"));
				mVO.setMF_BS(rs.getString("MF_BS"));
				mVO.setMF_ADDR(rs.getString("MF_ADDR"));
				mVO.setMF_FAX(rs.getString("MF_FAX"));
				mVO.setMF_STA(rs.getString("MF_STA").charAt(0));
				mVO.setMF_REPORTNUM(rs.getInt("MF_REPORTNUM"));
				list.add(mVO);
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

		ManufacturersJDBCDAO dao = new ManufacturersJDBCDAO();
		// 1.insert
//		 try {
//		 ManufacturersVO mVO =new ManufacturersVO();
//		 mVO.setMF_NAME("味全");
//		 mVO.setMF_PHONE("0988161273");
//		 mVO.setMF_MAIL("MFIEWJO@reiij.com");
//		 mVO.setMF_ACCO("weichung");
//		 mVO.setMF_PSW("896dec7c3f066910");
//		 mVO.setMF_LOGO(null);
//		 mVO.setMF_SELF("hello we are wayc");
//		 mVO.setMF_BS("06445444");
//		 mVO.setMF_ADDR("台灣省桃園區中壢區中央一號");
//		 mVO.setMF_FAX("03-4445454");
//		 mVO.setMF_STA('U');
//		 mVO.setMF_REPORTNUM(10);
//		 dao.insert(mVO);
//		 System.out.println("Insert Success!!");
//		 } catch (Exception se) {
//		 System.out.println("Insert Fail!!");
//		
//		 }

		// 2.update

//		 
//		 ManufacturersVO mVO =new ManufacturersVO();
//		 mVO.setMF_NAME("味全");
//		 mVO.setMF_PHONE("0988161273");
//		 mVO.setMF_MAIL("MFIEWJO@reiij.com");
//		 mVO.setMF_ACCO("weichung");
//		 mVO.setMF_PSW("896dec7c3f066910");
//		 mVO.setMF_LOGO(null);
//		 mVO.setMF_SELF("hello we are wayc");
//		 mVO.setMF_BS("06445444");
//		 mVO.setMF_ADDR("台灣省桃園區中壢區中央一號");
//		 mVO.setMF_FAX("03-4445454");
//		 mVO.setMF_STA('U');
//		 mVO.setMF_REPORTNUM(25);
//		 mVO.setMF_NO("MM00000003");
//		 dao.update(mVO);
//		 System.out.println("Update Success!!");

		// 3.delete

//		 dao.delete("MM00000003");
//		 System.out.println("Delete Success!!");

		// 4.FIND BY KEY

//		 ManufacturersVO mVO =dao.findByPrimaryKey("MM00000004");
//		 System.out.println("MF_NAME : " + mVO.getMF_NAME());
//		 System.out.println("MF_PHONE : " + mVO.getMF_PHONE());
//		 System.out.println("MF_MAIL : " + mVO.getMF_MAIL());
//		 System.out.println("MF_ACCO : " + mVO.getMF_ACCO());
//		 System.out.println("MF_PSW : " + mVO.getMF_PSW());
//		 System.out.println("MF_LOGO : " + mVO.getMF_LOGO());
//		 System.out.println("MF_SELF : " + mVO.getMF_SELF());
//		 System.out.println("MF_BS : " + mVO.getMF_BS());
//		 System.out.println("MF_ADDR : " + mVO.getMF_ADDR());
//		 System.out.println("MF_FAX : " + mVO.getMF_FAX());
//		 System.out.println("MF_STA : " + mVO.getMF_STA());
//		 System.out.println("MF_REPORTNUM : " + mVO.getMF_REPORTNUM());

		// 5. FIND ALL
		List<ManufacturersVO> listgVO = dao.getAll();
		for (ManufacturersVO mVO : listgVO) {
			 System.out.println("MF_NAME : " + mVO.getMF_NAME());
			 System.out.println("MF_PHONE : " + mVO.getMF_PHONE());
			 System.out.println("MF_MAIL : " + mVO.getMF_MAIL());
			 System.out.println("MF_ACCO : " + mVO.getMF_ACCO());
			 System.out.println("MF_PSW : " + mVO.getMF_PSW());
			 System.out.println("MF_LOGO : " + mVO.getMF_LOGO());
			 System.out.println("MF_SELF : " + mVO.getMF_SELF());
			 System.out.println("MF_BS : " + mVO.getMF_BS());
			 System.out.println("MF_ADDR : " + mVO.getMF_ADDR());
			 System.out.println("MF_FAX : " + mVO.getMF_FAX());
			 System.out.println("MF_STA : " + mVO.getMF_STA());
			 System.out.println("MF_REPORTNUM : " + mVO.getMF_REPORTNUM());
			 System.out.println("-----------------");
			
		}
		

	}

	@Override
	public void updateforSTA(ManufacturersVO ManufacturersVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String findByMfName(String mf_name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void k_insert(ManufacturersVO MANUFACTURERSVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void k_update(ManufacturersVO MANUFACTURERSVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void k_delete(String MF_NO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ManufacturersVO k_findByPrimaryKey(String MF_NO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManufacturersVO> k_getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
