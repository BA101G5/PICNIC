package com.goods_sell.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.manufacturers.model.ManufacturersVO;

public class Goods_SellJNDIDAO implements Goods_SellDAO_interface {
	private static DataSource ds = null;
	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT_STMT = "insert into GOODS_SELL(GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA) values('GS'||LPAD(GS_NO_SQ.nextval,8,0),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from GOODS_SELL ORDER BY GS_NO";
	private static final String GET_ONE_STMT = "select GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA from GOODS_SELL where GS_NO =?";
	private static final String DELETE_STMT = "delete from GOODS_SELL where GS_NO = ?";
	private static final String UPDATE_STMT = "update GOODS_SELL set MF_NO = ? ,GS_NAME = ? ,GS_DATE = ? ,GS_PRICE = ? ,GS_INFO = ? ,GS_IMG = ? ,GS_STA = ? where GS_NO = ? ";
	private static final String GET_ALL_BYTYPE_STMT = "select *  from GOODS_SELL where  GS_TYPE = ? ";
	private static final String GET_COUNT_BYMF_STMT = "select count(GS_TYPE)  from GOODS_SELL where MF_NO  = ? ";
	private static final String GET_ALL_BYMF_STMT = "select *  from GOODS_SELL where  GS_TYPE = ? and MF_NO = ?";
	
	@Override
	public void insert(Goods_SellVO goods_sellVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, goods_sellVO.getMf_no());
			pstmt.setString(2, goods_sellVO.getGs_name());
			pstmt.setTimestamp(3, goods_sellVO.getGs_date());
			pstmt.setInt(4, goods_sellVO.getGs_price());
			pstmt.setString(5, goods_sellVO.getGs_info());
			pstmt.setBytes(6, goods_sellVO.getGs_img());
			pstmt.setString(7, goods_sellVO.getGs_sta());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(Goods_SellVO goods_sellVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setString(1, goods_sellVO.getMf_no());
			pstmt.setString(2, goods_sellVO.getGs_name());
			pstmt.setTimestamp(3, goods_sellVO.getGs_date());
			pstmt.setInt(4, goods_sellVO.getGs_price());
			pstmt.setString(5, goods_sellVO.getGs_info());
			pstmt.setBytes(6, goods_sellVO.getGs_img());
			pstmt.setString(7, goods_sellVO.getGs_sta());
			pstmt.setString(8, goods_sellVO.getGs_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String gs_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, gs_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public Goods_SellVO findByPrimaryKey(String gs_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Goods_SellVO goods_sellVO = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, gs_no);
			rs = pstmt.executeQuery();
			rs.next();
				goods_sellVO = new Goods_SellVO();
				goods_sellVO.setGs_no(rs.getString("GS_NO"));
				goods_sellVO.setMf_no(rs.getString("MF_NO"));
				goods_sellVO.setGs_name(rs.getString("GS_NAME"));
				goods_sellVO.setGs_date(rs.getTimestamp("GS_DATE"));
				goods_sellVO.setGs_price(rs.getInt("GS_PRICE"));
				goods_sellVO.setGs_info(rs.getString("GS_INFO"));
				goods_sellVO.setGs_img(rs.getBytes("GS_IMG"));
				goods_sellVO.setGs_sta(rs.getString("GS_STA"));
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return goods_sellVO;
	}

	@Override
	public List<Goods_SellVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_SellVO> list = new ArrayList<Goods_SellVO>();
		ResultSet rs = null;
		try {
		    con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Goods_SellVO goods_sellVO = new Goods_SellVO();
				goods_sellVO.setGs_no(rs.getString("GS_NO"));
				goods_sellVO.setMf_no(rs.getString("MF_NO"));
				goods_sellVO.setGs_name(rs.getString("GS_NAME"));
				goods_sellVO.setGs_date(rs.getTimestamp("GS_DATE"));
				goods_sellVO.setGs_price(rs.getInt("GS_PRICE"));
				goods_sellVO.setGs_info(rs.getString("GS_INFO"));
				goods_sellVO.setGs_img(rs.getBytes("GS_IMG"));
				goods_sellVO.setGs_sta(rs.getString("GS_STA"));
				list.add(goods_sellVO);

			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return list;
	}
	@Override
	public List<Goods_SellVO> findByType(String type) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Goods_SellVO> list = new ArrayList<Goods_SellVO>();
		ResultSet rs = null;
		try {
		    con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BYTYPE_STMT);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Goods_SellVO goods_sellVO = new Goods_SellVO();
				goods_sellVO.setGs_no(rs.getString("GS_NO"));
				goods_sellVO.setMf_no(rs.getString("MF_NO"));
				goods_sellVO.setGs_name(rs.getString("GS_NAME"));
				goods_sellVO.setGs_date(rs.getTimestamp("GS_DATE"));
				goods_sellVO.setGs_price(rs.getInt("GS_PRICE"));
				goods_sellVO.setGs_info(rs.getString("GS_INFO"));
				goods_sellVO.setGs_img(rs.getBytes("GS_IMG"));
				goods_sellVO.setGs_sta(rs.getString("GS_STA"));
				list.add(goods_sellVO);

			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return list;
	}
@Override
	public List<String> getcountbymf(List<ManufacturersVO> list2) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<String> list = new ArrayList<String>();
		ResultSet rs = null;
		try {
		    con=ds.getConnection();
		    for(ManufacturersVO ManufacturersVO:list2){
			pstmt = con.prepareStatement(GET_COUNT_BYMF_STMT);	
			pstmt.setString(1, ManufacturersVO.getMF_NO());
			rs = pstmt.executeQuery();
		        rs.next();
		       String count=String.format("%s(%s)",ManufacturersVO.getMF_NAME(), rs.getString("COUNT(GS_TYPE)"));
		        System.out.println(count);
		        list.add(count);
		        }

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return list;
	}
@Override
public List<Goods_SellVO> finBymf(String type, String mf) {
	Connection con = null;
	PreparedStatement pstmt = null;
	List<Goods_SellVO> list = new ArrayList<Goods_SellVO>();
	ResultSet rs = null;
	try {
	    con=ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_BYMF_STMT);
		pstmt.setString(1, type);
		pstmt.setString(2,mf);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			Goods_SellVO goods_sellVO = new Goods_SellVO();
			goods_sellVO.setGs_no(rs.getString("GS_NO"));
			goods_sellVO.setMf_no(rs.getString("MF_NO"));
			goods_sellVO.setGs_name(rs.getString("GS_NAME"));
			goods_sellVO.setGs_date(rs.getTimestamp("GS_DATE"));
			goods_sellVO.setGs_price(rs.getInt("GS_PRICE"));
			goods_sellVO.setGs_info(rs.getString("GS_INFO"));
			goods_sellVO.setGs_img(rs.getBytes("GS_IMG"));
			goods_sellVO.setGs_sta(rs.getString("GS_STA"));
			list.add(goods_sellVO);

		}
	} catch (SQLException e) {
		throw new RuntimeException("A database error occured." + e.getMessage());
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
	return list;
}

@Override
public String findByType(String type, String mf_no) {
	// TODO Auto-generated method stub
	return null;
}
//-----------------------------------------------------------------------
private static final String K_INSERT_STMT = "insert into GOODS_SELL(GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA) values('GS'||LPAD(GS_NO_SQ.nextval,8,0),?,?,?,?,?,?,?)";
private static final String K_GET_ALL_STMT = "select * from GOODS_SELL ORDER BY GS_NO";
private static final String K_GET_ONE_STMT = "select GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA from GOODS_SELL where GS_NO =?";
private static final String K_DELETE_STMT = "delete from GOODS_SELL where GS_NO = ?";
private static final String K_UPDATE_STMT = "update GOODS_SELL set MF_NO = ? ,GS_NAME = ? ,GS_DATE = ? ,GS_PRICE = ? ,GS_INFO = ? ,GS_IMG = ? ,GS_STA = ? where GS_NO = ? ";


@Override
public void k_insert(Goods_SellVO goods_sellVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(K_INSERT_STMT);
		pstmt.setString(1, goods_sellVO.getMf_no());
		pstmt.setString(2, goods_sellVO.getGs_name());
		pstmt.setTimestamp(3, goods_sellVO.getGs_date());
		pstmt.setInt(4, goods_sellVO.getGs_price());
		pstmt.setString(5, goods_sellVO.getGs_info());
		pstmt.setBytes(6, goods_sellVO.getGs_img());
		pstmt.setString(7, goods_sellVO.getGs_sta());
		pstmt.executeUpdate();
	} catch (SQLException e) {
		throw new RuntimeException("A database error occured. " + e.getMessage());
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}
}

@Override
public void k_update(Goods_SellVO goods_sellVO) {
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(K_UPDATE_STMT);
		pstmt.setString(1, goods_sellVO.getMf_no());
		pstmt.setString(2, goods_sellVO.getGs_name());
		pstmt.setTimestamp(3, goods_sellVO.getGs_date());
		pstmt.setInt(4, goods_sellVO.getGs_price());
		pstmt.setString(5, goods_sellVO.getGs_info());
		pstmt.setBytes(6, goods_sellVO.getGs_img());
		pstmt.setString(7, goods_sellVO.getGs_sta());
		pstmt.setString(8, goods_sellVO.getGs_no());
		pstmt.executeUpdate();
	} catch (SQLException e) {
		throw new RuntimeException("A database error occured. " + e.getMessage());
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}

}

@Override
public void k_delete(String gs_no) {
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(K_DELETE_STMT);
		pstmt.setString(1, gs_no);
		pstmt.executeUpdate();
	} catch (SQLException e) {
		throw new RuntimeException("A database error occured. " + e.getMessage());
	} finally {

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}

}

@Override
public Goods_SellVO k_findByPrimaryKey(String gs_no) {
	Connection con = null;
	PreparedStatement pstmt = null;
	Goods_SellVO goods_sellVO = null;
	ResultSet rs = null;

	try {
		con = ds.getConnection();

		pstmt = con.prepareStatement(K_GET_ONE_STMT);
		pstmt.setString(1, gs_no);
		rs = pstmt.executeQuery();

		rs.next();
			goods_sellVO = new Goods_SellVO();
			goods_sellVO.setGs_no(rs.getString("GS_NO"));
			goods_sellVO.setMf_no(rs.getString("MF_NO"));
			goods_sellVO.setGs_name(rs.getString("GS_NAME"));
			goods_sellVO.setGs_date(rs.getTimestamp("GS_DATE"));
			goods_sellVO.setGs_price(rs.getInt("GS_PRICE"));
			goods_sellVO.setGs_info(rs.getString("GS_INFO"));
			goods_sellVO.setGs_img(rs.getBytes("GS_IMG"));
			goods_sellVO.setGs_sta(rs.getString("GS_STA"));
	} catch (SQLException e) {
		throw new RuntimeException("A database error occured. " + e.getMessage());
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
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace(System.err);
			}
		}
	}
	return goods_sellVO;
}

@Override
public List<Goods_SellVO> k_getAll() {
	Connection con = null;
	PreparedStatement pstmt = null;
	List<Goods_SellVO> list = new ArrayList<Goods_SellVO>();
	ResultSet rs = null;
	try {
	    con=ds.getConnection();
		pstmt = con.prepareStatement(K_GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			Goods_SellVO goods_sellVO = new Goods_SellVO();
			goods_sellVO.setGs_no(rs.getString("GS_NO"));
			goods_sellVO.setMf_no(rs.getString("MF_NO"));
			goods_sellVO.setGs_name(rs.getString("GS_NAME"));
			goods_sellVO.setGs_date(rs.getTimestamp("GS_DATE"));
			goods_sellVO.setGs_price(rs.getInt("GS_PRICE"));
			goods_sellVO.setGs_info(rs.getString("GS_INFO"));
			goods_sellVO.setGs_img(rs.getBytes("GS_IMG"));
			goods_sellVO.setGs_sta(rs.getString("GS_STA"));
			list.add(goods_sellVO);

		}
	} catch (SQLException e) {
		throw new RuntimeException("A database error occured." + e.getMessage());
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
	return list;
}

}