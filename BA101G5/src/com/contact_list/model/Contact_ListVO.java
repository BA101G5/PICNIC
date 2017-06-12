package com.contact_list.model;

public class Contact_ListVO implements java.io.Serializable {
		private String mem_no;
		private String contact_no;
		private String relationship;
		public String getMem_no() {
			return mem_no;
		}
		public void setMem_no(String mem_no) {
			this.mem_no = mem_no;
		}
		public String getContact_no() {
			return contact_no;
		}
		public void setContact_no(String contact_no) {
			this.contact_no = contact_no;
		}
		public String getRelationship() {
			return relationship;
		}
		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}
}
