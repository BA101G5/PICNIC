package com.goods_rent.model;

import java.util.List;

public class Goods_RentJDBCDAO implements Goods_Rent_interface{
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
	public void insert(Goods_RentVO goods_rentVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Goods_RentVO goods_rentVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String gr_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Goods_RentVO findByPrimaryKey(String gr_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods_RentVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
