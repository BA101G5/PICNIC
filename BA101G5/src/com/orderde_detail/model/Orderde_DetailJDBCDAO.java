package com.orderde_detail.model;

import java.util.List;

public class Orderde_DetailJDBCDAO implements Orderde_Detail_interface {
	String driver = "Oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin@localhost:1521:XE";
	String usserid = "ba101";
	String password = "ba101";

	private static final String INSERT_STMT  = " insert into ORDERDE_DETAIL (PICNIC_NO,P_NO,GR_NO,GS_NO,OD_AMOUNT,OD_PRICE,OD_DELIVER,OD_BS)values(?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = " select * from ORDERDE_DETAIL ORDER BY PICNIC_NO";
	private static final String GET_ONE_STMT = " select PICNIC_NO,P_NO,GR_NO,GS_NO,OD_AMOUNT,OD_PRICE,OD_DELIVER,OD_BS form ORDERDE_DETAIL where PICNIC_NO= ? ";
	private static final String DELETE_STMT  = " delete from ORDERDE_DETAIL where PICNIC_NO =? ";
	private static final String UPDATE_STMT  = " update ORDERDE_DETAIL set P_NO =? ,GR_NO = ?,GS_NO = ?,OD_AMOUNT =?,OD_PRICE =?,OD_DELIVER = ?,OD_BS =? where PICNIC_NO = ?";

	@Override
	public void insert(Orderde_DetailVO orderde_detailVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Orderde_DetailVO orderde_detailVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String picnic_no, String p_no, String gr_no, String gs_no) {
		// TODO Auto-generated method stub

	}

	@Override
	public Orderde_DetailVO findByPrimaryKey(String picnic_no, String p_no, String gr_no, String gs_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orderde_DetailVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
