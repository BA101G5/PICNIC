package com.chatroom.model;

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

public class ChatroomDAO implements ChatroomDAO_interface{

	
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, chatroomVO.getChatroom_name());
			pstmt.setInt(2, chatroomVO.getChatroom_kind());	
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
	public void update(ChatroomVO chatroomVO) {
		// TODO Auto-generated method stub
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chatroomVO.getChatroom_name());
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
	public void delete(String chatroom_no) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {


			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chatroom_no);

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
	public ChatroomVO findByPrimaryKey(String chatroom_no) {
		// TODO Auto-generated method stub
		ChatroomVO chatroomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = ds.getConnection();
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

			con = ds.getConnection();
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

}
