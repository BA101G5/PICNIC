package com.picmem.model;

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

public class PicmemJNDIDAO implements PicmemDAO_interface {
	private static DataSource ds = null;
	static {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "insert into PICMEM (PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT) values(?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from PICMEM order by PICNIC_NO";
	private static final String GET_ONE_STMT = "select PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT from PICMEM where PICNIC_NO =? and MEM_NO =? order by PICNIC_NO";
	private static final String DELETE_STMT = "delete from PICMEM where PICNIC_NO =? MEM_NO =?";
	private static final String UPDATE_STMT = "update PICMEM set PICMEM_IDEN = ?,PICMEM_STA =?,MEM_LONGI =?,MEM_LATIT =? where PICNIC_NO =? and MEM_NO =?";
	private static final String INSERT_OWNER_STMT = "insert into PICMEM(PICNIC_NO,MEM_NO,PICMEM_IDEN)values(?,?,'A')";
	private static final String GET_BYMEMNO_STMT = "select PICNIC_NO from PICMEM where MEM_NO =? and PICMEM_IDEN=\'A\'";
	private static final String GET_ALL_STMT_P = "select PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT from PICMEM where PICNIC_NO =?";
	@Override
	public void insert(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, picmemVO.getPicnic_no());
			pstmt.setString(2, picmemVO.getMem_no());
			pstmt.setString(3, picmemVO.getPicmem_iden());
			pstmt.setString(4, picmemVO.getPicmem_sta());
			pstmt.setDouble(5, picmemVO.getMem_longi());
			pstmt.setDouble(6, picmemVO.getMem_latit());

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
	public void update(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, picmemVO.getPicmem_iden());
			pstmt.setString(2, picmemVO.getPicmem_sta());
			pstmt.setDouble(3, picmemVO.getMem_longi());
			pstmt.setDouble(4, picmemVO.getMem_latit());
			pstmt.setString(5, picmemVO.getPicnic_no());
			pstmt.setString(6, picmemVO.getMem_no());
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
	public void delete(String picnic_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, picnic_no);
			pstmt.setString(2, mem_no);
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
	public PicmemVO findByPrimaryKey(String picnic_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PicmemVO picmemVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, picnic_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();

			rs.next();
			picmemVO = new PicmemVO();
			picmemVO.setPicnic_no(rs.getString("PICNIC_NO"));
			picmemVO.setMem_no(rs.getString("MEM_NO"));
			picmemVO.setPicmem_iden(rs.getString("PICMEM_IDEN"));
			picmemVO.setPicmem_sta(rs.getString("PICMEM_STA"));
			picmemVO.setMem_longi(rs.getDouble("MEM_LONGI"));
			picmemVO.setMem_latit(rs.getDouble("MEM_LATIT"));

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
		return picmemVO;
	}

	@Override
	public List<PicmemVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PicmemVO> list = new ArrayList<PicmemVO>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PicmemVO picmemVO = new PicmemVO();
				picmemVO.setPicnic_no(rs.getString("PICNIC_NO"));
				picmemVO.setMem_no(rs.getString("MEM_NO"));
				picmemVO.setPicmem_iden(rs.getString("PICMEM_IDEN"));
				picmemVO.setPicmem_sta(rs.getString("PICMEM_STA"));
				picmemVO.setMem_longi(rs.getDouble("MEM_LONGI"));
				picmemVO.setMem_latit(rs.getDouble("MEM_LATIT"));
				list.add(picmemVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public List<PicmemVO> getAll(String picnic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PicmemVO> list = new ArrayList<PicmemVO>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setString(1, picnic_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PicmemVO picmemVO = new PicmemVO();
				picmemVO.setPicnic_no(rs.getString("PICNIC_NO"));
				picmemVO.setMem_no(rs.getString("MEM_NO"));
				picmemVO.setPicmem_iden(rs.getString("PICMEM_IDEN"));
				picmemVO.setPicmem_sta(rs.getString("PICMEM_STA"));
				picmemVO.setMem_longi(rs.getDouble("MEM_LONGI"));
				picmemVO.setMem_latit(rs.getDouble("MEM_LATIT"));
				list.add(picmemVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public void insertowner(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_OWNER_STMT);
			pstmt.setString(1, picmemVO.getPicnic_no());
			pstmt.setString(2, picmemVO.getMem_no());

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
	public List<String> findByMem_no(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYMEMNO_STMT);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				list.add(rs.getString("PICNIC_NO"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
//--------------------------------------------------------------------------
	private static final String K_INSERT_STMT = "insert into PICMEM (PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT) values(?,?,?,?,?,?)";
	private static final String K_GET_ALL_STMT = "select * from PICMEM order by PICNIC_NO";
	private static final String K_GET_ONE_STMT = "select PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT from PICMEM where PICNIC_NO =? MEM_NO =? order by PICNIC_NO";
	private static final String K_DELETE_STMT = "delete from PICMEM where PICNIC_NO =? and MEM_NO =?";
	private static final String K_UPDATE_STMT = "update PICMEM set PICMEM_IDEN = ?,PICMEM_STA =?,MEM_LONGI =?,MEM_LATIT =? where PICNIC_NO =? and MEM_NO =?";
	private static final String K_INSERT_OWNER_STMT = "insert into PICMEM(PICNIC_NO,MEM_NO,PICMEM_IDEN)values(?,?,'團主')";
	private static final String K_GET_ONE_STMT_ALL_LIST = "select * from PICMEM where MEM_NO =? order by PICNIC_NO";
	@Override
	public void k_insert(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_INSERT_STMT);
			pstmt.setString(1, picmemVO.getPicnic_no());
			pstmt.setString(2, picmemVO.getMem_no());
			pstmt.setString(3, picmemVO.getPicmem_iden());
			pstmt.setString(4, picmemVO.getPicmem_sta());
			pstmt.setDouble(5, picmemVO.getMem_longi());
			pstmt.setDouble(6, picmemVO.getMem_latit());

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
	public void k_update(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_UPDATE_STMT);

			pstmt.setString(1, picmemVO.getPicmem_iden());
			pstmt.setString(2, picmemVO.getPicmem_sta());
			pstmt.setDouble(3, picmemVO.getMem_longi());
			pstmt.setDouble(4, picmemVO.getMem_latit());
			pstmt.setString(5, picmemVO.getPicnic_no());
			pstmt.setString(6, picmemVO.getMem_no());
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
	public void k_delete(String picnic_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_DELETE_STMT);
			pstmt.setString(1, picnic_no);
			pstmt.setString(2, mem_no);
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
	public PicmemVO k_findByPrimaryKey(String picnic_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PicmemVO picmemVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ONE_STMT);
			pstmt.setString(1, picnic_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();

			rs.next();
			picmemVO = new PicmemVO();
			picmemVO.setPicnic_no(rs.getString("PICNIC_NO"));
			picmemVO.setMem_no(rs.getString("MEM_NO"));
			picmemVO.setPicmem_iden(rs.getString("PICMEM_IDEN"));
			picmemVO.setPicmem_sta(rs.getString("PICMEM_STA"));
			picmemVO.setMem_longi(rs.getDouble("MEM_LONGI"));
			picmemVO.setMem_latit(rs.getDouble("MEM_LATIT"));

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
		return picmemVO;
	}

	@Override
	public List<PicmemVO> k_getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PicmemVO> list = new ArrayList<PicmemVO>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PicmemVO picmemVO = new PicmemVO();
				picmemVO.setPicnic_no(rs.getString("PICNIC_NO"));
				picmemVO.setMem_no(rs.getString("MEM_NO"));
				picmemVO.setPicmem_iden(rs.getString("PICMEM_IDEN"));
				picmemVO.setPicmem_sta(rs.getString("PICMEM_STA"));
				picmemVO.setMem_longi(rs.getDouble("MEM_LONGI"));
				picmemVO.setMem_latit(rs.getDouble("MEM_LATIT"));
				list.add(picmemVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
	public void k_insertowner(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(K_INSERT_OWNER_STMT);
			pstmt.setString(1, picmemVO.getPicnic_no());
			pstmt.setString(2, picmemVO.getMem_no());

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
	//----
	public List<String> k_getMemPicList(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> memPicList = new ArrayList<String>();

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ONE_STMT_ALL_LIST);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while(rs.next()){
				memPicList.add(rs.getString("PICNIC_NO"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
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
		return memPicList;
	}
}
