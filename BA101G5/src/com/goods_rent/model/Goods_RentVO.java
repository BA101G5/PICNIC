package com.goods_rent.model;
import java.sql.Date;
public class Goods_RentVO implements java.io.Serializable {
		private String gr_no;
		private String mf_no;
		private String p_no;
		private String gr_name;
		private Date gr_date;
		private Integer gr_price;
		private Integer gr_count;
		private String gr_info;
		private byte[] gr_img;
		private Date gr_until;
		private String gr_sta;
		public String getGr_no() {
			return gr_no;
		}
		public void setGr_no(String gr_no) {
			this.gr_no = gr_no;
		}
		public String getMf_no() {
			return mf_no;
		}
		public void setMf_no(String mf_no) {
			this.mf_no = mf_no;
		}
		public String getP_no() {
			return p_no;
		}
		public void setP_no(String p_no) {
			this.p_no = p_no;
		}
		public String getGr_name() {
			return gr_name;
		}
		public void setGr_name(String gr_name) {
			this.gr_name = gr_name;
		}
		public Date getGr_date() {
			return gr_date;
		}
		public void setGr_date(Date gr_date) {
			this.gr_date = gr_date;
		}
		public Integer getGr_price() {
			return gr_price;
		}
		public void setGr_price(Integer gr_price) {
			this.gr_price = gr_price;
		}
		public Integer getGr_count() {
			return gr_count;
		}
		public void setGr_count(Integer gr_count) {
			this.gr_count = gr_count;
		}
		public String getGr_info() {
			return gr_info;
		}
		public void setGr_info(String gr_info) {
			this.gr_info = gr_info;
		}
		public byte[] getGr_img() {
			return gr_img;
		}
		public void setGr_img(byte[] gr_img) {
			this.gr_img = gr_img;
		}
		public Date getGr_until() {
			return gr_until;
		}
		public void setGr_until(Date gr_until) {
			this.gr_until = gr_until;
		}
		public String getGr_sta() {
			return gr_sta;
		}
		public void setGr_sta(String gr_sta) {
			this.gr_sta = gr_sta;
		}
}
