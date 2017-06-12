package com.purview.model;
import java.util.List;

public interface Purview_interface {
	 public void insert (PurviewVO purviewVO);
	 public void update (PurviewVO purviewVO);
	 public void delete (String purview_no);
	 public PurviewVO findByPrimaryKey(String purview_no);
	 public List<PurviewVO> getAll();
}
