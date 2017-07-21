package com.general_member.model;


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

public class GeneralMemberDAO implements GeneralMemberDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT = "INSERT INTO GENERAL_MEMBER(MEM_NO, MEM_NAME, MEM_GEN, MEM_BIRTH, MEM_ADDR, MEM_MAIL, MEM_PSW, MEM_COIN, MEM_STA,MEM_PHONE,MEM_PBOARD,MEM_PIC,MEM_SELF)"
			+ "VALUES('MG' || LPAD(MEM_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE GENERAL_MEMBER SET MEM_NAME=?,MEM_GEN=?,MEM_BIRTH=?, MEM_ADDR=?, MEM_MAIL=? , MEM_PSW=?, MEM_COIN=?, MEM_STA=? ,MEM_PHONE=?,MEM_PBOARD=?,MEM_PIC=?,MEM_SELF=? WHERE MEM_NO=?";

	private static final String DELETE = "DELETE FROM GENERAL_MEMBER WHERE MEM_NO=?";
	private static final String FINDBYKEY = "SELECT * FROM GENERAL_MEMBER WHERE MEM_NO=?";
	private static final String FINDALL = "SELECT * FROM GENERAL_MEMBER ORDER BY MEM_NO DESC";
	private static final String FINDBYACCOUNT ="SELECT * FROM GENERAL_MEMBER WHERE MEM_MAIL=?";
	private static final String UPDATECOIN="UPDATE GENERAL_MEMBER SET MEM_COIN=? WHERE MEM_NO=?";
	private static final String UPDATEFORSTA ="UPDATE GENERAL_MEMBER SET MEM_STA=? WHERE MEM_MAIL=?";
	@Override
	public void insert(GeneralMemberVO generalmemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
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
			pstmt.setBytes(11, generalmemberVO.getMEM_PIC());
			pstmt.setString(12,generalmemberVO.getMEM_SELF());
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
			con = ds.getConnection();
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
			pstmt.setBytes(11, generalmemberVO.getMEM_PIC());
			pstmt.setString(12, generalmemberVO.getMEM_SELF());
			pstmt.setString(13, generalmemberVO.getMEM_NO());
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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
				gVO.setMEM_SELF(rs.getString("MEM_SELF"));
				gVO.setMEM_MAIL(rs.getString("MEM_MAIL"));
				gVO.setMEM_PSW(rs.getString("MEM_PSW"));
				gVO.setMEM_COIN(rs.getInt("MEM_COIN"));
				gVO.setMEM_PIC(rs.getBytes("MEM_PIC"));
				gVO.setMEM_STA(rs.getString("MEM_STA").charAt(0));
				gVO.setMEM_PHONE(rs.getString("MEM_PHONE"));
				gVO.setMEM_PBOARD(rs.getString("MEM_PBOARD").charAt(0));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
								
				gVO = new GeneralMemberVO();
				gVO.setMEM_NO(rs.getString("MEM_NO"));
				gVO.setMEM_NAME(rs.getString("MEM_NAME"));
				gVO.setMEM_GEN(rs.getString("MEM_GEN").charAt(0));
				gVO.setMEM_BIRTH(rs.getDate("MEM_BIRTH"));
				gVO.setMEM_ADDR(rs.getString("MEM_ADDR"));
				gVO.setMEM_MAIL(rs.getString("MEM_MAIL"));
				gVO.setMEM_SELF(rs.getString("MEM_SELF"));
				gVO.setMEM_PIC(rs.getBytes("MEM_PIC"));
				gVO.setMEM_PSW(rs.getString("MEM_PSW"));
				gVO.setMEM_COIN(rs.getInt("MEM_COIN"));
				gVO.setMEM_STA(rs.getString("MEM_STA").charAt(0));
				gVO.setMEM_PHONE(rs.getString("MEM_PHONE"));
				gVO.setMEM_PBOARD(rs.getString("MEM_PBOARD").charAt(0));
				list.add(gVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
	public GeneralMemberVO findByAccount(String MEM_MAIL) {
		GeneralMemberVO gVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FINDBYACCOUNT);
			pstmt.setString(1, MEM_MAIL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				gVO = new GeneralMemberVO();
				gVO.setMEM_NO(rs.getString("MEM_NO"));
				gVO.setMEM_NAME(rs.getString("MEM_NAME"));
				gVO.setMEM_GEN(rs.getString("MEM_GEN").charAt(0));
				gVO.setMEM_BIRTH(rs.getDate("MEM_BIRTH"));
				gVO.setMEM_ADDR(rs.getString("MEM_ADDR"));
				gVO.setMEM_SELF(rs.getString("MEM_SELF"));
				gVO.setMEM_MAIL(rs.getString("MEM_MAIL"));
				gVO.setMEM_PSW(rs.getString("MEM_PSW"));
				gVO.setMEM_COIN(rs.getInt("MEM_COIN"));
				gVO.setMEM_PIC(rs.getBytes("MEM_PIC"));
				gVO.setMEM_STA(rs.getString("MEM_STA").charAt(0));
				gVO.setMEM_PHONE(rs.getString("MEM_PHONE"));
				gVO.setMEM_PBOARD(rs.getString("MEM_PBOARD").charAt(0));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
	public void updatefromcoin(GeneralMemberVO generalmemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATECOIN);
			pstmt.setInt(1, generalmemberVO.getMEM_COIN());
			pstmt.setString(2, generalmemberVO.getMEM_NO());
			
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
	public void updateforSTA(GeneralMemberVO generalmemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEFORSTA);
			pstmt.setString(1, generalmemberVO.getMEM_STA().toString());
			pstmt.setString(2, generalmemberVO.getMEM_MAIL());
			
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
  
//---------------------------------------------------------------------------
  
	private static final String K_INSERT = "INSERT INTO GENERAL_MEMBER(MEM_NO, MEM_NAME, MEM_GEN, MEM_BIRTH, MEM_ADDR, MEM_MAIL, MEM_PSW, MEM_COIN, MEM_STATE,MEM_PHONE,MEM_PBOARD)"
			+ "VALUES('MG' || LPAD(MEM_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String K_UPDATE = "UPDATE GENERAL_MEMBER SET MEM_NAME=?,MEM_GEN=?,MEM_BIRTH=?, MEM_ADDR=?, MEM_MAIL=? , MEM_PSW=?,MEM_SELF=?,MEM_PIC=?, MEM_COIN=?, MEM_STA=? ,MEM_PHONE=?,MEM_PBOARD=? WHERE MEM_NO=?";
	private static final String K_DELETE = "UPDATE GENERAL_MEMBER SET MEM_STA='R' WHERE MEM_NO=?";
	private static final String K_FINDBYKEY = "SELECT MEM_NO, MEM_NAME, MEM_GEN, MEM_BIRTH, MEM_ADDR, MEM_MAIL, MEM_PSW, MEM_SELF ,MEM_PIC,MEM_COIN, MEM_STA,MEM_PHONE,MEM_PBOARD FROM GENERAL_MEMBER WHERE MEM_NO=?";
	private static final String K_FINDALL = "SELECT * FROM GENERAL_MEMBER Where MEM_STA != 'R'";


	@Override
	public void k_insert(GeneralMemberVO generalmemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_INSERT);
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
	public void k_update(GeneralMemberVO generalmemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_UPDATE);
			pstmt.setString(1, generalmemberVO.getMEM_NAME());
			pstmt.setString(2, String.valueOf(generalmemberVO.getMEM_GEN()));
			pstmt.setDate(3, generalmemberVO.getMEM_BIRTH());
			pstmt.setString(4, generalmemberVO.getMEM_ADDR());
			pstmt.setString(5, generalmemberVO.getMEM_MAIL());
			pstmt.setString(6, generalmemberVO.getMEM_PSW());
			pstmt.setString(7, generalmemberVO.getMEM_SELF());
			pstmt.setBytes(8, generalmemberVO.getMEM_PIC());
			pstmt.setInt(9, generalmemberVO.getMEM_COIN());
			pstmt.setString(10, String.valueOf(generalmemberVO.getMEM_STA()));
			pstmt.setString(11, generalmemberVO.getMEM_PHONE());
			pstmt.setString(12, String.valueOf(generalmemberVO.getMEM_PBOARD()));
			pstmt.setString(13, generalmemberVO.getMEM_NO());
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
	public void k_delete(String MEM_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_DELETE);
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
	public GeneralMemberVO k_findByPrimaryKey(String MEM_NO) {
		GeneralMemberVO gVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_FINDBYKEY);
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
				gVO.setMEM_SELF(rs.getString("MEM_SELF"));
				gVO.setMEM_PIC(rs.getBytes("MEM_PIC"));
				gVO.setMEM_COIN(rs.getInt("MEM_COIN"));
				gVO.setMEM_STA(rs.getString("MEM_STA").charAt(0));
				gVO.setMEM_PHONE(rs.getString("MEM_PHONE"));
				gVO.setMEM_PBOARD(rs.getString("MEM_PBOARD").charAt(0));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
	public List<GeneralMemberVO> k_getAll() {
		List<GeneralMemberVO> list = new ArrayList<GeneralMemberVO>();
		GeneralMemberVO gVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_FINDALL);
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
				list.add(gVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
