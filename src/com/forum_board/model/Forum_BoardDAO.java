package com.forum_board.model;

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

public class Forum_BoardDAO implements Forum_BoardDAO_interface {

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
		"INSERT INTO FORUM_BOARD (FORUM_NO, FORUM_NAME, FORUM_DESC, FORUM_STA) VALUES ('BF' || LPAD(FORUM_NO_SQ.NEXTVAL, 8, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT FORUM_NO, FORUM_NAME, FORUM_DESC, FORUM_STA FROM FORUM_BOARD order by FORUM_NO";
	private static final String GET_ONE_STMT = 
		"SELECT FORUM_NO, FORUM_NAME, FORUM_DESC, FORUM_STA FROM FORUM_BOARD where FORUM_NO = ?";
	private static final String DELETE = 
		"DELETE FROM FORUM_BOARD where FORUM_NO = ?";
	private static final String UPDATE = 
		"UPDATE FORUM_BOARD set FORUM_NAME=?, FORUM_DESC=?, FORUM_STA=? where FORUM_NO = ?";

	@Override
	public void insert(Forum_BoardVO forumBoardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, forumBoardVO.getForum_name());
			pstmt.setString(2, forumBoardVO.getForum_desc());
			pstmt.setString(3, forumBoardVO.getForum_sta());

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
	public void update(Forum_BoardVO forumBoardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumBoardVO.getForum_name());
			pstmt.setString(2, forumBoardVO.getForum_desc());
			pstmt.setString(3, forumBoardVO.getForum_sta());
			pstmt.setString(4, forumBoardVO.getForum_no());

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
	public void delete(String keyword_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, keyword_no);

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
	public Forum_BoardVO findByPrimaryKey(String keyword_no) {

		Forum_BoardVO forumBoardVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, keyword_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// intervlLetterVO 也稱為 Domain objects
				forumBoardVO = new Forum_BoardVO();
				forumBoardVO.setForum_no(rs.getString("FORUM_NO"));
				forumBoardVO.setForum_name(rs.getString("FORUM_NAME"));
				forumBoardVO.setForum_desc(rs.getString("FORUM_DESC"));
				forumBoardVO.setForum_sta(rs.getString("FORUM_STA"));
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
		return forumBoardVO;
	}

	@Override
	public List<Forum_BoardVO> getAll() {
		List<Forum_BoardVO> list = new ArrayList<Forum_BoardVO>();
		Forum_BoardVO forumBoardVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// forumBoardVO 也稱為 Domain objects
				forumBoardVO = new Forum_BoardVO();
				forumBoardVO.setForum_no(rs.getString("FORUM_NO"));
				forumBoardVO.setForum_name(rs.getString("FORUM_NAME"));
				forumBoardVO.setForum_desc(rs.getString("FORUM_DESC"));
				forumBoardVO.setForum_sta(rs.getString("FORUM_STA"));
				list.add(forumBoardVO); // Store the row in the list
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
