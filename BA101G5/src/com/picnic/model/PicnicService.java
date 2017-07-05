package com.picnic.model;

import java.sql.Date;
import java.sql.Timestamp;
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

	public void deletePicnic() {
	}

	public PicnicVO getOne() {
		return null;
	}

	public List<PicnicVO> getAll() {
		return null;
	}
	public void addPicnic(String picnic_name,Timestamp picnic_date,Integer picnic_pl){
		PicnicVO picnicVO=new PicnicVO();
		picnicVO.setPicnic_name(picnic_name);
		picnicVO.setPicnic_date(picnic_date);
		picnicVO.setPicnic_pl(picnic_pl);
		dao.addPicnic(picnicVO);
	
		
	}
}
