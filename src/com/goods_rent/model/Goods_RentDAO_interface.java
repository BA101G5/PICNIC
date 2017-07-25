package com.goods_rent.model;
import java.util.List;

public interface Goods_RentDAO_interface {
		public void insert(Goods_RentVO goods_rentVO);
		public void update(Goods_RentVO goods_rentVO);
		public void delete(String gr_no);
		public Goods_RentVO findByPrimaryKey(String gr_no);
		public List<Goods_RentVO> getAll();
		public List<Goods_RentVO> findbyplace(String mf_no,String place);
		//---------------------------------------------------------
		public void k_insert(Goods_RentVO goods_rentVO);
		public void k_update(Goods_RentVO goods_rentVO);
		public void k_delete(String gr_no);
		public Goods_RentVO k_findByPrimaryKey(String gr_no);
		public List<Goods_RentVO> k_getAll();
	
}
