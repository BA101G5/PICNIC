package com.forum_board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Forum_BoardJDBCDAO implements Forum_BoardDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, forumBoardVO.getForum_name());
			pstmt.setString(2, forumBoardVO.getForum_desc());
			pstmt.setString(3, forumBoardVO.getForum_sta());

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
	public void update(Forum_BoardVO forumBoardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, forumBoardVO.getForum_name());
			pstmt.setString(2, forumBoardVO.getForum_desc());
			pstmt.setString(3, forumBoardVO.getForum_sta());
			pstmt.setString(4, forumBoardVO.getForum_no());

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
	public void delete(String keyword_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, keyword_no);

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
	public Forum_BoardVO findByPrimaryKey(String keyword_no) {

		Forum_BoardVO forumBoardVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	
	public static void main(String[] args) {

		Forum_BoardJDBCDAO dao = new Forum_BoardJDBCDAO();

//		// 新增
		Forum_BoardVO forumBoardVO1 = new Forum_BoardVO();
		forumBoardVO1.setForum_name("新的討論區");
		forumBoardVO1.setForum_desc("新的討論區描述");
		forumBoardVO1.setForum_sta("V");
		dao.insert(forumBoardVO1);
		
//		// 修改
		Forum_BoardVO forumBoardVO2 = new Forum_BoardVO();
		forumBoardVO2.setForum_no("BF00000001");
		forumBoardVO2.setForum_name("**已修改**討論區名稱");
		forumBoardVO2.setForum_desc("**已修改**--討論區描述");
		forumBoardVO2.setForum_sta("V");
		dao.update(forumBoardVO2);
		
//		// 刪除
//		dao.delete("BF00000002");
		
		// 查詢 單筆.
		Forum_BoardVO forumBoardVO3 = dao.findByPrimaryKey("BF00000001");
		System.out.print(forumBoardVO3.getForum_no() + ",");
		System.out.print(forumBoardVO3.getForum_name() + ",");
		System.out.print(forumBoardVO3.getForum_desc() + ",");
		System.out.println("---------------------");
		
		// 查詢 全部
		List<Forum_BoardVO> list = dao.getAll();
		for (Forum_BoardVO aEmp : list) {
			System.out.print(aEmp.getForum_no() + ",");
			System.out.print(aEmp.getForum_name() + ",");
			System.out.print(aEmp.getForum_desc() + ",");
			System.out.println();
		}
	
	}

}
