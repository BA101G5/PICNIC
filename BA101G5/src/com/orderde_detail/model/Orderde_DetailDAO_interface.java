package com.orderde_detail.model;

import java.util.List;

public interface Orderde_DetailDAO_interface {
	public void insert(Orderde_DetailVO orderde_detailVO);
	public void update(Orderde_DetailVO orderde_detailVO);
	public void delete(String picnic_no,String p_no,String gr_no,String gs_no);
	public Orderde_DetailVO findByPrimaryKey(String picnic_no,String p_no,String gr_no,String gs_no);
	public List<Orderde_DetailVO> getAll();
}
