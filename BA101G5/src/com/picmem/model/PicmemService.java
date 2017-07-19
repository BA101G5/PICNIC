package com.picmem.model;

import java.util.List;

public class PicmemService {
	private PicmemDAO dao = null;

	public PicmemService() {
		dao = new PicmemDAO();
	}

	public PicmemVO addPicmem() {
		return null;
	}

	public PicmemVO updatePicmem() {
		return null;
	}

	public void deletePicmem() {
	}

	public PicmemVO getOne() {
		return null;
	}

	public List<PicmemVO> getAll() {
		return dao.getAll();
	}
	
	public void addowner(String picnic_no, String mem_no) {
		System.out.println("hello");
		PicmemVO picmemVO = new PicmemVO();
		picmemVO.setPicnic_no(picnic_no);
		picmemVO.setMem_no(mem_no);
		dao.insertowner(picmemVO);
	}

	public List<String> findbymem_no(String account) {

		return dao.findByMem_no(account);

	}
	//-----------------------------------------------
	public PicmemVO k_addPicmem(PicmemVO picVO) {
		PicmemVO picmemVO =	picVO;
		dao.k_insert(picmemVO);
		return picmemVO;
	}

	public PicmemVO k_updatePicmem() {
		return null;
	}

	public void k_deletePicmem(String picnic_no,String mem_no) {
		dao.k_delete(picnic_no, mem_no);
	}

	public PicmemVO k_getOne() {
		return null;
	}

	public List<PicmemVO> k_getAll() {
		return null;
	}
	public void k_addowner(String picnic_no,String mem_no){
		PicmemVO picmemVO =new PicmemVO();
		picmemVO.setPicnic_no(picnic_no);
		picmemVO.setMem_no(mem_no);
		dao.k_insertowner(picmemVO);
	}
	//---
	public List<String> k_getMemPicList(String mem_no){
		return dao.k_getMemPicList(mem_no);
	}
}
