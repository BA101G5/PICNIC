package com.chatroom_members.model;
import java.sql.Date;

public class Chatroom_MembersVO implements java.io.Serializable {
		private String chatroom_no;
		private String mem_no;
		private String chatroom_role;
		private Date   ban_until;
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
		public String getChatroom_role() {
			return chatroom_role;
		}
		public void setChatroom_role(String chatroom_role) {
			this.chatroom_role = chatroom_role;
		}
		public Date getBan_until() {
			return ban_until;
		}
		public void setBan_until(Date ban_until) {
			this.ban_until = ban_until;
		}
}
