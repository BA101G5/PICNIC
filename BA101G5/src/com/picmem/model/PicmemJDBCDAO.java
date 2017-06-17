package com.picmem.model;

import java.util.List;

public class PicmemJDBCDAO implements Picmem_interface {
		private String driver="Oracle.jdbc.driver.OracleDriver";
		private String url="jdbc:oracle:thin@localhost:1521:XE";
		String userid = "BA101G5";
		String passwd = "BA101G5";
		  private static final String INSERT_STMT = "insert into PICMEM (PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT) values(?,?,?,?,?,?)";
		  private static final String GET_ALL_STMT = "select * from PICMEM order by PICNIC_NO";
		  private static final String GET_ONE_STMT ="select PICNIC_NO,MEM_NO,PICMEM_IDEN,PICMEM_STA,MEM_LONGI,MEM_LATIT from PICMEM where PICNIC_NO =? MEM_NO =? order by PICNIC_NO";
		  private static final String DELETE ="delete from PICMEM where PICNIC_NO =? MEM_NO =?";
		  private static final String UPDATE ="update PICMEM set PICNIC_NO=?,MEM_NO=?,PICMEM_IDEN=?,PICMEM_STATUS=?,MEM_LONGI=?,MEM_LATIT=? where PICNIC_NO =? MEM_NO =?" ; 

	@Override
	public void insert(PicmemVO picmemVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(PicmemVO picmemVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String picnic_no, String mem_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PicmemVO findByPrimaryKey(String picnic_no, String mem_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PicmemVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
