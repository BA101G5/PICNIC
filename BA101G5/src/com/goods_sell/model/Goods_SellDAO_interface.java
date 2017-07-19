package com.goods_sell.model;
import java.util.List;

public interface Goods_SellDAO_interface {
		public void insert(Goods_SellVO goods_sellVO);
		public void update(Goods_SellVO goods_sellVO);
		public void delete(String gs_no);
		public Goods_SellVO findByPrimaryKey(String gs_no);
		public List<Goods_SellVO> getAll();
//--------------------------------------------------------------
		public void k_insert(Goods_SellVO goods_sellVO);
		public void k_update(Goods_SellVO goods_sellVO);
		public void k_delete(String gs_no);
		public Goods_SellVO k_findByPrimaryKey(String gs_no);
		public List<Goods_SellVO> k_getAll();
}
