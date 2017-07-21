package com.goods_rent.model;

import java.util.List;

public class Goods_RentService {
	Goods_RentDAO_interface dao;

	public Goods_RentService() {
		dao = new Goods_RentDAO();
	}

	public Goods_RentVO addGoods_Rent(String MF_NO,String P_NO,String GR_NAME,java.sql.Timestamp GR_DATE,Integer GR_PRICE,Integer GR_COUNT,String GR_INFO,byte[] GR_IMG,java.sql.Timestamp GR_UNTIL,String GR_STA,String GR_PLACE) {
		Goods_RentVO grVO = new Goods_RentVO();
		
		grVO.setMf_no(MF_NO);
		grVO.setP_no(P_NO);
		grVO.setGr_name(GR_NAME);
		grVO.setGr_date(GR_DATE);
		grVO.setGr_price(GR_PRICE);
		grVO.setGr_count(GR_COUNT);
		grVO.setGr_info(GR_INFO);
		grVO.setGr_img(GR_IMG);
		grVO.setGr_until(GR_UNTIL);
		grVO.setGr_sta(GR_STA);
		grVO.setGr_place(GR_PLACE);
	
		
		dao.insert(grVO);
	
		
		return grVO;
	}

	public Goods_RentVO updateGoods_Rent(String GR_NO,String MF_NO,String P_NO,String GR_NAME,java.sql.Timestamp GR_DATE,Integer GR_PRICE,Integer GR_COUNT,String GR_INFO,byte[] GR_IMG,java.sql.Timestamp GR_UNTIL,String GR_STA,String GR_PLACE) {
		Goods_RentVO grVO = new Goods_RentVO();
		grVO.setGr_no(GR_NO);
		grVO.setMf_no(MF_NO);
		grVO.setP_no(P_NO);
		grVO.setGr_name(GR_NAME);
		grVO.setGr_date(GR_DATE);
		grVO.setGr_price(GR_PRICE);
		grVO.setGr_count(GR_COUNT);
		grVO.setGr_info(GR_INFO);
		grVO.setGr_img(GR_IMG);
		grVO.setGr_until(GR_UNTIL);
		grVO.setGr_sta(GR_STA);
		grVO.setGr_place(GR_PLACE);
	
		
		dao.update(grVO);
		
		
		return grVO;
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
	//-----------------------------------------------------------
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
