package com.goods_sell.model;
import java.util.List;

import com.manufacturers.model.ManufacturersVO;

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

	public List<Goods_SellVO> findByType(String type) {
		System.out.println(type);
		return dao.findByType(type);
	}


	public List<String> getcountbymf(List<ManufacturersVO> list2) {
		
		return dao.getcountbymf(list2) ;
	}

	public List<Goods_SellVO> finBymf(String type,String mf) {
	
		return dao.finBymf(type,mf);
	}


}
