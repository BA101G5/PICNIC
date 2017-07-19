package com.chatroom.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChatroomJDBCDAO implements ChatroomDAO_interface{

	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";
	
	    private static final String INSERT_STMT = 
			"INSERT INTO CHATROOM (CHATROOM_NO,CHATROOM_NAME,CHATROOM_KIND) VALUES (CHATROOM_NO_SQ.NEXTVAL, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM CHATROOM order by CHATROOM_NO";
		private static final String GET_ONE_STMT = 
			"SELECT CHATROOM_NO,CHATROOM_NAME,CHATROOM_KIND FROM CHATROOM where CHATROOM_NO = ?";
		private static final String DELETE = 
			"DELETE FROM CHATROOM where CHATROOM_NO = ?";  // 聊天訊息好像不用刪?
		private static final String UPDATE =                 // 聊天訊息好像不用更新?
			"UPDATE CHATROOM set CHATROOM_NAME=? where CHATROOM_NO = ?";
	
	
		
	@Override
	public void insert(ChatroomVO chatroomVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, chatroomVO.getChatroom_name());
			pstmt.setInt(2, chatroomVO.getChatroom_kind());	
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
	public void update(ChatroomVO chatroomVO) {
		// TODO Auto-generated method stub
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, chatroomVO.getChatroom_name());
			pstmt.setString(2, chatroomVO.getChatroom_no());			
			pstmt.executeUpdate();

			// Handle any SQL errors
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(String chatroom_no) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chatroom_no);

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
	public ChatroomVO findByPrimaryKey(String chatroom_no) {
		// TODO Auto-generated method stub
		ChatroomVO chatroomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chatroom_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				chatroomVO = new ChatroomVO();
				chatroomVO.setChatroom_no(rs.getString("chatroom_no"));
				chatroomVO.setChatroom_name(rs.getString("chatroom_name"));
				chatroomVO.setChatroom_kind(rs.getInt("chatroom_kind"));	
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
		return chatroomVO;
	}

	@Override
	public List<ChatroomVO> getAll() {
		// TODO Auto-generated method stub
		List<ChatroomVO> list = new ArrayList<ChatroomVO>();
		ChatroomVO chatroomVO = null;
		
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
				chatroomVO = new ChatroomVO();
				chatroomVO.setChatroom_no(rs.getString("chatroom_no"));
				chatroomVO.setChatroom_name(rs.getString("chatroom_name"));
				chatroomVO.setChatroom_kind(rs.getInt("chatroom_kind"));
				list.add(chatroomVO); // Store the row in the list
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

		ChatroomJDBCDAO dao = new ChatroomJDBCDAO();	
		
		// 新增
		ChatroomVO chatroomVO1 = new ChatroomVO();
		chatroomVO1.setChatroom_no("PG00000002");
		chatroomVO1.setChatroom_name("MG00000002");
		chatroomVO1.setChatroom_kind(1);
		dao.insert(chatroomVO1);

		// 修改  
		ChatroomVO chatroomVO2 = new ChatroomVO();
		chatroomVO2.setChatroom_no("CR00000003");
		chatroomVO2.setChatroom_name("回來找我");
		chatroomVO2.setChatroom_kind(1);
		dao.update(chatroomVO2);

//		// 刪除
		dao.delete("11");

		// 查詢
		ChatroomVO chatroomVO3 = dao.findByPrimaryKey("CR00000004");
		System.out.print(chatroomVO3.getChatroom_no() + ",");
		System.out.print(chatroomVO3.getChatroom_name() + ",");
		System.out.print(chatroomVO3.getChatroom_kind());
		System.out.println("---------------------");

     	// 查詢
		List<ChatroomVO> list = dao.getAll();
		for (ChatroomVO chatroomVO : list) {
			System.out.print(chatroomVO.getChatroom_no() + ",");
			System.out.print(chatroomVO.getChatroom_name() + ",");
			System.out.print(chatroomVO.getChatroom_kind());
			System.out.println();
		}
	}		

}
