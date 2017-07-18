package com.buy_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Buy_RecordJDBCDAO implements Buy_RecordDAO_interface {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "BA101G5";
	private static final String PASSWORD = "BA101G5";
	private static final String INSERT = "INSERT INTO BUY_RECORD(BR_ID,MEM_NO,BR_DATE,BR_CASH)"
			+ "VALUES('BR' || LPAD(BR_NO_SQ.NEXTVAL, 8, '0'),?,?,?)";
	private static final String UPDATE = "UPDATE BUY_RECORD SET  MEM_NO=?,BR_DATE=?,BR_CASH=? WHERE BR_ID=?";
	private static final String DELETE = "DELETE FROM BUY_RECORD WHERE BR_ID=?";
	private static final String FINDBYKEY = "SELECT MEM_NO,BR_DATE,BR_CASH FROM BUY_RECORD WHERE BR_ID=?";
	private static final String FINDALL = "SELECT * FROM BUY_RECORD";
	private static final String FINDMG ="SELECT * FROM BUY_RECORD WHERE MEM_NO=?";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Buy_RecordVO Buy_recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, Buy_recordVO.getMEM_NO());
			pstmt.setTimestamp(2, Buy_recordVO.getBR_DATE());
			pstmt.setInt(3, Buy_recordVO.getBR_CASH());

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
	public void update(Buy_RecordVO Buy_recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Buy_recordVO.getMEM_NO());
			pstmt.setTimestamp(2, Buy_recordVO.getBR_DATE());
			pstmt.setInt(3, Buy_recordVO.getBR_CASH());
			pstmt.setString(4, Buy_recordVO.getBR_ID());

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
	public void delete(String BR_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, BR_NO);

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
	public Buy_RecordVO findByPrimaryKey(String BR_NO) {
		Buy_RecordVO bVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(FINDBYKEY);
			pstmt.setString(1, BR_NO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bVO = new Buy_RecordVO();
				bVO.setMEM_NO(rs.getString("MEM_NO"));
				bVO.setBR_DATE(rs.getTimestamp("BR_DATE"));
				bVO.setBR_CASH(rs.getInt("BR_CASH"));
				

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
		return bVO;
	}

	

	@Override
	public List<Buy_RecordVO> getAll() {
		List<Buy_RecordVO> list = new ArrayList<Buy_RecordVO>();
		Buy_RecordVO bVO = null;
		Connection con =null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			con =DriverManager.getConnection(URL,USERNAME,PASSWORD);
			pstmt = con.prepareStatement(FINDALL);
			rs =pstmt.executeQuery();
			while(rs.next()){
				bVO = new Buy_RecordVO();
				bVO.setMEM_NO(rs.getString("MEM_NO"));
				bVO.setBR_DATE(rs.getTimestamp("BR_DATE"));
				bVO.setBR_CASH(rs.getInt("BR_CASH"));
				
				list.add(bVO);
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
		Buy_RecordJDBCDAO dao = new Buy_RecordJDBCDAO();
		// 1.insert
		// try {
		// Buy_recordVO bVO =new Buy_recordVO();
		// bVO.setMEM_NO("MG00000001");
		// bVO.setBR_DATE(java.sql.Date.valueOf("2002-01-01"));
		// bVO.setBR_CASH(500);
		// dao.insert(bVO);
		// System.out.println("Insert Success!!");
		// } catch (Exception se) {
		// System.out.println("Insert Fail!!");

		// }
		// 2.update

		// Buy_recordVO bVO =new Buy_recordVO();
		// bVO.setMEM_NO("MG00000001");
		// bVO.setBR_DATE(java.sql.Date.valueOf("2012-01-01"));
		// bVO.setBR_CASH(5000);
		// bVO.setBR_ID("BR00000001");
		// dao.update(bVO);
		// System.out.println("update Success!!");

		// 3.delete

		// dao.delete("BR00000002");
		// System.out.println("Delete Success!!");

		// 4.FIND BY KEY

//		 Buy_recordVO bVO =dao.findByPrimaryKey("BR00000001");
//		 System.out.println("MEM_NO : " + bVO.getMEM_NO());
//		 System.out.println("BR_DATE : " + bVO.getBR_DATE());
//		 System.out.println("BR_CASH : " + bVO.getBR_CASH());
		 

		//5. FIND ALL
//				List<Buy_RecordVO> listgVO =dao.getAll();
//				for(Buy_RecordVO gVO :listgVO ){
//					System.out.println("MEM_NO : " + gVO.getMEM_NO());
//					System.out.println("BR_DATE : " + gVO.getBR_DATE());
//					System.out.println("BR_CASH : " + gVO.getBR_CASH());
//					
//					System.out.println("-----------------");
//				}
		//6. FIND BY MEMNO
		List<Buy_RecordVO> listgVO =dao.findByMG("MG00000001");
		for(Buy_RecordVO gVO :listgVO ){
			System.out.println("BR_ID : " + gVO.getBR_ID());
			System.out.println("MEM_NO : " + gVO.getMEM_NO());
			
			System.out.println("BR_DATE : " + gVO.getBR_DATE());
			System.out.println("BR_CASH : " + gVO.getBR_CASH());
			
			System.out.println("-----------------");
		}
	}

	@Override
	public List<Buy_RecordVO> findByMG(String MEM_NO) {
		
		List<Buy_RecordVO> list = new ArrayList<Buy_RecordVO>();
		Buy_RecordVO bVO = null;
		Connection con =null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		try {
			con =DriverManager.getConnection(URL,USERNAME,PASSWORD);
			pstmt = con.prepareStatement(FINDMG);
			pstmt.setString(1, MEM_NO);
			rs =pstmt.executeQuery();
			while(rs.next()){
				bVO = new Buy_RecordVO();
				bVO.setBR_ID(rs.getString("BR_ID"));
				bVO.setMEM_NO(rs.getString("MEM_NO"));
				bVO.setBR_DATE(rs.getTimestamp("BR_DATE"));
				bVO.setBR_CASH(rs.getInt("BR_CASH"));
				
				list.add(bVO);
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

}
