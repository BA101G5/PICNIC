package com.picnic.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PicnicService {
	private PicnicDAO dao = null;

	public PicnicService() {
		dao = new PicnicDAO();
	}

	public PicnicVO addPicnic() {
		return null;
	}

	public PicnicVO updatePicnic() {
		return null;
	}

	public void update_desc(String picnic_no, String desc) {
		dao.update_desc(picnic_no, desc);;
	}	
	
	public void deletePicnic() {
	}

	public PicnicVO getOnePicnic(String picnic_no) {
		return dao.findByPrimaryKey(picnic_no);
	}
	
	public PicnicVO getOne() {
		return null;
	}

	public List<PicnicVO> getAll() {
		return dao.getAll();
	}
	
	public List<PicnicVO> getAll_sl() {
		return dao.getAll_sl();
	}
	
	public String addPicnic(String picnic_name, Timestamp picnic_date, Integer picnic_pl) {
		PicnicVO picnicVO = new PicnicVO();
		picnicVO.setPicnic_name(picnic_name);
		picnicVO.setPicnic_date(picnic_date);
		picnicVO.setPicnic_pl(picnic_pl);
		String picnic_no = dao.addPicnic(picnicVO);
		return picnic_no;

	}

	public PicnicVO getByPicnic_No(String picnic_no) {
		return dao.findByPrimaryKey(picnic_no);
	}

	public List<PicnicVO> getByPicnic_Nos(List<String> list) {
		List<PicnicVO> picnicname = new ArrayList<PicnicVO>();

		for (String picnic_no : list) {
			PicnicVO picnicVO = dao.findByPrimaryKeywherepicnic_no(picnic_no);
			if (picnicVO != null) {
				picnicname.add(picnicVO);
			}
		}
		return picnicname;

	}
	//----------------------------------------------------------
	public PicnicVO k_addPicnic() {
		return null;
	}

	public PicnicVO k_updatePicnic() {
		return null;
	}

	public void k_deletePicnic(String picnic_no) {
		dao.k_delete(picnic_no);
	}

	public PicnicVO k_getOne(String picnic_no) {
		return dao.findByPrimaryKey(picnic_no);
	}

	public List<PicnicVO> k_getAll() {
		return dao.getAll();
	}
	
	public List<String> k_getAllandMEM(String MEM_NO,String searchWord){
		return dao.k_getAllandMEM(MEM_NO,searchWord);
	}
	
	public String k_addPicnic(String picnic_name,Timestamp picnic_date,Integer picnic_pl){
		PicnicVO picnicVO=new PicnicVO();
		picnicVO.setPicnic_name(picnic_name);
		picnicVO.setPicnic_date(picnic_date);
		picnicVO.setPicnic_pl(picnic_pl);
		String picnic_no = dao.addPicnic(picnicVO);
			return picnic_no;
		
	}
	
	public List<PicnicVO> k_getAllSearch(String searchWord){
		return dao.k_get_search_list(searchWord);
	}

	public void updatepicnic(String picnic_no,Integer tlprice) {
		dao.updatepicnic(picnic_no,tlprice);
		
	}
}
