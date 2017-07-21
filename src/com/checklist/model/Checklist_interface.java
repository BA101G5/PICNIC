package com.checklist.model;

import java.util.List;

public interface Checklist_interface {
	public void insert(ChecklistVO checklistVO);
	public void update(ChecklistVO checklistVO);
	
	public void update_mf(String mf_no);
	public void update_mem(String mem_no);
	public void update_goods(String mf_no);
	public void update_goods_one(String gs_no);
	public void blockadePicnic(String picnic_no);
	public String findPicnicA(String picnic_no);
	public void delete(String chli_no);
	public ChecklistVO findByPrimaryKey(String chli_no);

	public String findAFMem(String article_no);
	public void deleteArticle(String article_no);
	
	public String findABMem(String article_no);
	public void deletePBArticle(String article_no);
	
	public List<ChecklistVO> getAllUndone();
	public List<ChecklistVO> getAllDone();
	public List<ChecklistVO> getAllDone_0();
	public List<ChecklistVO> getAllDone_1();
	public List<ChecklistVO> getAllDone_2();
	public List<ChecklistVO> getAllDone_3();
	public List<ChecklistVO> getAllDone_4();
	public List<ChecklistVO> getAllUndone_0();
	public List<ChecklistVO> getAllUndone_1();
	public List<ChecklistVO> getAllUndone_2();
	public List<ChecklistVO> getAllUndone_3();
	public List<ChecklistVO> getAllUndone_4();

  //-----------------------------------------------------------------------------
  
	public void k_insert(ChecklistVO checklistVO);
	public void k_update(ChecklistVO checklistVO);
	public void k_delete(String chli_no);
	public ChecklistVO k_findByPrimaryKey(String chli_no);
	public List<ChecklistVO> k_getAll();
}
