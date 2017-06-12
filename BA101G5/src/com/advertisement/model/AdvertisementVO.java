package com.advertisement.model;
import java.sql.Date;
public class AdvertisementVO implements java.io.Serializable{
		private String ad_no;
		private String mf_no;
		private String ad_self;
		private byte[] ad_photo;
		private Date day_start;
		private Date day_end;
		private Double ad_cash;
		private String ad_sta;
		public String getAd_no() {
			return ad_no;
		}
		public void setAd_no(String ad_no) {
			this.ad_no = ad_no;
		}
		public String getMf_no() {
			return mf_no;
		}
		public void setMf_no(String mf_no) {
			this.mf_no = mf_no;
		}
		public String getAd_self() {
			return ad_self;
		}
		public void setAd_self(String ad_self) {
			this.ad_self = ad_self;
		}
		public byte[] getAd_photo() {
			return ad_photo;
		}
		public void setAd_photo(byte[] ad_photo) {
			this.ad_photo = ad_photo;
		}
		public Date getDay_start() {
			return day_start;
		}
		public void setDay_start(Date day_start) {
			this.day_start = day_start;
		}
		public Date getDay_end() {
			return day_end;
		}
		public void setDay_end(Date day_end) {
			this.day_end = day_end;
		}
		public Double getAd_cash() {
			return ad_cash;
		}
		public void setAd_cash(Double ad_cash) {
			this.ad_cash = ad_cash;
		}
		public String getAd_sta() {
			return ad_sta;
		}
		public void setAd_sta(String ad_sta) {
			this.ad_sta = ad_sta;
		}
}
