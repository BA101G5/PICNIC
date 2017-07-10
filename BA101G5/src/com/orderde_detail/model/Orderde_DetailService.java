package com.orderde_detail.model;

import java.util.List;

public class Orderde_DetailService {
	private Orderde_DetailDAO dao = null;

	public Orderde_DetailService() {
		dao = new Orderde_DetailDAO();
	}

	public void addOrderde_Detail(Orderde_DetailVO orderde_detailVO) {
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
