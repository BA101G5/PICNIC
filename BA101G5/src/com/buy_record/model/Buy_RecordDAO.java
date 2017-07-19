package com.buy_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Buy_RecordDAO implements Buy_RecordDAO_interface {
	

	private static final String INSERT = "INSERT INTO BUY_RECORD(BR_ID,MEM_NO,BR_DATE,BR_CASH)"
			+ "VALUES('BR' || LPAD(BR_NO_SQ.NEXTVAL, 8, '0'),?,?,?)";
	private static final String UPDATE = "UPDATE BUY_RECORD SET  MEM_NO=?,BR_DATE=?,BR_CASH=? WHERE BR_ID=?";
	private static final String DELETE = "DELETE FROM BUY_RECORD WHERE BR_ID=?";
	private static final String FINDBYKEY = "SELECT * FROM BUY_RECORD WHERE BR_ID=?";
	private static final String FINDALL = "SELECT * FROM BUY_RECORD";
	private static final String FINDMG ="SELECT * FROM BUY_RECORD WHERE MEM_NO=?";
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
	public void insert(Buy_RecordVO Buy_recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, Buy_recordVO.getMEM_NO());
			pstmt.setTimestamp(2, Buy_recordVO.getBR_DATE());
			pstmt.setInt(3, Buy_recordVO.getBR_CASH());

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
	public void update(Buy_RecordVO Buy_recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Buy_recordVO.getMEM_NO());
			pstmt.setTimestamp(2, Buy_recordVO.getBR_DATE());
			pstmt.setInt(3, Buy_recordVO.getBR_CASH());
			pstmt.setString(4, Buy_recordVO.getBR_ID());

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
	public void delete(String BR_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, BR_NO);

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
	public Buy_RecordVO findByPrimaryKey(String BR_ID) {
		Buy_RecordVO bVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYKEY);
			pstmt.setString(1, BR_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bVO = new Buy_RecordVO();
				bVO.setBR_ID(rs.getString("BR_ID"));
				bVO.setMEM_NO(rs.getString("MEM_NO"));
				bVO.setBR_DATE(rs.getTimestamp("BR_DATE"));
				bVO.setBR_CASH(rs.getInt("BR_CASH"));
				

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt !=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bVO;
	}

	

	@Override
	public List<Buy_RecordVO> getAll() {
		List<Buy_RecordVO> list = new ArrayList<Buy_RecordVO>();
		Buy_RecordVO bVO = null;
		Connection con =null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(FINDALL);
			rs =pstmt.executeQuery();
			while(rs.next()){
				bVO = new Buy_RecordVO();
				bVO.setBR_ID(rs.getString("BR_ID"));
				bVO.setMEM_NO(rs.getString("MEM_NO"));
				bVO.setBR_DATE(rs.getTimestamp("BR_DATE"));
				bVO.setBR_CASH(rs.getInt("BR_CASH"));
				
				list.add(bVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt !=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
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
	public List<Buy_RecordVO> findByMG(String MEM_NO) {
		List<Buy_RecordVO> list = new ArrayList<Buy_RecordVO>();
		Buy_RecordVO bVO = null;
		Connection con =null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(FINDMG);
			pstmt.setString(1, MEM_NO);
			rs =pstmt.executeQuery();
			while(rs.next()){
				bVO = new Buy_RecordVO();
				bVO.setBR_ID(rs.getString("BR_ID"));
				bVO.setMEM_NO(rs.getString("MEM_NO"));
				bVO.setBR_DATE(rs.getTimestamp("BR_DATE"));
				bVO.setBR_CASH(rs.getInt("BR_CASH"));
				
				list.add(bVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt !=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
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
