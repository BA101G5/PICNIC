package com.login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LogInDAO implements LogIn_interface{

	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		}catch (NamingException e){
			e.printStackTrace();
		}
	}
	private static final String SELECT_ALL = "SELECT * FROM ADMINISTRATOR WHERE adm_acc=?";
	@Override
	public LogInVO findByACC(String adm_acc) {
		LogInVO loginVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
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
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

}
