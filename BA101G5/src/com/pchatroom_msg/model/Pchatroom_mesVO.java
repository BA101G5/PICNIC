package com.pchatroom_msg.model;

import java.sql.Timestamp;

public class Pchatroom_mesVO implements java.io.Serializable{
	private String crmsg_no;
	private String picnic_no;
	private String mem_no;
	private Timestamp message_date;
	private String message_text;
	private byte[] message_img;
	public String getCrmsg_no() {
		return crmsg_no;
	}
	public void setCrmsg_no(String crmsg_no) {
		this.crmsg_no = crmsg_no;
	}
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
	public Timestamp getMessage_date() {
		return message_date;
	}
	public void setMessage_date(Timestamp message_date) {
		this.message_date = message_date;
	}
	public String getMessage_text() {
		return message_text;
	}
	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}
	public byte[] getMessage_img() {
		return message_img;
	}
	public void setMessage_img(byte[] message_img) {
		this.message_img = message_img;
	}
}
