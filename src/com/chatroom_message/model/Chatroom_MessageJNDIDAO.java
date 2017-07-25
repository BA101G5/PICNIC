package com.chatroom_message.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class Chatroom_MessageJNDIDAO implements Chatroom_MessageDAO_interface {
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
		"INSERT INTO chatroom_message (cr_msg_no,chatroom_no,mem_no,message_date,message_text,message_img) VALUES ('CM'||lpad(chatroom_message_sq.nextval,8,'0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT cr_msg_no,chatroom_no,mem_no,message_date,message_text,message_img FROM chatroom_message order by cr_msg_no";
	private static final String GET_ONE_STMT = 
		"SELECT cr_msg_no,chatroom_no,mem_no,message_date,message_text,message_img FROM chatroom_message where cr_msg_no = ?";
	private static final String DELETE = 
		"DELETE FROM chatroom_message where cr_msg_no = ?";
	private static final String UPDATE = 
		"UPDATE chatroom_message set chatroom_no=?, mem_no=?, message_date=?, message_text=?, message_img=? where cr_msg_no = ?";


@Override
public void insert(Chatroom_MessageVO chatroom_MessageVO) {
	// TODO Auto-generated method stub

	Connection con = null;
	PreparedStatement pstmt = null;

	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setString(1, chatroom_MessageVO.getChatroom_no());
		pstmt.setString(2, chatroom_MessageVO.getMem_no());
		pstmt.setTimestamp(3, chatroom_MessageVO.getMessage_date());
		pstmt.setString(4, chatroom_MessageVO.getMessage_text());
		pstmt.setBytes(5, chatroom_MessageVO.getMessage_img());
		
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
public void update(Chatroom_MessageVO chatroom_MessageVO) {
	// TODO Auto-generated method stub

	Connection con = null;
	PreparedStatement pstmt = null;

	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE);

		pstmt.setString(1, chatroom_MessageVO.getChatroom_no());
		pstmt.setString(2, chatroom_MessageVO.getMem_no());
		pstmt.setTimestamp(3, chatroom_MessageVO.getMessage_date());
		pstmt.setString(4, chatroom_MessageVO.getMessage_text());
		pstmt.setBytes(5, chatroom_MessageVO.getMessage_img());
		pstmt.setString(6, chatroom_MessageVO.getCr_msg_no());

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
public void delete(String cr_msg_no) {
	// TODO Auto-generated method stub

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, cr_msg_no);

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
public Chatroom_MessageVO findByPrimaryKey(String cr_msg_no) {
	// TODO Auto-generated method stub

	Chatroom_MessageVO chatroom_MessageVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setString(1, cr_msg_no);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// chatroom_MessageVO 也稱為 Domain objects
			chatroom_MessageVO = new Chatroom_MessageVO();
			chatroom_MessageVO.setCr_msg_no(rs.getString("cr_msg_no"));
			chatroom_MessageVO.setChatroom_no(rs.getString("chatroom_no"));
			chatroom_MessageVO.setMem_no(rs.getString("mem_no"));
			chatroom_MessageVO.setMessage_date(rs.getTimestamp("message_date"));
			chatroom_MessageVO.setMessage_text(rs.getString("message_text"));
			chatroom_MessageVO.setMessage_img(rs.getBytes("message_img"));
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
	return chatroom_MessageVO;
}

@Override
public List<Chatroom_MessageVO> getAll() {
	// TODO Auto-generated method stub
	List<Chatroom_MessageVO> list = new ArrayList<Chatroom_MessageVO>();
	Chatroom_MessageVO chatroom_MessageVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// Chatroom_MessageVO 也稱為 Domain objects
			chatroom_MessageVO = new Chatroom_MessageVO();
			chatroom_MessageVO.setCr_msg_no(rs.getString("cr_msg_no"));
			chatroom_MessageVO.setChatroom_no(rs.getString("chatroom_no"));
			chatroom_MessageVO.setMem_no(rs.getString("mem_no"));
			chatroom_MessageVO.setMessage_date(rs.getTimestamp("message_date"));
			chatroom_MessageVO.setMessage_text(rs.getString("message_text"));
			chatroom_MessageVO.setMessage_img(rs.getBytes("message_img"));
			list.add(chatroom_MessageVO); // Store the row in the list
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

public static void main(String[] args) {

	Chatroom_MessageJDBCDAO dao = new Chatroom_MessageJDBCDAO();

	// 新增
//	Chatroom_MessageVO chatroom_MessageVO1 = new Chatroom_MessageVO();
//	chatroom_MessageVO1.setChatroom_no("CR00000001");
//	chatroom_MessageVO1.setMem_no("MG00000004");
//	chatroom_MessageVO1.setMessage_date(new java.sql.Timestamp(System.currentTimeMillis()));
//	chatroom_MessageVO1.setMessage_text("asnsvdsdvsdvfdgsewwej");
//	try {
//		chatroom_MessageVO1.setMessage_img(getPictureByteArray("WebContent/w66.jpg"));
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	dao.insert(chatroom_MessageVO1);

	// 修改
//	Chatroom_MessageVO chatroom_MessageVO2 = new Chatroom_MessageVO();
//	chatroom_MessageVO2.setCr_msg_no("CM00000010");
//	chatroom_MessageVO2.setChatroom_no("CR00000002");
//	chatroom_MessageVO2.setMem_no("MG00000009");
//	chatroom_MessageVO2.setMessage_date(java.sql.Timestamp.valueOf("2002-01-01 22:33:11"));
//	chatroom_MessageVO2.setMessage_text("15brer5baergerbnretndrtynjrthrthrt");
//	try {
//		chatroom_MessageVO2.setMessage_img(getPictureByteArray("WebContent/w33.jpg"));
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	dao.update(chatroom_MessageVO2);

	// 刪除
//	dao.delete("CM00000006");

	// 查詢
//	Chatroom_MessageVO chatroom_MessageVO3 = dao.findByPrimaryKey("CM00000010");
//	System.out.print(chatroom_MessageVO3.getCr_msg_no() + ",");
//	System.out.print(chatroom_MessageVO3.getChatroom_no() + ",");
//	System.out.print(chatroom_MessageVO3.getMem_no() + ",");
//	System.out.print(chatroom_MessageVO3.getMessage_date() + ",");
//	System.out.print(chatroom_MessageVO3.getMessage_text() + ",");
//	System.out.print(chatroom_MessageVO3.getMessage_img()+"\n");
//	System.out.println("---------------------");

	// 查詢
//	List<Chatroom_MessageVO> list = dao.getAll();
//	for (Chatroom_MessageVO aEmp : list) {
//		System.out.print(aEmp.getCr_msg_no() + ",");
//		System.out.print(aEmp.getChatroom_no() + ",");
//		System.out.print(aEmp.getMem_no() + ",");
//		System.out.print(aEmp.getMessage_date() + ",");
//		System.out.print(aEmp.getMessage_text() + ",");
//		System.out.print(aEmp.getMessage_img());
//		System.out.println();
//	}
}
public static byte[] getPictureByteArray(String path) throws IOException {
	File file = new File(path);
	FileInputStream fis = new FileInputStream(file);
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	byte[] buffer = new byte[8192];
	int i;
	while ((i = fis.read(buffer)) != -1) {
		baos.write(buffer, 0, i);
	}
	baos.close();
	fis.close();

	return baos.toByteArray();
	}
}
