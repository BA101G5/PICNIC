package com.board_article.model;
import java.sql.Date;

public class Board_ArticleVO implements java.io.Serializable {
		private String article_no;
		private String author_no;
		private String topic_no;
		private String board_no;
		private String article_title;
		private String article_text;
		private Date article_post;
		private Date article_edit;
		private Integer article_view;
		private String article_sta;
		private Integer article_kind;
		private String article_pw;
		public String getArticle_no() {
			return article_no;
		}
		public void setArticle_no(String article_no) {
			this.article_no = article_no;
		}
		public String getAuthor_no() {
			return author_no;
		}
		public void setAuthor_no(String author_no) {
			this.author_no = author_no;
		}
		public String getTopic_no() {
			return topic_no;
		}
		public void setTopic_no(String topic_no) {
			this.topic_no = topic_no;
		}
		public String getBoard_no() {
			return board_no;
		}
		public void setBoard_no(String board_no) {
			this.board_no = board_no;
		}
		public String getArticle_title() {
			return article_title;
		}
		public void setArticle_title(String article_title) {
			this.article_title = article_title;
		}
		public String getArticle_text() {
			return article_text;
		}
		public void setArticle_text(String article_text) {
			this.article_text = article_text;
		}
		public Date getArticle_post() {
			return article_post;
		}
		public void setArticle_post(Date article_post) {
			this.article_post = article_post;
		}
		public Date getArticle_edit() {
			return article_edit;
		}
		public void setArticle_edit(Date article_edit) {
			this.article_edit = article_edit;
		}
		public Integer getArticle_view() {
			return article_view;
		}
		public void setArticle_view(Integer article_view) {
			this.article_view = article_view;
		}
		public String getArticle_sta() {
			return article_sta;
		}
		public void setArticle_sta(String article_sta) {
			this.article_sta = article_sta;
		}
		public Integer getArticle_kind() {
			return article_kind;
		}
		public void setArticle_kind(Integer article_kind) {
			this.article_kind = article_kind;
		}
		public String getArticle_pw() {
			return article_pw;
		}
		public void setArticle_pw(String article_pw) {
			this.article_pw = article_pw;
		}
}
