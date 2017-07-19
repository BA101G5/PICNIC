package com.checklist.model;

import java.util.List;

public class ChecklistService {
	
	ChecklistDAO_interface dao;
	
	public ChecklistService(){
		dao = new ChecklistDAO();
	}
	
	public ChecklistVO k_addChecklist(ChecklistVO chVO){
		ChecklistVO addChVO = chVO;
		dao.k_insert(addChVO);
		return addChVO;
	}
	
	public ChecklistVO k_updateChecklist(ChecklistVO chVO){
		ChecklistVO updateChVO = chVO;
		dao.k_update(updateChVO);
		return updateChVO;
	}
	
	public void k_delChecklist(String ch_no){
		dao.k_delete(ch_no);
	}
	
	public ChecklistVO k_getOneChecklist(String ch_no){
		return dao.k_findByPrimaryKey(ch_no);
	}
	
	public List<ChecklistVO> k_getAllChecklist(){
		return dao.k_getAll();
	}
	
	
}
