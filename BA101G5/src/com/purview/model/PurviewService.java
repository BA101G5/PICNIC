package com.purview.model;

import java.util.List;

import com.administrator.model.AdministratorDAO;

public class PurviewService {

	PurviewDAO_interface dao;
	
	public PurviewService() {
		dao = new PurviewDAO();
	}
	
	public PurviewVO addPurview (PurviewVO purviewVO){
		PurviewVO purVO= new PurviewVO(); 
		purVO = purviewVO;
		dao.insert(purVO);
		return purVO;
	}
	
	 public PurviewVO modPurview(PurviewVO purviewVO){
		PurviewVO purVO= new PurviewVO(); 
		purVO = purviewVO;
		dao.update(purVO);
		return purVO;
	 }
	 public  void deletePurview (String purview_no){
		 dao.delete(purview_no);
	 }
	 public PurviewVO findByPrimaryKey(String purview_no){
		 return dao.findByPrimaryKey(purview_no);
	 }
	 public List<PurviewVO> getAll(){
		 return dao.getAll();
	 }
	
	
}
