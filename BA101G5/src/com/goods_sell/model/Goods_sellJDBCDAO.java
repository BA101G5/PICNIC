package com.goods_sell.model;

import java.util.List;

public class Goods_sellJDBCDAO implements Goods_Sell_interface {
		String driver ="Oracle.driver.jdbc.OracleDriver";
		String url ="jdbc:oracle:thin@localhost:1521:XE";
		String userid ="ba101";
		String passwd ="ba101";
	
		private static final String INSERT_STMT ="";
		private static final String GET_ALL_STMT ="";
		private static final String GET_ONE_STMT ="";
		private static final String DELETE_STMT ="";
		private static final String UPDATE_STMT ="";

	
	
	
	
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
