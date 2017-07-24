package com.place.model;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.Context;

public class PlaceJNDIDAO implements PlaceDAO_interface {
	private static DataSource ds=null;
	static{
			try {
				Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/ba101_5");
			} catch (NamingException e) {
				e.printStackTrace();
			}
	}

	private static final String INSERT_STMT = "insert into PLACE (P_NO,MF_NO,MEM_NO,P_NAME,P_UNTIL,P_PLACE,P_POP,PIMG,P_INFO,P_STA,P_PRICE,PICNIC_NO,P_LAT,P_LON)values('P'||LPAD(P_NO_SQ.NEXTVAL,9,0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from PLACE ORDER BY P_NO";
	private static final String GET_ONE_STMT = "select MF_NO,MEM_NO,P_NAME,P_UNTIL,P_PLACE,P_POP,PIMG,P_INFO,P_STA,P_PRICE,PICNIC_NO,P_LAT,P_LON from PLACE WHERE P_PLACE= ?";
	private static final String DELETE_STMT = "delete from PLACE where P_NO = ?";
	private static final String UPDATE_STMT = "update PLACE set MF_NO=?,MEM_NO=?,P_NAME=?,P_UNTIL=?,P_PLACE=?,P_POP=?,PIMG=?,P_INFO=?,P_STA=?,P_PRICE=?,PICNIC_NO = ?,P_LAT = ?,P_LON = ? where P_NO=?";
	private static final String INSERT_FROM_CUST_STMT = "insert into PLACE(P_NO,MEM_NO,P_PLACE,P_STA,PICNIC_NO,P_PRICE,P_LAT,P_LON)values('P'||LPAD(P_NO_SQ.NEXTVAL,9,0),?,?,'I',?,'0',?,?)";
	
	@Override
	public void insert(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		    con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, placeVO.getMf_no());
			pstmt.setString(2, placeVO.getMem_no());
			pstmt.setString(3, placeVO.getP_name());
			pstmt.setTimestamp(4, placeVO.getP_until());
			pstmt.setString(5, placeVO.getP_place());
			pstmt.setInt(6, placeVO.getP_pop());
			pstmt.setBytes(7, placeVO.getPimg());
			pstmt.setString(8, placeVO.getP_info());
			pstmt.setString(9, placeVO.getP_sta());
			pstmt.setInt(10, placeVO.getP_price());
			pstmt.setString(11, placeVO.getPicnic_no());
			pstmt.setDouble(12, placeVO.getP_lat());
			pstmt.setDouble(13, placeVO.getP_lon());

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
	public void update(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, placeVO.getMf_no());
			pstmt.setString(2, placeVO.getMem_no());
			pstmt.setString(3, placeVO.getP_name());
			pstmt.setTimestamp(4, placeVO.getP_until());
			pstmt.setString(5, placeVO.getP_place());
			pstmt.setInt(6, placeVO.getP_pop());
			pstmt.setBytes(7, placeVO.getPimg());
			pstmt.setString(8, placeVO.getP_info());
			pstmt.setString(9, placeVO.getP_sta());
			pstmt.setInt(10, placeVO.getP_price());
			pstmt.setString(11, placeVO.getPicnic_no());
			pstmt.setDouble(12, placeVO.getP_lat());
			pstmt.setDouble(13, placeVO.getP_lon());			
			pstmt.setString(14, placeVO.getP_no());
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
	public void delete(String p_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, p_no);
			pstmt.executeUpdate();
		}catch(SQLException e){
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
	public PlaceVO findByPrimaryKey(String P_place) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PlaceVO placeVO = null;
		ResultSet rs = null;
		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, P_place);
			rs = pstmt.executeQuery();
		
			rs.next();
			placeVO = new PlaceVO();
			placeVO.setMf_no(rs.getString("MF_NO"));
			placeVO.setMem_no(rs.getString("MEM_NO"));
			placeVO.setP_name(rs.getString("P_NAME"));
			placeVO.setP_until(rs.getTimestamp("P_UNTIL"));
			placeVO.setP_place(rs.getString("P_PLACE"));
			placeVO.setP_pop(rs.getInt("P_POP"));
			placeVO.setPimg(rs.getBytes("PIMG"));
			placeVO.setP_info(rs.getString("P_INFO"));
			placeVO.setP_sta(rs.getString("P_STA"));
			placeVO.setP_price(rs.getInt("P_PRICE"));
			placeVO.setPicnic_no(rs.getString("PICNIC_NO"));
			placeVO.setP_lat(rs.getDouble("P_LAT"));
			placeVO.setP_lon(rs.getDouble("P_LON"));
		
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
		return placeVO;

	}

	@Override
	public List<PlaceVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PlaceVO> list = new ArrayList<PlaceVO>();
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PlaceVO placeVO = new PlaceVO();
				placeVO.setMf_no(rs.getString("MF_NO"));
				placeVO.setMem_no(rs.getString("MEM_NO"));
				placeVO.setP_name(rs.getString("P_NAME"));
				placeVO.setP_until(rs.getTimestamp("P_UNTIL"));
				placeVO.setP_place(rs.getString("P_PLACE"));
				placeVO.setP_pop(rs.getInt("P_POP"));
				placeVO.setPimg(rs.getBytes("PIMG"));
				placeVO.setP_info(rs.getString("P_INFO"));
				placeVO.setP_sta(rs.getString("P_STA"));
				placeVO.setP_price(rs.getInt("P_PRICE"));
				placeVO.setPicnic_no(rs.getString("PICNIC_NO"));
				placeVO.setP_lat(rs.getDouble("P_LAT"));
				placeVO.setP_lon(rs.getDouble("P_LON"));
				list.add(placeVO);
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


	private static byte[] getPicture(String path) {
		byte[] data = null;
		FileInputStream fileinput;
		try {
			fileinput = new FileInputStream(new File(path));
			ByteArrayOutputStream fileoutput = new ByteArrayOutputStream();
			byte[] buffer = new byte[8152];
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
	public String insertplace(PlaceVO placeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_FROM_CUST_STMT,new int[]{1});
			pstmt.setString(1, placeVO.getMem_no());
			pstmt.setString(2, placeVO.getP_place());
			pstmt.setString(3, placeVO.getPicnic_no());
			pstmt.setDouble(4, placeVO.getP_lat());
			pstmt.setDouble(5, placeVO.getP_lon());
			pstmt.setInt(6, placeVO.getP_pop());
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			rs.next();
			String p_no = rs.getString(1);

			return p_no;
			
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
	public void k_insert(PlaceVO placeVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void k_update(PlaceVO placeVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void k_delete(String p_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PlaceVO k_findByPrimaryKey(String p_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaceVO> k_getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void k_insertplace(PlaceVO placeVO) {
		// TODO Auto-generated method stub
		
	}

}
