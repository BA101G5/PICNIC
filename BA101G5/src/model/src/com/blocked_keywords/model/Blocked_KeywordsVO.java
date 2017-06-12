package com.blocked_keywords.model;

public class Blocked_KeywordsVO implements java.io.Serializable{
		private String keyword_no;
		private String keyword;
		private String replacement;
		public String getKeyword_no() {
			return keyword_no;
		}
		public void setKeyword_no(String keyword_no) {
			this.keyword_no = keyword_no;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public String getReplacement() {
			return replacement;
		}
		public void setReplacement(String replacement) {
			this.replacement = replacement;
		}

}
