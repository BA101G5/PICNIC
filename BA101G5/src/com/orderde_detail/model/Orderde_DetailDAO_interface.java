package com.orderde_detail.model;

import java.util.List;

public interface Orderde_DetailDAO_interface {
	public void insert(Orderde_DetailVO orderde_detailVO);
	public void update(Orderde_DetailVO orderde_detailVO);
	public void delete(String orderde_detailno);
	public Orderde_DetailVO findByPrimaryKey(String orderde_detailno);
	public List<Orderde_DetailVO> getAll();
	
}
