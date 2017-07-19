package com.picnic.model;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface PicnicDAO_interface {
		public void insert(PicnicVO picnicVO);
		public void update(PicnicVO picnicVO);
		public void delete(String picnic_no);
		public PicnicVO findByPrimaryKey(String picnic_no);
		public List<PicnicVO> getAll();
		public String addPicnic(PicnicVO picnicVO);
		

}
