package com.forum_board.model;

public class Forum_BoardVO implements java.io.Serializable{
		private String forum_no;
		private String forum_name;
		private String forum_desc;
		private String forum_sta;
		public String getForum_no() {
			return forum_no;
		}
		public void setForum_no(String forum_no) {
			this.forum_no = forum_no;
		}
		public String getForum_name() {
			return forum_name;
		}
		public void setForum_name(String forum_name) {
			this.forum_name = forum_name;
		}
		public String getForum_desc() {
			return forum_desc;
		}
		public void setForum_desc(String forum_desc) {
			this.forum_desc = forum_desc;
		}
		public String getForum_sta() {
			return forum_sta;
		}
		public void setForum_sta(String forum_sta) {
			this.forum_sta = forum_sta;
		}
}
