package com.picnic.model;
import java.sql.Date;

public class PicnicVO implements java.io.Serializable{
		private String picnic_no;
		private String picnic_name;
		private String picnic_desc;
		private Date picupdate;
		private Date picnic_startup;
		private Date picnic_setup;
		private String picnic_chk;
		private Date picnic_date;
		private Integer picnic_pl;
		private String picnic_sta;
		private Double ord_total;
		private Date ord_date;
		private String ord_dm;
		private String ord_sta;
		public String getPicnic_no() {
			return picnic_no;
		}
		public void setPicnic_no(String picnic_no) {
			this.picnic_no = picnic_no;
		}
		public String getPicnic_name() {
			return picnic_name;
		}
		public void setPicnic_name(String picnic_name) {
			this.picnic_name = picnic_name;
		}
		public String getPicnic_desc() {
			return picnic_desc;
		}
		public void setPicnic_desc(String picnic_desc) {
			this.picnic_desc = picnic_desc;
		}
		public Date getPicupdate() {
			return picupdate;
		}
		public void setPicupdate(Date picupdate) {
			this.picupdate = picupdate;
		}
		public Date getPicnic_startup() {
			return picnic_startup;
		}
		public void setPicnic_startup(Date picnic_startup) {
			this.picnic_startup = picnic_startup;
		}
		public Date getPicnic_setup() {
			return picnic_setup;
		}
		public void setPicnic_setup(Date picnic_setup) {
			this.picnic_setup = picnic_setup;
		}
		public String getPicnic_chk() {
			return picnic_chk;
		}
		public void setPicnic_chk(String picnic_chk) {
			this.picnic_chk = picnic_chk;
		}
		public Date getPicnic_date() {
			return picnic_date;
		}
		public void setPicnic_date(Date picnic_date) {
			this.picnic_date = picnic_date;
		}
		public Integer getPicnic_pl() {
			return picnic_pl;
		}
		public void setPicnic_pl(Integer picnic_pl) {
			this.picnic_pl = picnic_pl;
		}
		public String getPicnic_sta() {
			return picnic_sta;
		}
		public void setPicnic_sta(String picnic_sta) {
			this.picnic_sta = picnic_sta;
		}
		public Double getOrd_total() {
			return ord_total;
		}
		public void setOrd_total(Double ord_total) {
			this.ord_total = ord_total;
		}
		public Date getOrd_date() {
			return ord_date;
		}
		public void setOrd_date(Date ord_date) {
			this.ord_date = ord_date;
		}
		public String getOrd_dm() {
			return ord_dm;
		}
		public void setOrd_dm(String ord_dm) {
			this.ord_dm = ord_dm;
		}
		public String getOrd_sta() {
			return ord_sta;
		}
		public void setOrd_sta(String ord_sta) {
			this.ord_sta = ord_sta;
		}
}
