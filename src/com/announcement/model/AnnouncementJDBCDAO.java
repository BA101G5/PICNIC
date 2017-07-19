package com.announcement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AnnouncementJDBCDAO implements AnnouncementDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";

	private static final String INSERT_STMT = 
		"INSERT INTO ANNOUNCEMENT (ANN_NO, ANN_TEXT) VALUES ('AN' || LPAD(ANN_NO_SQ.NEXTVAL, 8, '0'), ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ANN_NO, ANN_TEXT FROM ANNOUNCEMENT order by ANN_NO";
	private static final String GET_ONE_STMT = 
		"SELECT ANN_NO, ANN_TEXT FROM ANNOUNCEMENT where ANN_NO = ?";
	private static final String DELETE = 
		"DELETE FROM ANNOUNCEMENT where ANN_NO = ?";
	private static final String UPDATE = 
		"UPDATE ANNOUNCEMENT set ANN_TEXT=? where ANN_NO = ?";

	@Override
	public void insert(AnnouncementVO announcementVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, announcementVO.getAnn_text());

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
	public void update(AnnouncementVO announcementVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, announcementVO.getAnn_text());
			pstmt.setString(2, announcementVO.getAnn_no());

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
	public void delete(String letter_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, letter_no);

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
	public AnnouncementVO findByPrimaryKey(String letter_no) {

		AnnouncementVO announcementVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, letter_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// intervlLetterVO 嘿 Domain objects
				announcementVO = new AnnouncementVO();
				announcementVO.setAnn_no(rs.getString("ANN_NO"));
				announcementVO.setAnn_text(rs.getString("ANN_TEXT"));
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
		return announcementVO;
	}

	@Override
	public List<AnnouncementVO> getAll() {
		List<AnnouncementVO> list = new ArrayList<AnnouncementVO>();
		AnnouncementVO announcementVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// announcementVO 嘿 Domain objects
				announcementVO = new AnnouncementVO();
				announcementVO.setAnn_no(rs.getString("ANN_NO"));
				announcementVO.setAnn_text(rs.getString("ANN_TEXT"));
				list.add(announcementVO); // Store the row in the list
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

		AnnouncementJDBCDAO dao = new AnnouncementJDBCDAO();

//		// 穝糤
		AnnouncementVO announcementVO1 = new AnnouncementVO();
		announcementVO1.setAnn_text("程穝: 舧厨把 ");
		dao.insert(announcementVO1);
		
//		// э
//		AnnouncementVO announcementVO2 = new AnnouncementVO();
//		announcementVO2.setAnn_no("AN00000001");
//		announcementVO2.setAnn_text("**э**--程穝: そずゅ");
//		dao.update(announcementVO2);
		
//		// 埃
//		dao.delete("AN00000003");
		
		// 琩高 虫掸.
		AnnouncementVO announcementVO3 = dao.findByPrimaryKey("AN00000001");
		System.out.print(announcementVO3.getAnn_no() + ",");
		System.out.print(announcementVO3.getAnn_text() + ",");
		System.out.println("---------------------");
		
		// 琩高 场
		List<AnnouncementVO> list = dao.getAll();
		for (AnnouncementVO aEmp : list) {
			System.out.print(aEmp.getAnn_no() + ",");
			System.out.print(aEmp.getAnn_text() + ",");
			System.out.println();
		}
	
	}
}