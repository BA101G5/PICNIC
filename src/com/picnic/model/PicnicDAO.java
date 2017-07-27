package com.picnic.model;

import java.sql.*;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PicnicDAO implements PicnicDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into Picnic (PICNIC_NO,PICNIC_NAME,PICNIC_DESC,PICNICDATE,PICNIC_STARTUP,PICNIC_SETUP,PICNIC_CHK,PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DATE_ORD_DM,ORD_STA) VALUES('PG'||LPAD(PICNIC_NO_SQ.NEXTVAL,8,0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select PICNIC_NO,PICNIC_NAME,PICNIC_DESC,PICKUPDATE,PICNIC_STARTUP,PICNIC_SETUP,PICNIC_CHK,PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DATE,ORD_DM,ORD_STA FROM PICNIC Order by PICNIC_NO";
	private static final String GET_ALL_SL_STMT = "select PICNIC_NO,PICNIC_NAME,PICNIC_DESC,PICKUPDATE,PICNIC_STARTUP,PICNIC_SETUP,PICNIC_CHK,PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DATE,ORD_DM,ORD_STA FROM PICNIC WHERE PICNIC_STA in ('S', 'L') Order by PICNIC_NO";
	private static final String GET_ONE_STMT = "select PICNIC_NO,PICNIC_NAME,PICNIC_DESC,PICKUPDATE,PICNIC_STARTUP,PICNIC_SETUP,PICNIC_CHK,PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DATE,ORD_DM,ORD_STA FROM PICNIC WHERE PICNIC_NO = ?";
	private static final String DELETE_STMT = "delete from PICNIC where PICNIC = ?";
	private static final String UPDATE_STMT = "update PICNIC set PICNIC_NAME=?,PICNIC_DESC=?,PICKUPDATE=?,PICNIC_STARTUP=?,PICNIC_SETUP=?,PICNIC_CHK=?,PICNIC_DATE=?,PICNIC_PL=?,PICNIC_STA=?,ORD_TOTAL=?,ORD_DATE=?,ORD_DM=?,ORD_STA=? where PICNIC_NO=?";
	private static final String INSERT_INITIATESTMT = "insert into PICNIC (PICNIC_NO,PICNIC_NAME,PICNIC_STARTUP,PICNIC_CHK, PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DM,ORD_STA) VALUES('PG'||LPAD(PICNIC_NO_SQ.NEXTVAL,8,0),?,SYSDATE,'N',?,?,'L','0','N','N')";
	private static final String GET_ONEWHERE_STMT = "select PICNIC_NO,PICNIC_NAME from PICNIC WHERE PICNIC_NO=? and PICNIC_CHK =\'N\' and ORD_STA=\'N\'";
	private static final String UPDATEFINISHEDPICNIC__STMT = "update PICNIC set PICNIC_CHK=?,PICNIC_STA=?,ORD_TOTAL=?,ORD_DATE=SYSDATE,ORD_STA=? where PICNIC_NO=?";
	private static final String UPDATE_STMT_DESC = "update PICNIC set PICNIC_DESC=? where PICNIC_NO=?";
	
	@Override
	public void insert(PicnicVO picnicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, picnicVO.getPicnic_name());
			pstmt.setString(2, picnicVO.getPicnic_desc());
			pstmt.setTimestamp(3, picnicVO.getPicupdate());
			pstmt.setTimestamp(4, picnicVO.getPicnic_startup());
			pstmt.setTimestamp(5, picnicVO.getPicnic_setup());
			pstmt.setString(6, picnicVO.getPicnic_chk());
			pstmt.setTimestamp(7, picnicVO.getPicnic_date());
			pstmt.setInt(8, picnicVO.getPicnic_pl());
			pstmt.setString(9, picnicVO.getPicnic_sta());
			pstmt.setDouble(10, picnicVO.getOrd_total());
			pstmt.setTimestamp(11, picnicVO.getOrd_date());
			pstmt.setString(12, picnicVO.getOrd_dm());
			pstmt.setString(13, picnicVO.getOrd_sta());

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
	public void update(PicnicVO picnicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, picnicVO.getPicnic_name());
			pstmt.setString(2, picnicVO.getPicnic_desc());
			pstmt.setTimestamp(3, picnicVO.getPicupdate());
			pstmt.setTimestamp(4, picnicVO.getPicnic_startup());
			pstmt.setTimestamp(5, picnicVO.getPicnic_setup());
			pstmt.setString(6, picnicVO.getPicnic_chk());
			pstmt.setTimestamp(7, picnicVO.getPicnic_date());
			pstmt.setInt(8, picnicVO.getPicnic_pl());
			pstmt.setString(9, picnicVO.getPicnic_sta());
			pstmt.setDouble(10, picnicVO.getOrd_total());
			pstmt.setTimestamp(11, picnicVO.getOrd_date());
			pstmt.setString(12, picnicVO.getOrd_dm());
			pstmt.setString(13, picnicVO.getOrd_sta());
			pstmt.setString(14, picnicVO.getPicnic_no());

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
	public void delete(String picnic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, picnic_no);

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
	public PicnicVO findByPrimaryKey(String picnic_no) {

		PicnicVO picnicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, picnic_no);
			rs = pstmt.executeQuery();

			rs.next();
			picnicVO = new PicnicVO();
			picnicVO.setPicnic_no(rs.getString("PICNIC_NO"));
			picnicVO.setPicnic_name(rs.getString("PICNIC_NAME"));
			picnicVO.setPicnic_desc(rs.getString("PICNIC_DESC"));
			picnicVO.setPicupdate(rs.getTimestamp("PICKUPDATE"));
			picnicVO.setPicnic_startup(rs.getTimestamp("PICNIC_STARTUP"));
			picnicVO.setPicnic_setup(rs.getTimestamp("PICNIC_SETUP"));
			picnicVO.setPicnic_chk(rs.getString("PICNIC_CHK"));
			picnicVO.setPicnic_date(rs.getTimestamp("PICNIC_DATE"));
			picnicVO.setPicnic_pl(rs.getInt("PICNIC_PL"));
			picnicVO.setPicnic_sta(rs.getString("PICNIC_STA"));
			picnicVO.setOrd_total(rs.getDouble("ORD_TOTAL"));
			picnicVO.setOrd_date(rs.getTimestamp("ORD_DATE"));
			picnicVO.setOrd_dm(rs.getString("ORD_DM"));
			picnicVO.setOrd_sta(rs.getString("ORD_STA"));

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return picnicVO;

	}

	@Override
	public List<PicnicVO> getAll() {
		List<PicnicVO> list = new ArrayList<PicnicVO>();
		PicnicVO picnicVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				picnicVO = new PicnicVO();
				picnicVO.setPicnic_no(rs.getString("PICNIC_NO"));
				picnicVO.setPicnic_name(rs.getString("PICNIC_NAME"));
				picnicVO.setPicnic_desc(rs.getString("PICNIC_DESC"));
				picnicVO.setPicupdate(rs.getTimestamp("PICKUPDATE"));
				picnicVO.setPicnic_startup(rs.getTimestamp("PICNIC_STARTUP"));
				picnicVO.setPicnic_setup(rs.getTimestamp("PICNIC_SETUP"));
				picnicVO.setPicnic_chk(rs.getString("PICNIC_CHK"));
				picnicVO.setPicnic_date(rs.getTimestamp("PICNIC_DATE"));
				picnicVO.setPicnic_pl(rs.getInt("PICNIC_PL"));
				picnicVO.setPicnic_sta(rs.getString("PICNIC_STA"));
				picnicVO.setOrd_total(rs.getDouble("ORD_TOTAL"));
				picnicVO.setOrd_date(rs.getTimestamp("ORD_DATE"));
				picnicVO.setOrd_dm(rs.getString("ORD_DM"));
				picnicVO.setOrd_sta(rs.getString("ORD_STA"));

				list.add(picnicVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	
	public List<PicnicVO> getAll_sl() {
		List<PicnicVO> list = new ArrayList<PicnicVO>();
		PicnicVO picnicVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				picnicVO = new PicnicVO();
				picnicVO.setPicnic_no(rs.getString("PICNIC_NO"));
				picnicVO.setPicnic_name(rs.getString("PICNIC_NAME"));
				picnicVO.setPicnic_desc(rs.getString("PICNIC_DESC"));
				picnicVO.setPicupdate(rs.getTimestamp("PICKUPDATE"));
				picnicVO.setPicnic_startup(rs.getTimestamp("PICNIC_STARTUP"));
				picnicVO.setPicnic_setup(rs.getTimestamp("PICNIC_SETUP"));
				picnicVO.setPicnic_chk(rs.getString("PICNIC_CHK"));
				picnicVO.setPicnic_date(rs.getTimestamp("PICNIC_DATE"));
				picnicVO.setPicnic_pl(rs.getInt("PICNIC_PL"));
				picnicVO.setPicnic_sta(rs.getString("PICNIC_STA"));
				picnicVO.setOrd_total(rs.getDouble("ORD_TOTAL"));
				picnicVO.setOrd_date(rs.getTimestamp("ORD_DATE"));
				picnicVO.setOrd_dm(rs.getString("ORD_DM"));
				picnicVO.setOrd_sta(rs.getString("ORD_STA"));

				list.add(picnicVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}
	
	@Override
	public String addPicnic(PicnicVO picnicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(INSERT_INITIATESTMT,new int[]{1});
			pstmt.setString(1, picnicVO.getPicnic_name());
			pstmt.setTimestamp(2, picnicVO.getPicnic_date());
			pstmt.setInt(3, picnicVO.getPicnic_pl());
			
			pstmt.executeUpdate();
			
			rs=pstmt.getGeneratedKeys();
			rs.next();
			String id =rs.getString(1);
			
			return id;
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(rs!=null){
				try{rs.close();
				}catch(SQLException se){
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

	}

	
	public PicnicVO findByPrimaryKeywherepicnic_no(String picnic_no) {
	
		PicnicVO picnicVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEWHERE_STMT);
			pstmt.setString(1, picnic_no);
		
			rs = pstmt.executeQuery();
			if(rs.next()){
			
			
			picnicVO = new PicnicVO();
			picnicVO.setPicnic_no(rs.getString("PICNIC_NO"));
			picnicVO.setPicnic_name(rs.getString("PICNIC_NAME"));		
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return picnicVO;

	}
	//-----------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------
	private static final String K_INSERT_STMT = "insert into Picnic (PICNIC_NO,PICNIC_NAME,PICNIC_DESC,PICNICDATE,PICNIC_STARTUP,PICNIC_SETUP,PICNIC_CHK,PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DATE_ORD_DM,ORD_STA) VALUES('PG'||LPAD(PICNIC_NO_SQ.NEXTVAL,8,0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String K_GET_ALL_STMT = "select PICNIC_NO,PICNIC_NAME,PICNIC_DESC,PICKUPDATE,PICNIC_STARTUP,PICNIC_SETUP,PICNIC_CHK,PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DATE,ORD_DM,ORD_STA FROM PICNIC where ORD_STA != 'U'  Order by PICNIC_NO";
	private static final String K_GET_ONE_STMT = "select PICNIC_NO,PICNIC_NAME,PICNIC_DESC,PICKUPDATE,PICNIC_STARTUP,PICNIC_SETUP,PICNIC_CHK,PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DATE,ORD_DM,ORD_STA FROM PICNIC WHERE PICNIC_NO = ?";
	private static final String K_DELETE_STMT = "delete from PICNIC where PICNIC_NO = ? ";
	private static final String K_UPDATE_STMT = "update PICNIC set PICNIC_NAME=?,PICNIC_DESC=?,PICKUPDATE=?,PICNIC_STARTUP=?,PICNIC_SETUP=?,PICNIC_CHK=?,PICNIC_DATE=?,PICNIC_PL=?,PICNIC_STA=?,ORD_TOTAL=?,ORD_DATE=?,ORD_DM=?,ORD_STA=? where PICNICNO=?";
	private static final String K_INSERT_INITIATESTMT = "insert into PICNIC (PICNIC_NO,PICNIC_NAME,PICNIC_STARTUP,PICNIC_CHK, PICNIC_DATE,PICNIC_PL,PICNIC_STA,ORD_TOTAL,ORD_DM,ORD_STA) VALUES('PG'||LPAD(PICNIC_NO_SQ.NEXTVAL,8,0),?,SYSDATE,'N',?,?,'L','0','N','N')";
	private static final String K_GET_ALL_STMT_MEM = "select pic.PICNIC_NO,picm.MEM_NO "
			+ "from PICNIC pic left outer join (select * from PICMEM where MEM_NO = ?) picm on pic.PICNIC_NO = picm.PICNIC_NO "
			+ " where  ORD_STA != 'U' ";
	
	private static final String K_GET_SEARCH = "select * from PICNIC pic  where  ORD_STA != 'U' ";

	
	@Override
	public void k_insert(PicnicVO picnicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_INSERT_STMT);
			pstmt.setString(1, picnicVO.getPicnic_name());
			pstmt.setString(2, picnicVO.getPicnic_desc());
			pstmt.setTimestamp(3, picnicVO.getPicupdate());
			pstmt.setTimestamp(4, picnicVO.getPicnic_startup());
			pstmt.setTimestamp(5, picnicVO.getPicnic_setup());
			pstmt.setString(6, picnicVO.getPicnic_chk());
			pstmt.setTimestamp(7, picnicVO.getPicnic_date());
			pstmt.setInt(8, picnicVO.getPicnic_pl());
			pstmt.setString(9, picnicVO.getPicnic_sta());
			pstmt.setDouble(10, picnicVO.getOrd_total());
			pstmt.setTimestamp(11, picnicVO.getOrd_date());
			pstmt.setString(12, picnicVO.getOrd_dm());
			pstmt.setString(13, picnicVO.getOrd_sta());

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
	public void k_update(PicnicVO picnicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_UPDATE_STMT);

			pstmt.setString(1, picnicVO.getPicnic_name());
			pstmt.setString(2, picnicVO.getPicnic_desc());
			pstmt.setTimestamp(3, picnicVO.getPicupdate());
			pstmt.setTimestamp(4, picnicVO.getPicnic_startup());
			pstmt.setTimestamp(5, picnicVO.getPicnic_setup());
			pstmt.setString(6, picnicVO.getPicnic_chk());
			pstmt.setTimestamp(7, picnicVO.getPicnic_date());
			pstmt.setInt(8, picnicVO.getPicnic_pl());
			pstmt.setString(9, picnicVO.getPicnic_sta());
			pstmt.setDouble(10, picnicVO.getOrd_total());
			pstmt.setTimestamp(11, picnicVO.getOrd_date());
			pstmt.setString(12, picnicVO.getOrd_dm());
			pstmt.setString(13, picnicVO.getOrd_sta());

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
	public void k_delete(String picnic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_DELETE_STMT);

			pstmt.setString(1, picnic_no);

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
	public PicnicVO k_findByPrimaryKey(String picnic_no) {

		PicnicVO picnicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ONE_STMT);
			pstmt.setString(1, picnic_no);
			rs = pstmt.executeQuery();

			rs.next();
			picnicVO = new PicnicVO();
			
			picnicVO.setPicnic_no(rs.getString("PICNIC_NO"));
			picnicVO.setPicnic_name(rs.getString("PICNIC_NAME"));
			picnicVO.setPicnic_desc(rs.getString("PICNIC_DESC"));
			picnicVO.setPicupdate(rs.getTimestamp("PICKUPDATE"));
			picnicVO.setPicnic_startup(rs.getTimestamp("PICNIC_STARTUP"));
			picnicVO.setPicnic_setup(rs.getTimestamp("PICNIC_SETUP"));
			picnicVO.setPicnic_chk(rs.getString("PICNIC_CHK"));
			picnicVO.setPicnic_date(rs.getTimestamp("PICNIC_DATE"));
			picnicVO.setPicnic_pl(rs.getInt("PICNIC_PL"));
			picnicVO.setPicnic_sta(rs.getString("PICNIC_STA"));
			picnicVO.setOrd_total(rs.getDouble("ORD_TOTAL"));
			picnicVO.setOrd_date(rs.getTimestamp("ORD_DATE"));
			picnicVO.setOrd_dm(rs.getString("ORD_DM"));
			picnicVO.setOrd_sta(rs.getString("Ord_sta"));

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return picnicVO;

	}

	@Override
	public List<PicnicVO> k_getAll() {
		List<PicnicVO> list = new ArrayList<PicnicVO>();
		PicnicVO picnicVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				picnicVO = new PicnicVO();
				picnicVO.setPicnic_no(rs.getString("PICNIC_NO"));
				picnicVO.setPicnic_name(rs.getString("PICNIC_NAME"));
				picnicVO.setPicnic_desc(rs.getString("PICNIC_DESC"));
				picnicVO.setPicupdate(rs.getTimestamp("PICKUPDATE"));
				picnicVO.setPicnic_startup(rs.getTimestamp("PICNIC_STARTUP"));
				picnicVO.setPicnic_setup(rs.getTimestamp("PICNIC_SETUP"));
				picnicVO.setPicnic_chk(rs.getString("PICNIC_CHK"));
				picnicVO.setPicnic_date(rs.getTimestamp("PICNIC_DATE"));
				picnicVO.setPicnic_pl(rs.getInt("PICNIC_PL"));
				picnicVO.setPicnic_sta(rs.getString("PICNIC_STA"));
				picnicVO.setOrd_total(rs.getDouble("ORD_TOTAL"));
				picnicVO.setOrd_date(rs.getTimestamp("ORD_DATE"));
				picnicVO.setOrd_dm(rs.getString("ORD_DM"));
				picnicVO.setOrd_sta(rs.getString("ORD_STA"));

				list.add(picnicVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public String k_addPicnic(PicnicVO picnicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(K_INSERT_INITIATESTMT,new int[]{1});
			pstmt.setString(1, picnicVO.getPicnic_name());
			pstmt.setTimestamp(2, picnicVO.getPicnic_date());
			pstmt.setInt(3, picnicVO.getPicnic_pl());
			
			pstmt.executeUpdate();
			
			rs=pstmt.getGeneratedKeys();
			rs.next();
			String id =rs.getString(1);
			
			return id;
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(rs!=null){
				try{rs.close();
				}catch(SQLException se){
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

	}

	public List<String> k_getAllandMEM(String MEM_NO,String searchWord) {
		
		List<String> addPicList = new ArrayList<String>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			if(searchWord == ""){
				pstmt = con.prepareStatement(K_GET_ALL_STMT_MEM);
			}else{
				pstmt = con.prepareStatement(K_GET_ALL_STMT_MEM + searchWord);
				//pstmt.setString(1, search_string);
			}
			
			pstmt.setString(1, MEM_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(null == (rs.getString("MEM_NO"))){
					addPicList.add("¥[¤J");
				}else{
					addPicList.add("°h¥X");
				}
	
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return addPicList;
	}
	
	public List<PicnicVO> k_get_search_list(String searchWord){
		List<PicnicVO> list = new ArrayList<PicnicVO>();
		PicnicVO picnicVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			if(searchWord == ""){
				pstmt = con.prepareStatement(K_GET_SEARCH);
			}else{
				pstmt = con.prepareStatement(K_GET_SEARCH + searchWord);
				//pstmt.setString(1, search_string);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				picnicVO = new PicnicVO();
				picnicVO.setPicnic_no(rs.getString("PICNIC_NO"));
				picnicVO.setPicnic_name(rs.getString("PICNIC_NAME"));
				picnicVO.setPicnic_desc(rs.getString("PICNIC_DESC"));
				picnicVO.setPicupdate(rs.getTimestamp("PICKUPDATE"));
				picnicVO.setPicnic_startup(rs.getTimestamp("PICNIC_STARTUP"));
				picnicVO.setPicnic_setup(rs.getTimestamp("PICNIC_SETUP"));
				picnicVO.setPicnic_chk(rs.getString("PICNIC_CHK"));
				picnicVO.setPicnic_date(rs.getTimestamp("PICNIC_DATE"));
				picnicVO.setPicnic_pl(rs.getInt("PICNIC_PL"));
				picnicVO.setPicnic_sta(rs.getString("PICNIC_STA"));
				picnicVO.setOrd_total(rs.getDouble("ORD_TOTAL"));
				picnicVO.setOrd_date(rs.getTimestamp("ORD_DATE"));
				picnicVO.setOrd_dm(rs.getString("ORD_DM"));
				picnicVO.setOrd_sta(rs.getString("ORD_STA"));

				list.add(picnicVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (final SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	public void updatepicnic(String picnic_no,Integer tlprice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEFINISHEDPICNIC__STMT);
			System.out.println(tlprice);
			pstmt.setString(1, "F");
			pstmt.setString(2, "F");
			pstmt.setInt(3, tlprice);
			pstmt.setString(4, "F");
			pstmt.setString(5,picnic_no);
	
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

	
	
	public void update_desc(String picnic_no, String desc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT_DESC);
	
			pstmt.setString(1, desc);
			pstmt.setString(2, picnic_no);
	
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
}
