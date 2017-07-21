package com.commend.model;

import java.util.List;

public interface CommendDAO_interface {
	public void k_insert(CommendVO commendVO);
	public void k_update(CommendVO commendVO);
	public void k_delete(String comm_memno,String comm_be_no);
	public CommendVO k_findByPrimaryKey(String comm_memno,String comm_be_no);
	public List<CommendVO> k_getAll();
}
