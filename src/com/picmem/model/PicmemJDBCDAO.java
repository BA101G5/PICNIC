package com.picmem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pboard_article.model.Pboard_ArticleVO;

public class PicmemJDBCDAO implements PicmemDAO_interface {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";
	private static final String INSERT_STMT = "insert into PICMEM (PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT) values(?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from PICMEM order by PICNIC_NO";
	private static final String GET_ALL_STMT_P = "select PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT from PICMEM where PICNIC_NO =?";
	private static final String GET_ONE_STMT = "select PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT from PICMEM where PICNIC_NO =? and MEM_NO =? order by PICNIC_NO";
	private static final String DELETE_STMT = "delete from PICMEM where PICNIC_NO =? MEM_NO =?";
	private static final String UPDATE_STMT = "update PICMEM set PICMEM_IDEN = ?,PICMEM_STA =?,MEM_LONGI =?,MEM_LATIT =? where PICNIC_NO =? and MEM_NO =?";
	private static final String INSERT_OWNER_STMT = "insert into PICMEM(PICNIC_NO,MEM_NO,PICMEM_IDEN)values(?,?,\'團主\')";
	private static final String GET_BYMEMNO_STMT = "select P_NO form PICMEM where MEM_NO =?";
	private static final String COUNT_STMT = "select COUNT(*) as COUNT from PICMEM where PICNIC_NO =? and MEM_NO =?";

	@Override
	public void insert(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, picmemVO.getPicnic_no());
			pstmt.setString(2, picmemVO.getMem_no());
			pstmt.setString(3, picmemVO.getPicmem_iden());
			pstmt.setString(4, picmemVO.getPicmem_sta());
			pstmt.setDouble(5, picmemVO.getMem_longi());
			pstmt.setDouble(6, picmemVO.getMem_latit());

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
	public void update(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, picmemVO.getPicmem_iden());
			pstmt.setString(2, picmemVO.getPicmem_sta());
			pstmt.setDouble(3, picmemVO.getMem_longi());
			pstmt.setDouble(4, picmemVO.getMem_latit());
			pstmt.setString(5, picmemVO.getPicnic_no());
			pstmt.setString(6, picmemVO.getMem_no());
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
	public void delete(String picnic_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, picnic_no);
			pstmt.setString(2, mem_no);
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
	public PicmemVO findByPrimaryKey(String picnic_no, String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PicmemVO picmemVO = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<PicmemVO> getAll(String picnic_no) {
		// GET_ALL_STMT_P
		List<PicmemVO> list = new ArrayList<PicmemVO>();
		PicmemVO picmemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_P);
			pstmt.setString(1, picnic_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				picmemVO = new PicmemVO();
				picmemVO.setPicnic_no(rs.getString("PICNIC_NO"));
				picmemVO.setMem_no(rs.getString("MEM_NO"));
				picmemVO.setPicmem_iden(rs.getString("PICMEM_IDEN"));
				picmemVO.setPicmem_sta(rs.getString("PICMEM_STA"));
				picmemVO.setMem_longi(rs.getDouble("MEM_LONGI"));
				picmemVO.setMem_latit(rs.getDouble("MEM_LATIT"));
				list.add(picmemVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<PicmemVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PicmemVO> list = new ArrayList<PicmemVO>();
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	

	public int count(String picnic_no, String mem_no) {
		int _count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(COUNT_STMT);
			pstmt.setString(1, picnic_no);
			pstmt.setString(2, mem_no);
			rs = pstmt.executeQuery();

			rs.next();
			_count = rs.getInt("COUNT");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return _count;
	}
	
	
	@Override
	public void insertowner(PicmemVO picmemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_OWNER_STMT);
			pstmt.setString(1, picmemVO.getPicnic_no());
			pstmt.setString(2, picmemVO.getMem_no());

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
	public List<String> findByMem_no(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYMEMNO_STMT);
			pstmt.setString(1,mem_no);
			rs=pstmt.executeQuery();
			while(rs.next()){
			list.add(rs.getString("PICNIC_NO"));	
			}} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {
		PicmemJDBCDAO picmemjdbcdao = new PicmemJDBCDAO();
		// insert
//		 PicmemVO picmemVO = new PicmemVO();
//		 picmemVO.setPicnic_no("PG00000006");
//		 picmemVO.setMem_no("MG00000003");
//		 picmemVO.setPicmem_iden("團員");
//		 picmemVO.setPicmem_sta("A");
//		 picmemVO.setMem_longi(121.13);
//		 picmemVO.setMem_latit(24.57);
//		 picmemjdbcdao.insert(picmemVO);
		// update
		// PicmemVO picmemVO = new PicmemVO();
		// picmemVO.setPicnic_no("PG00000001");
		// picmemVO.setMem_no("MG00000001");
		// picmemVO.setPicmem_iden("aoeuaoeu");
		// picmemVO.setPicmem_sta("A");
		// picmemVO.setMem_longi(44.012);
		// picmemVO.setMem_latit(44.022);
		// picmemjdbcdao.update(picmemVO);
		// delete
		// picmemjdbcdao.delete("PG00000001", "MG00000001");
		// search one
//		 PicmemVO picmemVO = picmemjdbcdao.findByPrimaryKey("PG00000001", "MG00000001");
//		 System.out.println(picmemVO.getPicnic_no());
//		 System.out.println(picmemVO.getMem_no());
//		 System.out.println(picmemVO.getPicmem_iden());
//		 System.out.println(picmemVO.getPicmem_sta());
//		 System.out.println(picmemVO.getMem_longi());
//		 System.out.println(picmemVO.getMem_latit());
		 System.out.println("---------------------");
		 System.out.println(picmemjdbcdao.count("PG00000001", "MG00000001"));
		 System.out.println("---------------------");
		 System.out.println(picmemjdbcdao.count("PG00000003", "MG00000009"));
		// search all
//		 List<PicmemVO> list = picmemjdbcdao.getAll("PG00000001");
//		 for (PicmemVO picmemVO_l : list) {
//			 System.out.println(picmemVO_l.getPicnic_no());
//			 System.out.println(picmemVO_l.getMem_no());
//			 System.out.println(picmemVO_l.getPicmem_iden());
//			 System.out.println(picmemVO_l.getPicmem_sta());
//			 System.out.println(picmemVO_l.getMem_longi());
//			 System.out.println(picmemVO_l.getMem_latit());
//			 System.out.println("---------------------");
//		 }
		// insert owner
		// PicmemVO picmemVO =new PicmemVO();
		// picmemVO.setPicnic_no("PG00000007");
		// picmemVO.setMem_no("MG00000002");
		// picmemjdbcdao.insertowner(picmemVO);

	}
	
	//-------------------------------------------------------------------
	public void k_insert(PicmemVO picmemVO){}
	public void k_update(PicmemVO picmemVO){}
	public void k_delete(String picnic_no,String mem_no){}
	public PicmemVO k_findByPrimaryKey(String picnic_no,String mem_no){return null;}
	public List<PicmemVO> k_getAll(){return null;}
	public void k_insertowner(PicmemVO picmemVO){}


}
