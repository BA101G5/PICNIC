package com.purview_detail.model;

import java.util.List;

public class Purview_DetailSerice {
	
	Purview_DetailDAO_interface dao;
	
	public Purview_DetailSerice(){
		dao = new Purview_DetailDAO();
	}
	
	 public Purview_DetailVO addPurview (Purview_DetailVO purview_detailVO){
		 Purview_DetailVO purdVO = new Purview_DetailVO();
		 purdVO = purview_detailVO;
		 dao.insert(purdVO);
		 return purdVO;
	 }
	 
	 public Purview_DetailVO modPurview (Purview_DetailVO purview_detailVO){
		 Purview_DetailVO purdVO = new Purview_DetailVO();
		 purdVO = purview_detailVO;
		 dao.update(purdVO);
		 return purdVO;
	 }
	 
	 public void delete (String adm_no){
		 dao.delete(adm_no);
	 }
	 
	 public List<Purview_DetailVO> findByPrimaryKey(String adm_no){
		 return dao.findByPrimaryKey(adm_no);
	 }
	 public List<Purview_DetailVO> getAll(String adm_no){
		 return dao.getAll(adm_no);
	 }
	
	
}
