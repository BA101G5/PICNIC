package com.chatroom_message.model;

import java.sql.Timestamp;

public class Chatroom_MessageVO implements java.io.Serializable {
  
	private String cr_msg_no;
	private String chatroom_no;
	private String mem_no;
	private Timestamp message_date;
	private String message_text;
	private byte[] message_img;
	
	public String getCr_msg_no() {
		return cr_msg_no;
	}
	public void setCr_msg_no(String cr_msg_no) {
		this.cr_msg_no = cr_msg_no;
	}
	public String getChatroom_no() {
		return chatroom_no;
	}
	public void setChatroom_no(String chatroom_no) {
		this.chatroom_no = chatroom_no;
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
