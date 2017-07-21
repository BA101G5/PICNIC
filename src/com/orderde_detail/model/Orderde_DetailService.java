package com.orderde_detail.model;

import java.util.List;

import com.goods_rent.model.Goods_RentVO;
import com.goods_sell.model.Goods_SellVO;
import com.place.model.PlaceVO;

public class Orderde_DetailService {
	private Orderde_DetailDAO dao = null;

	public Orderde_DetailService() {
		dao = new Orderde_DetailDAO();
	}

	public void addPlaceOrderde_Detail(Integer P_price, String p_no,String mem_no,String picnic_no,String od_place){
		Orderde_DetailVO orderde_detailVO=new Orderde_DetailVO();
		orderde_detailVO.setP_no(p_no);
 		orderde_detailVO.setMem_no(mem_no);
 		orderde_detailVO.setOd_amount(1);
 		orderde_detailVO.setOd_price(P_price);
 		orderde_detailVO.setPicnic_no(picnic_no);
 		orderde_detailVO.setOd_place(od_place);
 		System.out.println(picnic_no);
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

	public void deleteOrderde_Detail(String orderde_detailno) {
		
		dao.delete(orderde_detailno);
	}

	public Orderde_DetailVO getOne(String orderde_detailno) {
		return dao.findByPrimaryKey(orderde_detailno);
	}

	public List<Orderde_DetailVO> getAll() {
		return null;
	}

	public List<Orderde_DetailVO> getAllPICNICNO(String picnic_no){
		
		return dao.getAllPICNICNO(picnic_no);
	}
	
	public void addGrOrderde_Detail(Goods_RentVO goods_rentVO, Integer amount, String account, String picnic_no) {
	Orderde_DetailVO orderde_detailVO = new Orderde_DetailVO();
	orderde_detailVO.setPicnic_no(picnic_no);
	orderde_detailVO.setP_no(goods_rentVO.getP_no());
	orderde_detailVO.setGr_no(goods_rentVO.getGr_no());
	
	orderde_detailVO.setOd_amount(amount);
	orderde_detailVO.setOd_price(goods_rentVO.getGr_price());
	orderde_detailVO.setMem_no(account);	
	
	dao.insert(orderde_detailVO);	
	}
	public List<Orderde_DetailVO> getGsByMem(String mem_no){
		
		
		return dao.getGsByMenno(mem_no);
	}

	public void updateOrderde_Detail(List<Orderde_DetailVO> listGr, List<Orderde_DetailVO> listGs) {
		
		
		
		
		
		
		dao.updateOrderde_Detail(listGr, listGs);
		
	}

	public void getNumberByGsNo() {
		
		
	}
//-----------------------------------------------------------------
	public void k_addOrderde_Detail(Orderde_DetailVO orderde_detailVO) {
		dao.k_insert(orderde_detailVO);
	}

	public Orderde_DetailVO k_updateOrderde_Detail() {
		return null;
	}

	public void k_deleteOrderde_Detail() {
	}

	public Orderde_DetailVO k_getOne(String orderde_detailno) {
		return dao.k_findByPrimaryKey(orderde_detailno);
	}

	public List<Orderde_DetailVO> k_getAll() {
		return dao.k_getAll();
	}
	//--
	public List<Orderde_DetailVO> k_getAllByPicnic(String picnic_no){
		return dao.k_getAllByPicnic(picnic_no);
	}
	
	
	public Orderde_DetailVO k_gsGoodNumber(String gs_no,String pic_no) {
		Orderde_DetailVO ordVO =dao.k_gsGoodNumber(gs_no, pic_no);
		return ordVO;
	}
	
	public Orderde_DetailVO k_grGoodNumber(String gr_no,String pic_no) {
		Orderde_DetailVO ordVO =dao.k_grGoodNumber(gr_no, pic_no);
		return ordVO;
	}
}
