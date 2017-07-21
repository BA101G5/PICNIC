package com.checklist.model;

import java.sql.Timestamp;
import java.util.List;

public class ChecklistService {
	private Checklist_interface dao;
	
	public ChecklistService(){
		dao = new ChecklistDAO();
	}
	
	public ChecklistVO addChecklist(Integer chli_cate, String chli_be_num, String chli_memno, 
									String chli_reason, Timestamp chli_date){
		
		ChecklistVO checklistVO = new ChecklistVO();
		
		checklistVO.setChli_cate(chli_cate);
		checklistVO.setChli_be_num(chli_be_num);
		checklistVO.setChli_memno(chli_memno);
		checklistVO.setChli_reason(chli_reason);
		checklistVO.setChli_date(chli_date);
		dao.insert(checklistVO);
		
		return checklistVO;
	}
	
	public ChecklistVO updateChecklist(Timestamp chli_start, Integer chli_day, Timestamp chli_end, 
			Integer chli_pun, String chli_sta, String chli_no){
		
		ChecklistVO checklistVO = new ChecklistVO();
		
		checklistVO.setChli_start(chli_start);
		checklistVO.setChli_day(chli_day);
		checklistVO.setChli_end(chli_end);
		checklistVO.setChli_pun(chli_pun);
		checklistVO.setChli_sta(chli_sta);
		checklistVO.setChli_no(chli_no);
		dao.update(checklistVO);
		
		return checklistVO;
	}
	
	public void deleteChecklist(String chli_no){
		dao.delete(chli_no);
	}
	public ChecklistVO getOneChecklist(String chli_no){
		return dao.findByPrimaryKey(chli_no);
	}
	
	
	public void update_mf(String mf_no){
		ChecklistVO checklistVO = new ChecklistVO();
		checklistVO.setMf_no(mf_no);
		dao.update_mf(mf_no);
	}
	public void update_mem(String mem_no){
		ChecklistVO checklistVO = new ChecklistVO();
		checklistVO.setMem_no(mem_no);
		dao.update_mem(mem_no);
	}
	public void update_goods(String mf_no){
		ChecklistVO checklistVO = new ChecklistVO();
		checklistVO.setMf_no(mf_no);
		dao.update_goods(mf_no);
	}
	public void update_goods_one(String gs_no){
		ChecklistVO checklistVO = new ChecklistVO();
		checklistVO.setGs_no(gs_no);
		dao.update_goods_one(gs_no);
	}
	
	public void blockadePicnic(String picnic_no){
		ChecklistVO checklistVO = new ChecklistVO();
		checklistVO.setPicnic_no(picnic_no);
		dao.blockadePicnic(picnic_no);
	}
	public String findPicnicA(String picnic_no){
		ChecklistVO checklistVO = new ChecklistVO();
		checklistVO.setPicnic_no(picnic_no);
		String abc=dao.findPicnicA(picnic_no);
		return abc;
	}
	public String findAFMem(String article_no){
		ChecklistVO checklistVO = new ChecklistVO();
		checklistVO.setArticle_no(article_no);
		String mem = dao.findAFMem(article_no);
		return mem;
	}
	public void deleteArticle(String article_no){
		dao.deleteArticle(article_no);
	}
	public String findABMem(String artilce_no){
		ChecklistVO checklistVO = new ChecklistVO();
		checklistVO.setArticle_no(artilce_no);
		String mem = dao.findABMem(artilce_no);
		return mem;
	}
	public void deletePBArticle(String article_no){
		dao.deletePBArticle(article_no);
	}
	
	
	
	public List<ChecklistVO> getAllUndone(){
		return dao.getAllUndone();
	}
	public List<ChecklistVO> getAllDone(){
		return dao.getAllDone();
	}
	public List<ChecklistVO> getAllDone_0(){
		return dao.getAllDone_0();
	}
	public List<ChecklistVO> getAllDone_1(){
		return dao.getAllDone_1();
	}
	public List<ChecklistVO> getAllDone_2(){
		return dao.getAllDone_2();
	}
	public List<ChecklistVO> getAllDone_3(){
		return dao.getAllDone_3();
	}
	public List<ChecklistVO> getAllDone_4(){
		return dao.getAllDone_4();
	}
	public List<ChecklistVO> getAllUndone_0(){
		return dao.getAllUndone_0();
	}
	public List<ChecklistVO> getAllUndone_1(){
		return dao.getAllUndone_1();
	}
	public List<ChecklistVO> getAllUndone_2(){
		return dao.getAllUndone_2();
	}
	public List<ChecklistVO> getAllUndone_3(){
		return dao.getAllUndone_3();
	}
	public List<ChecklistVO> getAllUndone_4(){
		return dao.getAllUndone_4();
	}

  
  //-----------------------------------------------------------------
  
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
