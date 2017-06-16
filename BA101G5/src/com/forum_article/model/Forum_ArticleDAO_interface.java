package com.forum_article.model;
import java.util.List;

public interface Forum_ArticleDAO_interface {
	 public void insert (Forum_ArticleVO forum_article);
	 public void update (Forum_ArticleVO forum_article);
	 public void delete (String article_no);
	 public Forum_ArticleVO findByPrimaryKey(String article_no);
	 public List<Forum_ArticleVO>getAll();
}
