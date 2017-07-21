package com.pchatroom_msg.model;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Pchatroom_mesDAO implements Pchatroom_mesDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	    private static final String INSERT_STMT = 
			"INSERT INTO PCHATROOM_MES (CRMSG_NO,PICNIC_NO,MEM_NO,MESSAGE_DATE,MESSAGE_TEXT,MESSAGE_IMG) VALUES (CRMSG_NO_SQ.NEXTVAL, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM PCHATROOM_MES order by CRMSG_NO";
		private static final String GET_ONE_STMT = 
			"SELECT CRMSG_NO,PICNIC_NO,MEM_NO,to_char(MESSAGE_DATE,'yyyy-mm-dd'),MESSAGE_TEXT,MESSAGE_IMG FROM PCHATROOM_MES where PICNIC_NO = ?";
		private static final String DELETE = 
			"DELETE FROM PCHATROOM_MES where CRMSG_NO = ?";  // 聊天訊息好像不用刪?
		private static final String UPDATE =                 // 聊天訊息好像不用更新?
			"UPDATE PCHATROOM_MES set CRMSG_NO=?, MEM_NO=?, MESSAGE_DATE=?, MESSAGE_TEXT=?, MESSAGE_IMG=? where PICNIC_NO = ?";
	
	
	@Override
	public void insert(Pchatroom_mesVO pchatroom_MesVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, pchatroom_MesVO.getPicnic_no());
			pstmt.setString(2, pchatroom_MesVO.getMem_no());
			pstmt.setTimestamp(3,pchatroom_MesVO.getMessage_date());
			pstmt.setString(4, pchatroom_MesVO.getMessage_text());
			pstmt.setBytes(5, pchatroom_MesVO.getMessage_img());
			
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}

	@Override
	public void update(Pchatroom_mesVO pchatroom_MesVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pchatroom_MesVO.getCrmsg_no());
			pstmt.setString(2, pchatroom_MesVO.getPicnic_no());
			pstmt.setString(3, pchatroom_MesVO.getMem_no());
			pstmt.setTimestamp(4, pchatroom_MesVO.getMessage_date());
			pstmt.setString(5, pchatroom_MesVO.getMessage_text());
			pstmt.setBytes(6, pchatroom_MesVO.getMessage_img()); 

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}		
		

	@Override
	public void delete(String crmsg_no) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, crmsg_no);

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

	@Override
	public Pchatroom_mesVO findByPrimaryKey(String picnic_no) {
		// TODO Auto-generated method stub
		Pchatroom_mesVO pchatroom_MesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, picnic_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				pchatroom_MesVO = new Pchatroom_mesVO();
				pchatroom_MesVO.setCrmsg_no(rs.getString("crmsg_no"));
				pchatroom_MesVO.setPicnic_no(rs.getString("picnic_no"));
				pchatroom_MesVO.setMem_no(rs.getString("mem_no"));
				pchatroom_MesVO.setMessage_date(rs.getTimestamp("message_img"));				
				pchatroom_MesVO.setMessage_text(rs.getString("message_text"));	
				pchatroom_MesVO.setMessage_img(rs.getBytes("message_img"));				
			}

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
		return pchatroom_MesVO;
	}
	@Override
	public List<Pchatroom_mesVO> getAll() {
		// TODO Auto-generated method stub
		List<Pchatroom_mesVO> list = new ArrayList<Pchatroom_mesVO>();
		Pchatroom_mesVO pchatroom_MesVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				pchatroom_MesVO = new Pchatroom_mesVO();
				pchatroom_MesVO.setCrmsg_no(rs.getString("crmsg_no"));
				pchatroom_MesVO.setPicnic_no(rs.getString("picnic_no"));
				pchatroom_MesVO.setMem_no(rs.getString("mem_no"));
				pchatroom_MesVO.setMessage_date(rs.getTimestamp("message_date"));
				pchatroom_MesVO.setMessage_text(rs.getString("message_text"));
				pchatroom_MesVO.setMessage_img(rs.getBytes("message_img"));
				list.add(pchatroom_MesVO); // Store the row in the list
			}			
			
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
		
	
// 加照片用的	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}
}
