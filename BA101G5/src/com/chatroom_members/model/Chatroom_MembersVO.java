package com.chatroom_members.model;

import java.sql.Timestamp;

public class Chatroom_MembersVO implements java.io.Serializable{
	private String chatroom_no;
	private String mem_no;
	private Character chatroom_role;
	private Timestamp ban_until;
	
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
	public Character getChatroom_role() {
		return chatroom_role;
	}
	public void setChatroom_role(Character chatroom_role) {
		this.chatroom_role = chatroom_role;
	}
	public Timestamp getBan_until() {
		return ban_until;
	}
	public void setBan_until(Timestamp ban_until) {
		this.ban_until = ban_until;
	}
	
}
