package com.buy_record.model;
import java.sql.Timestamp;

public class Buy_RecordVO implements java.io.Serializable{
		private String br_id;
		private String mem_no;
		private Timestamp br_date;
		private Integer br_cash;
		public String getBr_id() {
			return br_id;
		}
		public void setBr_id(String br_id) {
			this.br_id = br_id;
		}
		public String getMem_no() {
			return mem_no;
		}
		public void setMem_no(String mem_no) {
			this.mem_no = mem_no;
		}
		public Timestamp getBr_date() {
			return br_date;
		}
		public void setBr_date(Timestamp br_date) {
			this.br_date = br_date;
		}
		public Integer getBr_cash() {
			return br_cash;
		}
		public void setBr_cash(Integer br_cash) {
			this.br_cash = br_cash;
		}
}
