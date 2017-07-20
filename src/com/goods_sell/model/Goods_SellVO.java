package com.goods_sell.model;
import java.sql.Timestamp;

public class Goods_SellVO implements java.io.Serializable{
		private String gs_no;
		private String mf_no;
		private String gs_name;
		private Timestamp gs_date;
		private Integer gs_price;
		private String gs_info;
		private byte[] gs_img;
		private String gs_sta;
		private String gs_type;
		public String getGs_type() {
			return gs_type;
		}
		public void setGs_type(String gs_type) {
			this.gs_type = gs_type;
		}
		public String getGs_no() {
			return gs_no;
		}
		public void setGs_no(String gs_no) {
			this.gs_no = gs_no;
		}
		public String getMf_no() {
			return mf_no;
		}
		public void setMf_no(String mf_no) {
			this.mf_no = mf_no;
		}
		public String getGs_name() {
			return gs_name;
		}
		public void setGs_name(String gs_name) {
			this.gs_name = gs_name;
		}
		public Timestamp getGs_date() {
			return gs_date;
		}
		public void setGs_date(Timestamp gs_date) {
			this.gs_date = gs_date;
		}
		public Integer getGs_price() {
			return gs_price;
		}
		public void setGs_price(Integer gs_price) {
			this.gs_price = gs_price;
		}
		public String getGs_info() {
			return gs_info;
		}
		public void setGs_info(String gs_info) {
			this.gs_info = gs_info;
		}
		public byte[] getGs_img() {
			return gs_img;
		}
		public void setGs_img(byte[] gs_img) {
			this.gs_img = gs_img;
		}
		public String getGs_sta() {
			return gs_sta;
		}
		public void setGs_sta(String gs_sta) {
			this.gs_sta = gs_sta;
		}
		
		
}
