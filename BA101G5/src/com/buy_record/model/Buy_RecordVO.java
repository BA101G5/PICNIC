package com.buy_record.model;

import java.sql.Date;

public class Buy_RecordVO {
		private String BR_ID;
		private String MEM_NO;
		private Date BR_DATE;
		private Integer BR_CASH;
		public String getBR_ID() {
			return BR_ID;
		}
		public void setBR_ID(String bR_ID) {
			BR_ID = bR_ID;
		}
		public String getMEM_NO() {
			return MEM_NO;
		}
		public void setMEM_NO(String mEM_NO) {
			MEM_NO = mEM_NO;
		}
		public Date getBR_DATE() {
			return BR_DATE;
		}
		public void setBR_DATE(Date bR_DATE) {
			BR_DATE = bR_DATE;
		}
		public Integer getBR_CASH() {
			return BR_CASH;
		}
		public void setBR_CASH(Integer bR_CASH) {
			BR_CASH = bR_CASH;
		}
		
		
}
