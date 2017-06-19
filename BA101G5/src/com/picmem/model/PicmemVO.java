package com.picmem.model;

public class PicmemVO implements java.io.Serializable {
		private String picnic_no;
		private String mem_no;
		private String picmem_iden;
		private String picmem_sta;
		private Double mem_longi;
		private Double mem_latit;
		public String getPicnic_no() {
			return picnic_no;
		}
		public void setPicnic_no(String picnic_no) {
			this.picnic_no = picnic_no;
		}
		public String getMem_no() {
			return mem_no;
		}
		public void setMem_no(String mem_no) {
			this.mem_no = mem_no;
		}
		public String getPicmem_iden() {
			return picmem_iden;
		}
		public void setPicmem_iden(String picmem_iden) {
			this.picmem_iden = picmem_iden;
		}
		public String getPicmem_sta() {
			return picmem_sta;
		}
		public void setPicmem_sta(String picmem_sta) {
			this.picmem_sta = picmem_sta;
		}
		public Double getMem_longi() {
			return mem_longi;
		}
		public void setMem_longi(Double mem_longi) {
			this.mem_longi = mem_longi;
		}
		public Double getMem_latit() {
			return mem_latit;
		}
		public void setMem_latit(Double mem_latit) {
			this.mem_latit = mem_latit;
		}
}
