package com.goods_sell.model;
import java.util.List;

public interface Goods_Sell_interface {
		public void insert(Goods_SellVO goods_sellVO);
		public void update(Goods_SellVO goods_sellVO);
		public void delete(String gs_no);
		public Goods_SellVO findByPrimaryKey(String gs_no);
		public List<Goods_SellVO> getAll();

}
