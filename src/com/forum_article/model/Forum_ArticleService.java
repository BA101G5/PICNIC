package com.forum_article.model;

import java.sql.Timestamp;
import java.util.List;

public class Forum_ArticleService {

	private Forum_ArticleDAO_interface dao;

	public Forum_ArticleService() {
		dao = new Forum_ArticleDAO();
	}

	public Forum_ArticleVO addForum_Article(String author_no, String topic_no, String forum_no, String article_title, String article_text, Timestamp article_post, Timestamp article_edit, Integer article_view, String article_sta, Integer article_kind, String article_pw) {

		Forum_ArticleVO forumArticleVO = new Forum_ArticleVO();

		forumArticleVO.setAuthor_no(author_no);
		forumArticleVO.setTopic_no(topic_no);
		forumArticleVO.setForum_no(forum_no);
		forumArticleVO.setArticle_title(article_title);
		forumArticleVO.setArticle_text(article_text);
		forumArticleVO.setArticle_post(article_post);
		forumArticleVO.setArticle_edit(article_edit);
		forumArticleVO.setArticle_view(article_view);
		forumArticleVO.setArticle_sta(article_sta);
		forumArticleVO.setArticle_kind(article_kind);
		forumArticleVO.setArticle_pw(article_pw);
		dao.insert(forumArticleVO);

		return forumArticleVO;
	}

	public Forum_ArticleVO updateForum_Article(String article_no, String author_no, String topic_no, String forum_no, String article_title, String article_text, Timestamp article_post, Timestamp article_edit, Integer article_view, String article_sta, Integer article_kind, String article_pw) {

		Forum_ArticleVO forumArticleVO = new Forum_ArticleVO();

		forumArticleVO.setArticle_no(article_no);
		forumArticleVO.setAuthor_no(author_no);
		forumArticleVO.setTopic_no(topic_no);
		forumArticleVO.setForum_no(forum_no);
		forumArticleVO.setArticle_title(article_title);
		forumArticleVO.setArticle_text(article_text);
		forumArticleVO.setArticle_post(article_post);
		forumArticleVO.setArticle_edit(article_edit);
		forumArticleVO.setArticle_view(article_view);
		forumArticleVO.setArticle_sta(article_sta);
		forumArticleVO.setArticle_kind(article_kind);
		forumArticleVO.setArticle_pw(article_pw);
		dao.update(forumArticleVO);

		return forumArticleVO;
	}

	public void deleteForum_Article(String article_no) {
		dao.delete(article_no);
	}

	public Forum_ArticleVO getOneForum_Article(String article_no) {
		return dao.findByPrimaryKey(article_no);
	}

	public List<Forum_ArticleVO> getAll() {
		return dao.getAll();
	}
}
