package com.advertisement.model;

import java.sql.Date;
import java.util.List;


public class AdvertisementService {
	AdvertisementDAO_interface dao;

	public AdvertisementService() {
		dao = new AdvertisementDAO();
	}

	public AdvertisementVO addAdvertisement(String AD_NO,String MF_NO, String AD_SELF, byte[] AD_PHOTO, Date DAY_START,
			Date DAY_END, Integer AD_CASH, Character AD_STA) {
		AdvertisementVO aVO = new AdvertisementVO();
		
		aVO = new AdvertisementVO();
		aVO.setAD_NO(AD_NO);
		aVO.setMF_NO(MF_NO);
		aVO.setAD_SELF(AD_SELF);
		aVO.setAD_PHOTO(AD_PHOTO);
		aVO.setDAY_START(DAY_START);
		aVO.setDAY_END(DAY_END);
		aVO.setAD_CASH(AD_CASH);
		aVO.setAD_STA(AD_STA);
		dao.insert(aVO);
		return aVO;
	}

	public AdvertisementVO updateAdvertisement(String MF_NO, String AD_SELF, byte[] AD_PHOTO, Date DAY_START,
			Date DAY_END, Integer AD_CASH, Character AD_STA) {
AdvertisementVO aVO = new AdvertisementVO();
		
		aVO = new AdvertisementVO();
		aVO.setMF_NO(MF_NO);
		aVO.setAD_SELF(AD_SELF);
		aVO.setAD_PHOTO(AD_PHOTO);
		aVO.setDAY_START(DAY_START);
		aVO.setDAY_END(DAY_END);
		aVO.setAD_CASH(AD_CASH);
		aVO.setAD_STA(AD_STA);
		dao.update(aVO);

		return aVO;
	}

	public void deleteAdvertisement(String AD_NO) {
		dao.delete(AD_NO);
	}

	public AdvertisementVO getOneAdvertisement(String AD_NO) {
		return dao.findByPrimaryKey(AD_NO);
	}

	public List<AdvertisementVO> getAll() {
		return dao.getAll();
	}
}