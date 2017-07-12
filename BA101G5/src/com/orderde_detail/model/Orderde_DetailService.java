package com.orderde_detail.model;

import java.util.List;

import com.goods_sell.model.Goods_SellVO;
import com.place.model.PlaceVO;

public class Orderde_DetailService {
	private Orderde_DetailDAO dao = null;

	public Orderde_DetailService() {
		dao = new Orderde_DetailDAO();
	}

	public void addPlaceOrderde_Detail(PlaceVO placeVO, String p_no){
		Orderde_DetailVO orderde_detailVO=new Orderde_DetailVO();
		orderde_detailVO.setP_no(p_no);
// 		orderde_detailVO.setMem_no(mem_no);
		dao.insert(orderde_detailVO);
	}
	public void addGsOrderde_Detail(Goods_SellVO goods_sellVO ,Integer amount,String account) {
	
		Orderde_DetailVO orderde_detailVO=new Orderde_DetailVO();
		orderde_detailVO.setGs_no(goods_sellVO.getGs_no());
		Integer price=goods_sellVO.getGs_price();
		Integer totalprice=price*amount;
		
		orderde_detailVO.setOd_amount(amount);
		orderde_detailVO.setOd_price(totalprice);
		orderde_detailVO.setMem_no(account);
		dao.insert(orderde_detailVO);
	}

	public Orderde_DetailVO updateOrderde_Detail() {
		return null;
	}

	public void deleteOrderde_Detail() {
	}

	public Orderde_DetailVO getOne(String orderde_detailno) {
		return dao.findByPrimaryKey(orderde_detailno);
	}

	public List<Orderde_DetailVO> getAll() {
		return null;
	}

}
