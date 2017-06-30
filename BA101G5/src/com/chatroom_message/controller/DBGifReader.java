package com.chatroom_message.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.sql.DataSource;


public class DBGifReader extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
String cr_msg_no=req.getParameter("cr_msg_no");
//String cr_msg_no=new String(cr_msg_no.getBytes("ISO-8859-1"),"UTF-8");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
				"SELECT message_img FROM chatroom_message WHERE cr_msg_no ='"+cr_msg_no+"'");

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(1));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in=getServletContext().getResourceAsStream("/images/back1.gif");
				byte[] buf=new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in=getServletContext().getResourceAsStream("/images/back1.gif");
			byte[] buf=new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA101_5");
			con = ds.getConnection();
		} catch (Exception e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
