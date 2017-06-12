package com.commend.model;
import java.sql.Timestamp;

public class CommendVO implements java.io.Serializable{
		private String comm_memno;
		private String comm_be_no;
		private Integer comm_cate;
		private Integer comm_grade;
		private Timestamp comm_date;
		public String getComm_memno() {
			return comm_memno;
		}
		public void setComm_memno(String comm_memno) {
			this.comm_memno = comm_memno;
		}
		public String getComm_be_no() {
			return comm_be_no;
		}
		public void setComm_be_no(String comm_be_no) {
			this.comm_be_no = comm_be_no;
		}
		public Integer getComm_cate() {
			return comm_cate;
		}
		public void setComm_cate(Integer comm_cate) {
			this.comm_cate = comm_cate;
		}
		public Integer getComm_grade() {
			return comm_grade;
		}
		public void setComm_grade(Integer comm_grade) {
			this.comm_grade = comm_grade;
		}
		public Timestamp getComm_date() {
			return comm_date;
		}
		public void setComm_date(Timestamp comm_date) {
			this.comm_date = comm_date;
		}
}
