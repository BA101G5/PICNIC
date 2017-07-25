package com.manufacturers.model;

import java.sql.Date;
import java.util.List;

import com.general_member.model.GeneralMemberVO;


public class ManufacturersService {
	ManufacturersDAO_interface dao;

	public ManufacturersService() {
		dao = new ManufacturersDAO();
	}

	public ManufacturersVO addManufacturers(String MF_NAME, String MF_PHONE, String MF_MAIL,
			String MF_ACCO, String MF_PSW, byte[] MF_LOGO, String MF_SELF, String MF_BS, String MF_ADDR,
			String MF_FAX, Character MF_STA, Integer MF_REPORTNUM) {
		ManufacturersVO mVO = new ManufacturersVO();
		
//		mVO.setMF_NO(MF_NO);
		mVO.setMF_NAME(MF_NAME);
		mVO.setMF_PHONE(MF_PHONE);
		mVO.setMF_MAIL(MF_MAIL);
		mVO.setMF_ACCO(MF_ACCO);
		mVO.setMF_PSW(MF_PSW);
		mVO.setMF_LOGO(MF_LOGO);
		mVO.setMF_SELF(MF_SELF);
		mVO.setMF_BS(MF_BS);
		mVO.setMF_ADDR(MF_ADDR);
		mVO.setMF_FAX(MF_FAX);
		mVO.setMF_STA(MF_STA);
		mVO.setMF_REPORTNUM(MF_REPORTNUM);
		dao.insert(mVO);
		return mVO;
	}

	public ManufacturersVO updateManufacturers(String MF_NO, String MF_NAME, String MF_PHONE, String MF_MAIL,
			String MF_ACCO, String MF_PSW, byte[] MF_LOGO, String MF_SELF, String MF_BS, String MF_ADDR,
			String MF_FAX, Character MF_STA, Integer MF_REPORTNUM) {
		ManufacturersVO mVO = new ManufacturersVO();
		
		mVO.setMF_NO(MF_NO);
		mVO.setMF_NAME(MF_NAME);
		mVO.setMF_PHONE(MF_PHONE);
		mVO.setMF_MAIL(MF_MAIL);
		mVO.setMF_ACCO(MF_ACCO);
		mVO.setMF_PSW(MF_PSW);
		mVO.setMF_LOGO(MF_LOGO);
		mVO.setMF_SELF(MF_SELF);
		mVO.setMF_BS(MF_BS);
		mVO.setMF_ADDR(MF_ADDR);
		mVO.setMF_FAX(MF_FAX);
		mVO.setMF_STA(MF_STA);
		mVO.setMF_REPORTNUM(MF_REPORTNUM);
		dao.update(mVO);

		return mVO;
	}

	public void deleteManufacturers(String MF_NO) {
		dao.delete(MF_NO);
	}

	public ManufacturersVO getOneManufacturers(String MF_NO) {
		return dao.findByPrimaryKey(MF_NO);
	}

	public List<ManufacturersVO> getAll() {
		return dao.getAll();
	}
	public void updateforSTA(String MF_ACCO, Character MF_STA) {
		ManufacturersVO mVO = new ManufacturersVO();

		mVO.setMF_ACCO(MF_ACCO);

		mVO.setMF_STA(MF_STA);

		dao.updateforSTA(mVO);

	}

	public String findByMfname(String mf_name) {
		
		return dao.findByMfName(mf_name);
	}
  
	public void updateManufacturers(ManufacturersVO ma_detail) {
		// TODO Auto-generated method stub
	}
	
	
	//------------------------------------------------------
	public ManufacturersVO k_addManufacturers(String MF_NO, String MF_NAME, String MF_PHONE, String MF_MAIL,
			String MF_ACCO, String MF_PSW, byte[] MF_LOGO, String MF_SELF, String MF_BS, String MF_ADDR,
			String MF_FAX, Character MF_STA, Integer MF_REPORTNUM) {
		ManufacturersVO mVO = new ManufacturersVO();
		
		mVO.setMF_NO(MF_NO);
		mVO.setMF_NAME(MF_NAME);
		mVO.setMF_PHONE(MF_PHONE);
		mVO.setMF_MAIL(MF_MAIL);
		mVO.setMF_ACCO(MF_ACCO);
		mVO.setMF_PSW(MF_PSW);
		mVO.setMF_LOGO(MF_LOGO);
		mVO.setMF_SELF(MF_SELF);
		mVO.setMF_BS(MF_BS);
		mVO.setMF_ADDR(MF_ADDR);
		mVO.setMF_FAX(MF_FAX);
		mVO.setMF_STA(MF_STA);
		mVO.setMF_REPORTNUM(MF_REPORTNUM);
		dao.k_insert(mVO);
		return mVO;
	}

	public ManufacturersVO k_updateManufacturers(ManufacturersVO mVO) {
		ManufacturersVO upMVO = new ManufacturersVO();
		
		upMVO.setMF_NO(mVO.getMF_NO());
		upMVO.setMF_NAME(mVO.getMF_NAME());
		upMVO.setMF_PHONE(mVO.getMF_PHONE());
		upMVO.setMF_MAIL(mVO.getMF_MAIL());
		upMVO.setMF_ACCO(mVO.getMF_ACCO());
		upMVO.setMF_PSW(mVO.getMF_PSW());
		upMVO.setMF_LOGO(mVO.getMF_LOGO());
		upMVO.setMF_SELF(mVO.getMF_SELF());
		upMVO.setMF_BS(mVO.getMF_BS());
		upMVO.setMF_ADDR(mVO.getMF_ADDR());
		upMVO.setMF_FAX(mVO.getMF_FAX());
		upMVO.setMF_STA(mVO.getMF_STA());
		upMVO.setMF_REPORTNUM(mVO.getMF_REPORTNUM());
		dao.k_update(upMVO);
		return upMVO;
	}

	public void k_deleteManufacturers(String MF_NO) {
		dao.k_delete(MF_NO);
	}

	public ManufacturersVO k_getOneManufacturers(String MF_NO) {
		return dao.k_findByPrimaryKey(MF_NO);
	}

	public List<ManufacturersVO> k_getAll() {
		return dao.k_getAll();
	}
}
