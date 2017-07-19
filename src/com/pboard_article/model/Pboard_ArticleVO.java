package com.pboard_article.model;
import java.sql.Timestamp;

public class Pboard_ArticleVO implements java.io.Serializable{
	private String article_no;
	private String author_no;
	private String topic_no;
	private String picnic_no;
	private String article_title;
	private String article_text;
	private Timestamp article_post;
	private Timestamp article_edit;
	private Integer article_views;
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
	public String getPicnic_no() {
		return picnic_no;
	}
	public void setPicnic_no(String picnic_no) {
		this.picnic_no = picnic_no;
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
	public Timestamp getArticle_post() {
		return article_post;
	}
	public void setArticle_post(Timestamp article_post) {
		this.article_post = article_post;
	}
	public Timestamp getArticle_edit() {
		return article_edit;
	}
	public void setArticle_edit(Timestamp article_edit) {
		this.article_edit = article_edit;
	}
	public Integer getArticle_views() {
		return article_views;
	}
	public void setArticle_views(Integer article_views) {
		this.article_views = article_views;
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
