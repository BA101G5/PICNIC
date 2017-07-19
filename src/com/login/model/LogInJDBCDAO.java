package com.login.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogInJDBCDAO implements LogIn_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA101G5";
	String passwd = "BA101G5";
	
	private static final String SELECT_ALL = "SELECT * FROM ADMINISTRATOR WHERE adm_acc=?";
	
	@Override
	public LogInVO findByACC(String adm_acc) {
		LogInVO loginVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(SELECT_ALL);
			
			pstmt.setString(1, adm_acc);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				loginVO = new LogInVO();
				loginVO.setAdm_no(rs.getString("adm_no"));
				loginVO.setAdm_acc(rs.getString("adm_acc"));
				loginVO.setAdm_pw(rs.getString("adm_pw"));
				loginVO.setAdm_iden(rs.getString("adm_iden"));
				loginVO.setAdm_name(rs.getString("adm_name"));
				loginVO.setAdm_sta(rs.getString("adm_sta"));
			}
		}catch (ClassNotFoundException e) {
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
		return loginVO;
	}
	public static void main(String[] args){
		LogInJDBCDAO dao = new LogInJDBCDAO();
		
		LogInVO loginVO = dao.findByACC("1");
		
		System.out.println(loginVO.getAdm_no());
		System.out.println(loginVO.getAdm_acc());
		System.out.println(loginVO.getAdm_pw());
		System.out.println(loginVO.getAdm_iden());
		System.out.println(loginVO.getAdm_name());
		System.out.println(loginVO.getAdm_sta());
	}
}
