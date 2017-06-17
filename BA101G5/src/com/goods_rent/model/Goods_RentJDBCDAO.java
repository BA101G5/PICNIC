package com.goods_rent.model;

import java.util.List;

public class Goods_RentJDBCDAO implements Goods_Rent_interface{
		String driver ="Oracle.jdbc.driver.Oracledriver";
		String url ="jdbc:oracle:thin@localhost:1521:XE";
		String userid ="ba101";
		String passwd ="ba101";
	
		private static final String INSERT_STMT ="insert into GOODS_RENT(GR_NO,MF_NO,P_NO,GR_NAME,GR_DATE,GR_PRICE,GR_COUNT,GR_INFO,GR_IMG,GR_UNTIL,GR_STA) values(?,?,?,?,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT ="select * from GOODS_RENT order by GR_NO";
		private static final String GET_ONE_STMT ="select GR_NO,MF_NO,P_NO,GR_NAME,GR_DATE,GR_PRICE,GR_COUNT,GR_INFO,GR_IMG,GR_UNTIL,GR_STA from GOODS_RENT where GR_NO =?";
		private static final String DELETE_STMT ="delete from GOODS_RENT where GR_NO =?";
		private static final String UPDATE_STMT ="update GOODS_RENT set MF_NO=?,P_NO=?,GR_NAME=?,GR_DATE=?,GR_PRICE=?,GR_COUNT=?,GR_INFO=?,GR_IMG=?,GR_UNTIL=?,GR_STA=? where GR_NO=?";

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
