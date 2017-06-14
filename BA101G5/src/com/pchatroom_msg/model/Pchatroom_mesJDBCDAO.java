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


public class Pchatroom_mesJDBCDAO implements Pchatroom_mesDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

	
	    private static final String INSERT_STMT = 
			"INSERT INTO PCHATROOM_MES (CRMSG_NO,PICNIC_NO,MEM_NO,MESSAGE_DATE,MESSAGE_TEXT,MESSAGE_IMG) VALUES (CRMSG_NO_SQ.NEXTVAL, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM PCHATROOM_MES order by CRMSG_NO";
		private static final String GET_ONE_STMT = 
			"SELECT CRMSG_NO,PICNIC_NO,MEM_NO,to_char(MESSAGE_DATE,'yyyy-mm-dd'),MESSAGE_TEXT,MESSAGE_IMG FROM PCHATROOM_MES where PICNIC_NO = ?";
		private static final String DELETE = 
			"DELETE FROM PCHATROOM_MES where CRMSG_NO = ?";  // 聊天訊息好像不用刪?
		private static final String UPDATE =                 // 聊天訊息好像不用更新?
			"UPDATE PCHATROOM_MES set PICNIC_NO=?, MEM_NO=?, MESSAGE_DATE=?, MESSAGE_TEXT=?, MESSAGE_IMG=? where CRMSG_NO = ?";
	
	
	
	@Override
	public void insert(Pchatroom_mesVO pchatroom_MesVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, pchatroom_MesVO.getPicnic_no());
			pstmt.setString(2, pchatroom_MesVO.getMem_no());
			pstmt.setTimestamp(3,pchatroom_MesVO.getMessage_date());
			pstmt.setString(4, pchatroom_MesVO.getMessage_text());
			pstmt.setBytes(5, pchatroom_MesVO.getMessage_img());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, pchatroom_MesVO.getPicnic_no());
			pstmt.setString(2, pchatroom_MesVO.getMem_no());
			pstmt.setTimestamp(3, pchatroom_MesVO.getMessage_date());
			pstmt.setString(4, pchatroom_MesVO.getMessage_text());
			pstmt.setBytes(5, pchatroom_MesVO.getMessage_img()); 
			pstmt.setString(6, pchatroom_MesVO.getCrmsg_no());
			pstmt.executeUpdate();

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, crmsg_no);

			pstmt.executeUpdate();

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		
	public static void main(String[] args) {

		Pchatroom_mesJDBCDAO dao = new Pchatroom_mesJDBCDAO();
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");		

		
		
		// 新增
		Pchatroom_mesVO pchatroom_MesVO1 = new Pchatroom_mesVO();
		pchatroom_MesVO1.setPicnic_no("PG00000002");
		pchatroom_MesVO1.setMem_no("MG00000002");
		pchatroom_MesVO1.setMessage_date(java.sql.Timestamp.valueOf(ft.format(dNow)));
		pchatroom_MesVO1.setMessage_text("546546");
		try {
			pchatroom_MesVO1.setMessage_img(getPictureByteArray("WebContent/ABC.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(pchatroom_MesVO1);

		// 修改  
		Pchatroom_mesVO pchatroom_mesVO2 = new Pchatroom_mesVO();
		pchatroom_mesVO2.setCrmsg_no("6");
		pchatroom_mesVO2.setPicnic_no("PG00000008");
		pchatroom_mesVO2.setMem_no("MG00000003");
		pchatroom_mesVO2.setMessage_date(java.sql.Timestamp.valueOf(ft.format(dNow)));
		pchatroom_mesVO2.setMessage_text("他吃飯吃很快111");
		pchatroom_mesVO2.setMessage_img(null);
		dao.update(pchatroom_mesVO2);

		// 刪除  野餐團的聊天室訊息記錄 好像不用刪除
		dao.delete("7");

		// 查詢
		Pchatroom_mesVO pchatroom_MesVO3 = dao.findByPrimaryKey("PG00000001");
		System.out.print(pchatroom_MesVO3.getCrmsg_no() + ",");
		System.out.print(pchatroom_MesVO3.getPicnic_no() + ",");
		System.out.print(pchatroom_MesVO3.getMem_no() + ",");
		System.out.print(pchatroom_MesVO3.getMessage_date() + ",");
		System.out.print(pchatroom_MesVO3.getMessage_text() + ",");
		System.out.println(pchatroom_MesVO3.getMessage_img());
		System.out.println("---------------------");

     	// 查詢
		List<Pchatroom_mesVO> list = dao.getAll();
		for (Pchatroom_mesVO pchatroom_MesVO : list) {
			System.out.print(pchatroom_MesVO.getCrmsg_no() + ",");
			System.out.print(pchatroom_MesVO.getPicnic_no() + ",");
			System.out.print(pchatroom_MesVO.getMem_no() + ",");
			System.out.print(pchatroom_MesVO.getMessage_date() + ",");
			System.out.print(pchatroom_MesVO.getMessage_text() + ",");
			System.out.print(pchatroom_MesVO.getMessage_img());
			System.out.println();
		}
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
