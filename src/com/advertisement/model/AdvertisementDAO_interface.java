package com.advertisement.model;

import java.util.List;

public interface AdvertisementDAO_interface {

	public void insert(AdvertisementVO AdvertisementVO);

	public void update(AdvertisementVO AdvertisementVO);
	
	public void updateforSTA(AdvertisementVO AdvertisementVO);
	
	public void delete(String AD_NO);

	public AdvertisementVO findByPrimaryKey(String AD_NO);

	public List<AdvertisementVO> getAll();
	
	public List<AdvertisementVO> findByMM(String MF_NO);

	public List<AdvertisementVO> getAll_U();
	public List<AdvertisementVO> getAll_Other();
}
