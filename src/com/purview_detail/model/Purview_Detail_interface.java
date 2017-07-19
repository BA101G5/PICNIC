package com.purview_detail.model;
import java.util.List;

public interface Purview_Detail_interface {
	 public void insert (Purview_DetailVO purview_detailVO);
	 public void update (Purview_DetailVO purview_detailVO);
	 public void delete (String adm_no);
	 public List<Purview_DetailVO> findByPrimaryKey(String adm_no);
	 public List<Purview_DetailVO> getAll(String adm_no);

}
