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



	public List<Goods_SellVO> getAll() {
		
		return dao.getAll();
	}

	public Goods_SellVO getOne(String gs_no) {
		
		return dao.findByPrimaryKey(gs_no);
	}


}
