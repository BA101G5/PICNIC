package com.orderde_detail.model;
import java.sql.Timestamp;

public class Orderde_DetailVO implements java.io.Serializable {
		private String orderde_detailno;
		private String mem_no;
		private String picnic_no;
		private String p_no;
		private String gr_no;
		private String gs_no;
		private Integer od_amount;
		private Integer od_price;
		private Timestamp od_deliver;
		private String od_place;
		private String od_bs;
		
		public String getPicnic_no() {
			return picnic_no;
		}
		public void setPicnic_no(String picnic_no) {
			this.picnic_no = picnic_no;
		}
		public String getP_no() {
			return p_no;
		}
		public void setP_no(String p_no) {
			this.p_no = p_no;
		}
		public String getGr_no() {
			return gr_no;
		}
		public void setGr_no(String gr_no) {
			this.gr_no = gr_no;
		}
		public String getGs_no() {
			return gs_no;
		}
		public void setGs_no(String gs_no) {
			this.gs_no = gs_no;
		}
		public Integer getOd_amount() {
			return od_amount;
		}
		public void setOd_amount(Integer od_amount) {
			this.od_amount = od_amount;
		}
		public Integer getOd_price() {
			return od_price;
		}
		public void setOd_price(Integer od_price) {
			this.od_price = od_price;
		}
		public Timestamp getOd_deliver() {
			return od_deliver;
		}
		public void setOd_deliver(Timestamp od_deliver) {
			this.od_deliver = od_deliver;
		}
		public String getOd_bs() {
			return od_bs;
		}
		public void setOd_bs(String od_bs) {
			this.od_bs = od_bs;
		}
		public String getOrderde_detailno() {
			return orderde_detailno;
		}
		public void setOrderde_detailno(String orderde_detailno) {
			this.orderde_detailno = orderde_detailno;
		}
		public String getOd_place() {
			return od_place;
		}
		public void setOd_place(String od_place) {
			this.od_place = od_place;
		}
		public String getMem_no() {
			return mem_no;
		}
		public void setMem_no(String mem_no) {
			this.mem_no = mem_no;
		}
	
}
