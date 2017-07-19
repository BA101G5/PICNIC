package com.chatroom_members.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Chatroom_MembersDAO implements Chatroom_MembersDAO_interface {
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
		"INSERT INTO chatroom_members (chatroom_no,mem_no,chatroom_role,ban_until) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT chatroom_no,mem_no,chatroom_role,ban_until FROM chatroom_members order by chatroom_no";
	private static final String GET_ONE_STMT = 
		"SELECT chatroom_no,mem_no,chatroom_role,ban_until FROM chatroom_members where chatroom_no= ? and mem_no=?";
	private static final String DELETE = 
		"DELETE FROM chatroom_members where chatroom_no=? and mem_no=?";
	private static final String UPDATE = 
		"UPDATE chatroom_members set chatroom_role=?, ban_until=? where chatroom_no=? and mem_no = ?";
	private static final String GET_ALL_STMT_COND = 
	"SELECT CHATROOM_NO, MEM_NO, CHATROOM_KIND FROM CHATROOM_MEMBERS natural join CHATROOM WHERE nvl(CHATROOM.CHATROOM_KIND, 0) = 0";
	private static final String GET_ONE_STMT_COND = 
	"SELECT CHATROOM_NO, MEM_NO, CHATROOM_KIND FROM CHATROOM_MEMBERS natural join CHATROOM WHERE nvl(CHATROOM.CHATROOM_KIND, 0) = 0 AND MEM_NO = ? AND CHATROOM_NO in ( SELECT CHATROOM_NO FROM CHATROOM_MEMBERS natural join CHATROOM WHERE nvl(CHATROOM.CHATROOM_KIND, 0) = 0 AND MEM_NO = ? )";

	@Override
	public void insert(Chatroom_MembersVO chatroom_MembersVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, chatroom_MembersVO.getChatroom_no());
			pstmt.setString(2, chatroom_MembersVO.getMem_no());
			pstmt.setString(3, chatroom_MembersVO.getChatroom_role().toString());
			pstmt.setTimestamp(4, chatroom_MembersVO.getBan_until());
			
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
	public void update(Chatroom_MembersVO chatroom_MembersVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, chatroom_MembersVO.getChatroom_role().toString());
			pstmt.setTimestamp(2, chatroom_MembersVO.getBan_until());
			pstmt.setString(3, chatroom_MembersVO.getChatroom_no());
			pstmt.setString(4, chatroom_MembersVO.getMem_no());

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
	public void delete(String chatroom_no, String mem_no) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chatroom_no);
			pstmt.setString(2, mem_no);
			
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
	public List<Chatroom_MembersVO> findByPrimaryKey(String chatroom_no, String mem_no) {
		// TODO Auto-generated method stub
		
		List<Chatroom_MembersVO> list = new ArrayList<Chatroom_MembersVO>();

		Chatroom_MembersVO chatroom_MembersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, chatroom_no);
			pstmt.setString(2, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]붙О Domain objects
				chatroom_MembersVO = new Chatroom_MembersVO();
				chatroom_MembersVO.setChatroom_no(rs.getString("chatroom_no"));
				chatroom_MembersVO.setMem_no(rs.getString("mem_no"));
				chatroom_MembersVO.setChatroom_role(rs.getString("Chatroom_role").charAt(0));
				chatroom_MembersVO.setBan_until(rs.getTimestamp("ban_until"));
				list.add(chatroom_MembersVO);
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

	@Override
	public List<Chatroom_MembersVO> getAll() {
		// TODO Auto-generated method stub
		List<Chatroom_MembersVO> list = new ArrayList<Chatroom_MembersVO>();
		Chatroom_MembersVO chatroom_MembersVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]붙О Domain objects
				chatroom_MembersVO = new Chatroom_MembersVO();
				chatroom_MembersVO.setChatroom_no(rs.getString("chatroom_no"));
				chatroom_MembersVO.setMem_no(rs.getString("mem_no"));
				chatroom_MembersVO.setChatroom_role(rs.getString("chatroom_role").charAt(0));
				chatroom_MembersVO.setBan_until(rs.getTimestamp("ban_until"));
				list.add(chatroom_MembersVO); // Store the row in the list
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

	public List<Chatroom_MembersVO2> getAllwCond() {
		// TODO Auto-generated method stub
		List<Chatroom_MembersVO2> list = new ArrayList<Chatroom_MembersVO2>();
		Chatroom_MembersVO2 chatroom_MembersVO2 = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_COND);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// empVO �]붙О Domain objects
				chatroom_MembersVO2 = new Chatroom_MembersVO2();
				chatroom_MembersVO2.setChatroom_no(rs.getString("chatroom_no"));
				chatroom_MembersVO2.setMem_no(rs.getString("mem_no"));
				chatroom_MembersVO2.setChatroom_kind(rs.getInt("chatroom_kind"));
				list.add(chatroom_MembersVO2); // Store the row in the list
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

	@Override
	public Chatroom_MembersVO2 getOnewCond(String mem_no, String mem_no2) {
	
		Chatroom_MembersVO2 cmVO2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_COND);
			rs = pstmt.executeQuery();
	
			pstmt.setString(1, mem_no);
			pstmt.setString(2, mem_no2);
	
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// Chatroom_MembersVO2 �]붙О Domain objects
				cmVO2 = new Chatroom_MembersVO2();
				cmVO2.setChatroom_no(rs.getString("chatroom_no"));
				cmVO2.setMem_no(rs.getString("mem_no"));
				cmVO2.setChatroom_kind(rs.getInt("chatroom_kind"));
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
		return cmVO2;
	}

}
