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
//-----------------------------------------------------------------------
	public Goods_SellVO k_addGoods_Sell() {
		return null;
	}

	public Goods_SellVO k_updateGoods_Sell() {
		return null;
	}

	public void k_deleteGoods_Sell() {
	}

	public Goods_SellVO k_getOne(String gs_no) {
		//String table="goods_sells";
		Goods_SellVO goods_sellVO =dao.k_findByPrimaryKey(gs_no);
		//Imageout img=new Imageout();
		//System.out.println("hello");
		//String url=img.imageoutput(contextpath,table, goods_sellVO.getGs_name(), goods_sellVO.getGs_img());
		//goods_sellVO.setUrl(url);
		return goods_sellVO;
	}

	public List<Goods_SellVO> k_getAll() {
		
		return dao.k_getAll();
	}

}
