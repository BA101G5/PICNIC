package com.advertisement.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class AdvertisementDAO implements AdvertisementDAO_interface {
	
	private static final String INSERT = "INSERT INTO Advertisement(AD_NO,MF_NO,AD_SELF,AD_PHOTO,DAY_START,DAY_END,AD_CASH,AD_STA)"
			+ "VALUES('AD' || LPAD(AD_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Advertisement SET AD_SELF=?,AD_PHOTO=?,DAY_START=?,DAY_END=?,AD_CASH=?,AD_STA=? WHERE AD_NO=?";
	private static final String DELETE = "DELETE FROM Advertisement WHERE AD_NO=?";
	private static final String FINDBYKEY = "SELECT AD_NO,MF_NO,AD_SELF,AD_PHOTO,DAY_START,DAY_END,AD_CASH,AD_STA FROM Advertisement WHERE AD_NO=?";
	private static final String FINDALL = "SELECT * FROM Advertisement";
	private static final String FINDBYMM ="SELECT AD_NO,MF_NO,AD_SELF,AD_PHOTO,DAY_START,DAY_END,AD_CASH,AD_STA FROM Advertisement WHERE MF_NO=?";
	private static final String UPDATESTA="UPDATE Advertisement SET AD_STA=? WHERE AD_NO=?";
	
	private static final String FINDALL_U = "SELECT * FROM Advertisement where AD_STA='U'";
	private static final String FINDALL_OTHER = "SELECT * FROM Advertisement where AD_STA!='U'";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(AdvertisementVO AdvertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
			System.out.println("!!!!!!!!!!!");
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYKEY);
			pstmt.setString(1, AD_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aVO = new AdvertisementVO();
				aVO.setAD_NO(rs.getString("AD_NO"));
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aVO = new AdvertisementVO();
				aVO.setAD_NO(rs.getString("AD_NO"));
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

	@Override
	public List<AdvertisementVO> findByMM(String MF_NO) {
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO aVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYMM);
			pstmt.setString(1, MF_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aVO = new AdvertisementVO();
				aVO.setAD_NO(rs.getString("AD_NO"));
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

	@Override
	public void updateforSTA(AdvertisementVO AdvertisementVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTA);
			
			
			
			
			pstmt.setString(1, String.valueOf(AdvertisementVO.getAD_STA()));
			pstmt.setString(2, AdvertisementVO.getAD_NO());

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
	public List<AdvertisementVO> getAll_U() {
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO aVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDALL_U);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aVO = new AdvertisementVO();
				aVO.setAD_NO(rs.getString("AD_NO"));
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

	@Override
	public List<AdvertisementVO> getAll_Other() {
		List<AdvertisementVO> list = new ArrayList<AdvertisementVO>();
		AdvertisementVO aVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDALL_OTHER);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				aVO = new AdvertisementVO();
				aVO.setAD_NO(rs.getString("AD_NO"));
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



}
