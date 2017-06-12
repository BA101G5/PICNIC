package com.manufacturers.model;
import java.util.List;

public interface Manufacturers_interface {
		public void insert(ManufacturersVO manufacturersVO);
		public void update(ManufacturersVO manufacturersVO);
		public void delect(String mf_no);
		public ManufacturersVO findByPrimaryKey(String mf_no);
		public List<ManufacturersVO> getAll();

}
