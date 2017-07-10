package com.goods_sell.model;
import java.util.List;
import com.util.encoding.Imageout;
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

	public Goods_SellVO getOne(String gs_no,String contextpath) {
		String table="goods_sells";
		Goods_SellVO goods_sellVO =dao.findByPrimaryKey(gs_no);
		Imageout img=new Imageout();
		String url=img.imageoutput(contextpath,table, goods_sellVO.getGs_no(), goods_sellVO.getGs_img());
		goods_sellVO.setUrl(url);
		return goods_sellVO;
	}

	public List<Goods_SellVO> getAll() {
		
		return dao.getAll();
	}

	public Goods_SellVO getOne(String gs_no) {
		
		return dao.findByPrimaryKey(gs_no);
	}


}
