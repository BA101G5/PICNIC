package com.goods_sell.model;

import java.util.List;

public class Goods_sellJDBCDAO implements Goods_Sell_interface {
	String driver = "Oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin@localhost:1521:XE";
	String userid = "ba101";
	String passwd = "ba101";

	private static final String INSERT_STMT = "insert into GOODS_SELL(GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA) values(?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "select * from GOODS_SELL ORDER BY GS_NO";
	private static final String GET_ONE_STMT = "select GS_NO,MF_NO,GS_NAME,GS_DATE,GS_PRICE,GS_INFO,GS_IMG,GS_STA from GOODS_SELL where GS_NO =?";
	private static final String DELETE_STMT = "delete from GOODS_SELL where GS_NO = ?";
	private static final String UPDATE_STMT = "update GOODS_SELL set MF_NO = ? ,GS_NAME = ? ,GS_DATE = ? ,GS_PRICE = ? ,GS_INFO = ? ,GS_IMG = ? ,GS_STA = ? where GS_NO = ? ";

	@Override
	public void insert(Goods_SellVO goods_sellVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Goods_SellVO goods_sellVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String gs_no) {
		// TODO Auto-generated method stub

	}

	@Override
	public Goods_SellVO findByPrimaryKey(String gs_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods_SellVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
