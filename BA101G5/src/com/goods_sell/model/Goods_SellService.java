package com.goods_sell.model;

import java.util.List;

public class Goods_SellService {
	private Goods_SellDAO dao = null;

	public Goods_SellService() {
		dao = new Goods_SellDAO();
	}

	public Goods_SellVO addGoods_Sell() {
		return null;
	}

	public Goods_SellVO updateGoods_Sell() {
		return null;
	}

	public void deleteGoods_Sell() {
	}

	public Goods_SellVO getOne() {
		return null;
	}

	public List<Goods_SellVO> getAll() {
		
		return dao.getAll();
	}

}
