package com.goods_sell.model;
import java.util.List;

import com.manufacturers.model.ManufacturersVO;

public interface Goods_SellDAO_interface {
		public void insert(Goods_SellVO goods_sellVO);
		public void update(Goods_SellVO goods_sellVO);
		public void delete(String gs_no);
		public Goods_SellVO findByPrimaryKey(String gs_no);
		public List<Goods_SellVO> getAll();
		public List<Goods_SellVO> findByType(String type);
		public List<String> getcountbymf(List<ManufacturersVO> list2);
		public List<Goods_SellVO> finBymf(String type, String mf);

}
