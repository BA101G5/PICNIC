package com.manufacturers.model;

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

public class ManufacturersDAO implements ManufacturersDAO_interface {
	
	private static final String INSERT = "INSERT INTO MANUFACTURERS(MF_NO,MF_NAME,MF_PHONE,MF_MAIL,MF_ACCO,MF_PSW,MF_LOGO,MF_SELF,MF_BS,MF_ADDR,MF_FAX,MF_STA,MF_REPORTNUM)"
			+ "VALUES('MM' || LPAD(MF_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE MANUFACTURERS SET MF_NAME=?,MF_PHONE=?,MF_MAIL=?, MF_ACCO=?, MF_PSW=? ,MF_LOGO=? ,MF_SELF=?, MF_BS=?, MF_ADDR=? ,MF_FAX=?,MF_STA=?,MF_REPORTNUM=? WHERE MF_NO=?";
	private static final String DELETE = "DELETE FROM MANUFACTURERS WHERE MF_NO=?";
	private static final String FINDBYKEY = "SELECT MF_NAME, MF_PHONE, MF_MAIL, MF_ACCO, MF_PSW, MF_SELF,MF_LOGO, MF_BS, MF_ADDR,MF_FAX,MF_STA,MF_REPORTNUM FROM MANUFACTURERS WHERE MF_NO=?";
	private static final String FINDALL = "SELECT * FROM MANUFACTURERS";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ManufacturersVO ManufacturersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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

	

}
