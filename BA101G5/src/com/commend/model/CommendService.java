package com.commend.model;

import java.util.List;

public class CommendService {

	CommendDAO_interface dao;
	
	public CommendService(){
		dao = new CommendDAO();
	}
	
	public CommendVO k_addCommend(CommendVO comVO){
		CommendVO addcomVO = comVO;
		dao.k_insert(addcomVO);
		return addcomVO;
	}
	
	public CommendVO k_updateCommend(CommendVO comVO){
		CommendVO updatecomVO = comVO;
		dao.k_update(updatecomVO);
		return updatecomVO;
	}
	
	public void k_delCommend(String comm_memno, String comm_be_no){
		dao.k_delete(comm_memno, comm_be_no);
	}
	
	public CommendVO k_getOneCommend(String comm_memno, String comm_be_no){
		return dao.k_findByPrimaryKey(comm_memno, comm_be_no);
	}
	
	public List<CommendVO> k_getAllCommend(){
		return dao.k_getAll();
	}
	
	
	
}
