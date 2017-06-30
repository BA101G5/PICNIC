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

	public Goods_RentVO getOne() {
		return null;
	}

	public List<Goods_RentVO> getAll() {
		return dao.getAll();
	}

}
