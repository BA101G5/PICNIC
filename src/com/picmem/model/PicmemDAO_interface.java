package com.picmem.model;
import java.util.List;

public interface PicmemDAO_interface {
	public void insert(PicmemVO picmemVO);
	public void update(PicmemVO picmemVO);
	public void delete(String picnic_no,String mem_no);
	public PicmemVO findByPrimaryKey(String picnic_no,String mem_no);
	public List<PicmemVO> getAll();
	public List<PicmemVO> getAll(String picnicNo);
	public void insertowner(PicmemVO picmemVO);
	public List<String> findByMem_no(String mem_no);
	//---------------------------------------------------------------
	public void k_insert(PicmemVO picmemVO);
	public void k_update(PicmemVO picmemVO);
	public void k_delete(String picnic_no,String mem_no);
	public PicmemVO k_findByPrimaryKey(String picnic_no,String mem_no);
	public List<PicmemVO> k_getAll();
	public void k_insertowner(PicmemVO picmemVO);

}
