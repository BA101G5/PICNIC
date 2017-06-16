package com.manufacturers.model;

import java.sql.Date;
import java.util.List;


public class ManufacturersService {
	ManufacturersDAO_interface dao;

	public ManufacturersService() {
		dao = new ManufacturersDAO();
	}

	public ManufacturersVO addManufacturers(String MF_NO, String MF_NAME, String MF_PHONE, String MF_MAIL,
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
}
