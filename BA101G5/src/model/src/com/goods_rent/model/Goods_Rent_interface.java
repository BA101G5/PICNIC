package com.goods_rent.model;
import java.util.List;

public interface Goods_Rent_interface {
		public void insert(Goods_RentVO goods_rentVO);
		public void update(Goods_RentVO goods_rentVO);
		public void delete(String gr_no);
		public Goods_RentVO findByPrimaryKey(String gr_no);
		public List<Goods_RentVO> getAll();
}
