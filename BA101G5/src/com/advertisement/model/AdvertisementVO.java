package com.advertisement.model;

import java.sql.Date;

public class AdvertisementVO {
	private String AD_NO;
	private String MF_NO;
	private String AD_SELF;
	private byte[] AD_PHOTO;
	private Date DAY_START;
	private Date DAY_END;
	private Integer AD_CASH;
	private Character AD_STA;
	
	
	
	
	public String getAD_NO() {
		return AD_NO;
	}
	public void setAD_NO(String aD_NO) {
		AD_NO = aD_NO;
	}
	public String getMF_NO() {
		return MF_NO;
	}
	public void setMF_NO(String mF_NO) {
		MF_NO = mF_NO;
	}
	public String getAD_SELF() {
		return AD_SELF;
	}
	public void setAD_SELF(String aD_SELF) {
		AD_SELF = aD_SELF;
	}
	public byte[] getAD_PHOTO() {
		return AD_PHOTO;
	}
	public void setAD_PHOTO(byte[] aD_PHOTO) {
		AD_PHOTO = aD_PHOTO;
	}
	public Date getDAY_START() {
		return DAY_START;
	}
	public void setDAY_START(Date dAY_START) {
		DAY_START = dAY_START;
	}
	public Date getDAY_END() {
		return DAY_END;
	}
	public void setDAY_END(Date dAY_END) {
		DAY_END = dAY_END;
	}
	public Integer getAD_CASH() {
		return AD_CASH;
	}
	public void setAD_CASH(Integer aD_CASH) {
		AD_CASH = aD_CASH;
	}
	public Character getAD_STA() {
		return AD_STA;
	}
	public void setAD_STA(Character aD_STA) {
		AD_STA = aD_STA;
	}
	
}
