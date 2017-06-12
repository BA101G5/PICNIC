package com.picmem.model;
import java.util.List;

public interface Picmem_interface {
	public void insert(PicmemVO picmemVO);
	public void update(PicmemVO picmemVO);
	public void delete(String picnic_no,String mem_no);
	public PicmemVO findByPrimaryKey(String picnic_no,String mem_no);
	public List<PicmemVO> getAll();
}
