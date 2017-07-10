package com.orderde_detail.model;

import java.util.List;

public class Orderde_DetailService {
	private Orderde_DetailDAO dao = null;

	public Orderde_DetailService() {
		dao = new Orderde_DetailDAO();
	}

	public void addGsOrderde_Detail(String mem_no,String gs_no,Integer od_amount) {
	
		Orderde_DetailVO orderde_detailVO=new Orderde_DetailVO();
		orderde_detailVO.setGs_no(gs_no);
		orderde_detailVO.setMem_no(mem_no);
		orderde_detailVO.setOd_amount(od_amount);
		dao.insert(orderde_detailVO);
	}

	public Orderde_DetailVO updateOrderde_Detail() {
		return null;
	}

	public void deleteOrderde_Detail() {
	}

	public Orderde_DetailVO getOne(String orderde_detailno) {
		return dao.findByPrimaryKey(orderde_detailno);
	}

	public List<Orderde_DetailVO> getAll() {
		return null;
	}

}
