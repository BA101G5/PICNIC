package com.general_member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralMemberJDBCDAO implements GeneralMemberDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "BA101G5";
	private static final String PASSWORD = "BA101G5";
	private static final String INSERT = "INSERT INTO GENERAL_MEMBER(MEM_NO, MEM_NAME, MEM_GEN, MEM_BIRTH, MEM_ADDR, MEM_MAIL, MEM_PSW, MEM_COIN, MEM_STA,MEM_PHONE,MEM_PBOARD)"
			+ "VALUES('MG' || LPAD(MEM_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE GENERAL_MEMBER SET MEM_NAME=?,MEM_GEN=?,MEM_BIRTH=?, MEM_ADDR=?, MEM_MAIL=? , MEM_PSW=?, MEM_COIN=?, MEM_STA=? ,MEM_PHONE=?,MEM_PBOARD=? WHERE MEM_NO=?";
	private static final String DELETE = "DELETE FROM GENERAL_MEMBER WHERE MEM_NO=?";
	private static final String FINDBYKEY = "SELECT MEM_NO,MEM_NAME, MEM_GEN, MEM_BIRTH, MEM_ADDR, MEM_MAIL, MEM_PSW, MEM_COIN, MEM_STA,MEM_PHONE,MEM_PBOARD FROM GENERAL_MEMBER WHERE MEM_NO=?";
	private static final String FINDALL ="SELECT * FROM GENERAL_MEMBER";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(GeneralMemberVO generalmemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, generalmemberVO.getMEM_NAME());
			pstmt.setString(2, String.valueOf(generalmemberVO.getMEM_GEN()));
			pstmt.setDate(3, generalmemberVO.getMEM_BIRTH());
			pstmt.setString(4, generalmemberVO.getMEM_ADDR());
			pstmt.setString(5, generalmemberVO.getMEM_MAIL());
			pstmt.setString(6, generalmemberVO.getMEM_PSW());
			pstmt.setInt(7, generalmemberVO.getMEM_COIN());
			pstmt.setString(8, String.valueOf(generalmemberVO.getMEM_STA()));
			pstmt.setString(9, generalmemberVO.getMEM_PHONE());
			pstmt.setString(10, String.valueOf(generalmemberVO.getMEM_PBOARD()));

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(GeneralMemberVO generalmemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, generalmemberVO.getMEM_NAME());
			pstmt.setString(2, String.valueOf(generalmemberVO.getMEM_GEN()));
			pstmt.setDate(3, generalmemberVO.getMEM_BIRTH());
			pstmt.setString(4, generalmemberVO.getMEM_ADDR());
			pstmt.setString(5, generalmemberVO.getMEM_MAIL());
			pstmt.setString(6, generalmemberVO.getMEM_PSW());
			pstmt.setInt(7, generalmemberVO.getMEM_COIN());
			pstmt.setString(8, String.valueOf(generalmemberVO.getMEM_STA()));
			pstmt.setString(9, generalmemberVO.getMEM_PHONE());
			pstmt.setString(10, String.valueOf(generalmemberVO.getMEM_PBOARD()));
			pstmt.setString(11, generalmemberVO.getMEM_NO());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(String MEM_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, MEM_NO);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public GeneralMemberVO findByPrimaryKey(String MEM_NO) {
		GeneralMemberVO gVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(FINDBYKEY);
			pstmt.setString(1, MEM_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gVO = new GeneralMemberVO();
				gVO.setMEM_NO(rs.getString("MEM_NO"));
				gVO.setMEM_NAME(rs.getString("MEM_NAME"));
				gVO.setMEM_GEN(rs.getString("MEM_GEN").charAt(0));
				gVO.setMEM_BIRTH(rs.getDate("MEM_BIRTH"));
				gVO.setMEM_ADDR(rs.getString("MEM_ADDR"));
				gVO.setMEM_MAIL(rs.getString("MEM_MAIL"));
				gVO.setMEM_PSW(rs.getString("MEM_PSW"));
				gVO.setMEM_COIN(rs.getInt("MEM_COIN"));
				gVO.setMEM_STA(rs.getString("MEM_STA").charAt(0));
				gVO.setMEM_PHONE(rs.getString("MEM_PHONE"));
				gVO.setMEM_PBOARD(rs.getString("MEM_PBOARD").charAt(0));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt !=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return gVO;
	}

	@Override
	public List<GeneralMemberVO> getAll() {
		List<GeneralMemberVO> list = new ArrayList<GeneralMemberVO>();
		GeneralMemberVO gVO = null;
		Connection con =null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			con =DriverManager.getConnection(URL,USERNAME,PASSWORD);
			pstmt = con.prepareStatement(FINDALL);
			rs =pstmt.executeQuery();
			while(rs.next()){
				gVO = new GeneralMemberVO();
				gVO.setMEM_NO(rs.getString("MEM_NO"));
				gVO.setMEM_NAME(rs.getString("MEM_NAME"));
				gVO.setMEM_GEN(rs.getString("MEM_GEN").charAt(0));
				gVO.setMEM_BIRTH(rs.getDate("MEM_BIRTH"));
				gVO.setMEM_ADDR(rs.getString("MEM_ADDR"));
				gVO.setMEM_MAIL(rs.getString("MEM_MAIL"));
				gVO.setMEM_PSW(rs.getString("MEM_PSW"));
				gVO.setMEM_COIN(rs.getInt("MEM_COIN"));
				gVO.setMEM_STA(rs.getString("MEM_STA").charAt(0));
				gVO.setMEM_PHONE(rs.getString("MEM_PHONE"));
				gVO.setMEM_PBOARD(rs.getString("MEM_PBOARD").charAt(0));
				list.add(gVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt !=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return list;
	}

	public static void main(String[] args) {

		GeneralMemberJDBCDAO dao = new GeneralMemberJDBCDAO();
		// 1.insert
		// try {
		// GeneralMemberVO gVO =new GeneralMemberVO();
		// gVO.setMEM_NAME("伸儒");
		// gVO.setMEM_GEN('M');
		// gVO.setMEM_BIRTH(java.sql.Date.valueOf("2002-01-01"));
		// gVO.setMEM_ADDR("臺中市西區民生路140號");
		// gVO.setMEM_MAIL("hyyhhyhyy@lirtft.com");
		// gVO.setMEM_PSW("896de0bf7b6894f5189c9c7c3f066910");
		// gVO.setMEM_COIN(2898);
		// gVO.setMEM_STA('M');
		// gVO.setMEM_PHONE("0912484797");
		// gVO.setMEM_PBOARD('Y');
		// dao.insert(gVO);
		// System.out.println("Insert Success!!");
		// } catch (Exception se) {
		// System.out.println("Insert Fail!!");
		//
		// }

		// 2.update

		// GeneralMemberVO gVO =new GeneralMemberVO();
		// gVO.setMEM_NAME("123");
		// gVO.setMEM_GEN('F');
		// gVO.setMEM_BIRTH(java.sql.Date.valueOf("2012-01-01"));
		// gVO.setMEM_ADDR("臺中市西區民生路140號");
		// gVO.setMEM_MAIL("123@lirtft.com");
		// gVO.setMEM_PSW("896de0bf7b6894f5189c9c7c3f066910");
		// gVO.setMEM_COIN(2898);
		// gVO.setMEM_STA('U');
		// gVO.setMEM_PHONE("0912484797");
		// gVO.setMEM_PBOARD('N');
		// gVO.setMEM_NO("MG00000015");
		// dao.update(gVO);
		// System.out.println("Update Success!!");

		// 3.delete
		
		// dao.delete("MG00000015");
		// System.out.println("Delete Success!!");
		
		// 4.FIND BY KEY 
		
//		GeneralMemberVO gVO =dao.findByPrimaryKey("MG00000004");
//		System.out.println("MEM_NAME : " + gVO.getMEM_NAME());
//		System.out.println("MEM_GEN : " + gVO.getMEM_GEN());
//		System.out.println("MEM_BIRTH : " + gVO.getMEM_BIRTH());
//		System.out.println("MEM_ADDR : " + gVO.getMEM_ADDR());
//		System.out.println("MEM_MAIL : " + gVO.getMEM_MAIL());
//		System.out.println("MEM_PSW : " + gVO.getMEM_PSW());
//		System.out.println("MEM_COIN : " + gVO.getMEM_COIN());
//		System.out.println("MEM_STA : " + gVO.getMEM_STA());
//		System.out.println("MEM_PHONE : " + gVO.getMEM_PHONE());
//		System.out.println("MEM_PBOARD : " + gVO.getMEM_PBOARD());
		
		//5. FIND ALL
		List<GeneralMemberVO> listgVO =dao.getAll();
		for(GeneralMemberVO gVO :listgVO ){
			System.out.println("MEM_NAME : " + gVO.getMEM_NAME());
			System.out.println("MEM_GEN : " + gVO.getMEM_GEN());
			System.out.println("MEM_BIRTH : " + gVO.getMEM_BIRTH());
			System.out.println("MEM_ADDR : " + gVO.getMEM_ADDR());
			System.out.println("MEM_MAIL : " + gVO.getMEM_MAIL());
			System.out.println("MEM_PSW : " + gVO.getMEM_PSW());
			System.out.println("MEM_COIN : " + gVO.getMEM_COIN());
			System.out.println("MEM_STA : " + gVO.getMEM_STA());
			System.out.println("MEM_PHONE : " + gVO.getMEM_PHONE());
			System.out.println("MEM_PBOARD : " + gVO.getMEM_PBOARD());
			System.out.println("-----------------");
		}
		
	}

	@Override
	public void updatefromcoin(GeneralMemberVO generalmemberVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateforSTA(GeneralMemberVO generalmemberVO) {
		// TODO Auto-generated method stub
		
	}
//----------------------------------------------------------------------------
	public void k_insert(GeneralMemberVO generalmemberVO){}
    public void k_update(GeneralMemberVO generalmemberVO){}
    public void k_delete(String MEM_NO){}
    public GeneralMemberVO k_findByPrimaryKey(String MEM_NO){return null;}
    public List<GeneralMemberVO> k_getAll(){return null;}
}
