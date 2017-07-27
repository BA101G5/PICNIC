package com.picmem.model;

import java.util.List;

import com.pboard_article.model.Pboard_ArticleVO;

public class PicmemService {
	private PicmemDAO dao = null;

	public PicmemService() {
		dao = new PicmemDAO();
	}

	public PicmemVO addPicmem(String picnic_no, String mem_no) {
		PicmemVO picmemVO = new PicmemVO();
		
		picmemVO.setPicnic_no(picnic_no);
		picmemVO.setMem_no(mem_no);
		picmemVO.setPicmem_iden("¹Î­û");
		picmemVO.setPicmem_sta("A");
		picmemVO.setMem_longi(121.13);
		picmemVO.setMem_latit(24.57);
		dao.insert(picmemVO);

		return picmemVO;
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

	public List<PicmemVO> getAll(String picnic_no) {
		return dao.getAll(picnic_no);
	}
	
	public void addowner(String picnic_no, String mem_no) {
		PicmemVO picmemVO = new PicmemVO();
		picmemVO.setPicnic_no(picnic_no);
		picmemVO.setMem_no(mem_no);
		dao.insertowner(picmemVO);
	}

	public List<String> findbymem_no(String account) {

		return dao.findByMem_no(account);

	}
	//---------------------------------------------------------------
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
	
	public void k_deleteAllPicmem(String picnic_no) {
		dao.k_deleteAll(picnic_no);
	}

	public PicmemVO k_getOne(String picnic_no, String mem_no) {
		return dao.findByPrimaryKey(picnic_no, mem_no);
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
	
	
	public int count(String picnic_no, String mem_no) {
		return dao.count(picnic_no, mem_no);
	}
	
	public int amILord(String picnic_no, String mem_no) {
		return dao.amILord(picnic_no, mem_no);
	}
}
