package com.util.encoding;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.ServletOutputStream;

public class imageoutput1 extends HttpServlet {

	Connection con;

	public void init() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
			con = ds.getConnection();
		} catch (NamingException e) {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se.getMessage());

				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		String table = req.getParameter("table");
		String table2 =new String(table.getBytes("ISO-8859-1"), "Big5");
		String picturename = req.getParameter("picturename");
		String picturename2 = new String(picturename.getBytes("ISO-8859-1"),"Big5");
		
		String images=null;
		String columl =null;
		if(table.equals("GOODS_SELL")){images="GS_IMG"; columl="GS_NO";}else if(table.equals("GOODS_RENT")){images="GR_IMG"; columl="GR_NO";}
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT "+images+" FROM " + table2 + " where "+columl+" = \'" + picturename2+"\'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("GS_IMG"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destory() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}

}
