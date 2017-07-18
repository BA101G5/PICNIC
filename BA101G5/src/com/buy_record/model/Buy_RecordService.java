package com.buy_record.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Buy_RecordService {
	Buy_RecordDAO_interface dao;

	public Buy_RecordService() {
		dao = new Buy_RecordDAO();
	}

	public Buy_RecordVO addBuy_record(String MEM_NO, Timestamp BR_DATE,Integer BR_CASH) {
		Buy_RecordVO bVO = new Buy_RecordVO();
		
		bVO.setMEM_NO(MEM_NO);
		bVO.setBR_DATE(BR_DATE);
		bVO.setBR_CASH(BR_CASH);
		
		dao.insert(bVO);
		return bVO;
	}

	public Buy_RecordVO updateBuy_record(String BR_ID,String MEM_NO, Timestamp BR_DATE,Integer BR_CASH) {
		Buy_RecordVO bVO = new Buy_RecordVO();
		bVO.setBR_ID(BR_ID);
		bVO.setMEM_NO(MEM_NO);
		bVO.setBR_DATE(BR_DATE);
		bVO.setBR_CASH(BR_CASH);
		dao.update(bVO);

		return bVO;
	}

	public void deleteBuy_record(String BR_ID) {
		dao.delete(BR_ID);
	}

	public Buy_RecordVO getOneBuy_record(String BR_ID) {
		return dao.findByPrimaryKey(BR_ID);
	}

	public List<Buy_RecordVO> getAll() {
		return dao.getAll();
	}
	public List<Buy_RecordVO> getForMG(String MEM_NO) {
		return dao.findByMG(MEM_NO);
	}
}
