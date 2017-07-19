package com.chatroom.model;

public class ChatroomInfo implements java.io.Serializable {
	private String chatroom_no;
	private String chatroom_name;
	private Integer chatroom_kind;
	public String getChatroom_no() {
		return chatroom_no;
	}
	public void setChatroom_no(String chatroom_no) {
		this.chatroom_no = chatroom_no;
	}
	public String getChatroom_name() {
		return chatroom_name;
	}
	public void setChatroom_name(String chatroom_name) {
		this.chatroom_name = chatroom_name;
	}
	public Integer getChatroom_kind() {
		return chatroom_kind;
	}
	public void setChatroom_kind(Integer chatroom_kind) {
		this.chatroom_kind = chatroom_kind;
	}
}
