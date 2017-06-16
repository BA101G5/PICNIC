package com.interval_letter.model;
import java.sql.Date;
import java.sql.Timestamp;

public class Interval_LetterVO implements java.io.Serializable{
		private String letter_no;
		private String sender_no;
		private String recipient_no;
//		private Date letter_date;
		private Timestamp letter_date;
		private String letter_text;
		private String letter_title;
		private String letter_sta;
		public String getLetter_no() {
			return letter_no;
		}
		public void setLetter_no(String letter_no) {
			this.letter_no = letter_no;
		}
		public String getSender_no() {
			return sender_no;
		}
		public void setSender_no(String sender_no) {
			this.sender_no = sender_no;
		}
		public String getRecipient_no() {
			return recipient_no;
		}
		public void setRecipient_no(String recipient_no) {
			this.recipient_no = recipient_no;
		}
//		public Date getLetter_date() {
		public Timestamp getLetter_date() {
			return letter_date;
		}
//		public void setLetter_date(Date letter_date) {
		public void setLetter_date(Timestamp letter_date) {
			this.letter_date = letter_date;
		}
		public String getLetter_text() {
			return letter_text;
		}
		public void setLetter_text(String letter_text) {
			this.letter_text = letter_text;
		}
		public String getLetter_title() {
			return letter_title;
		}
		public void setLetter_title(String letter_title) {
			this.letter_title = letter_title;
		}
		public String getLetter_sta() {
			return letter_sta;
		}
		public void setLetter_sta(String letter_sta) {
			this.letter_sta = letter_sta;
		}
}
