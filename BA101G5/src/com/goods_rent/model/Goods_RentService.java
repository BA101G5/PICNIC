package com.goods_rent.model;

import java.util.List;

public class Goods_RentService {
	Goods_RentDAO_interface dao;

	public Goods_RentService() {
		dao = new Goods_RentDAO();
	}

	public Goods_RentVO addGoods_Rent() {
		return null;
	}

	public Goods_RentVO updateGoods_Rent() {
		return null;
	}

	public void deleteGoods_Rent() {
	}

	public Goods_RentVO getOne(String gr_no) {
		return dao.findByPrimaryKey(gr_no);
	}

	public List<Goods_RentVO> getAll() {
		return dao.getAll();
	}

	public List<Goods_RentVO> findbyplace(String mf_no, String place) {
		return dao.findbyplace(mf_no, place);
	}
	//-------------------------------------------------------------------------
	public Goods_RentVO k_addGoods_Rent() {
		return null;
	}

	public Goods_RentVO k_updateGoods_Rent() {
		return null;
	}

	public void k_deleteGoods_Rent() {
	}

	public Goods_RentVO k_getOne(String gr_no) {
		return dao.k_findByPrimaryKey(gr_no);
	}

	public List<Goods_RentVO> k_getAll() {
		return dao.k_getAll();
	}
}
