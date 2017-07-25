package com.orderde_detail.model;

import java.util.List;

import com.goods_sell.model.Goods_SellVO;

public interface Orderde_DetailDAO_interface {
	public void insert(Orderde_DetailVO orderde_detailVO);
	public void update(Orderde_DetailVO orderde_detailVO);
	public void delete(String orderde_detailno);
	public Orderde_DetailVO findByPrimaryKey(String orderde_detailno);
	public List<Orderde_DetailVO> getAll();
	public List<Orderde_DetailVO> getAllPICNICNO(String picnic_no);
	public List<Orderde_DetailVO> getGsByMenno(String mem_no);
	public void updateOrderde_Detail(List<Orderde_DetailVO> listGr, List<Orderde_DetailVO> listGs);
//---------------------------------------------------------------
	public void k_insert(Orderde_DetailVO orderde_detailVO);
	public void k_update(Orderde_DetailVO orderde_detailVO);
	public void k_delete(String orderde_detailno);
	public Orderde_DetailVO k_findByPrimaryKey(String orderde_detailno);
	public List<Orderde_DetailVO> k_getAll();
	
}
