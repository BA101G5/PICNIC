package com.pboard_article.model;
import java.util.List;

public interface Pboard_Article_interface {
		public void insert(Pboard_ArticleVO pboard_articleVO);
		public void update(Pboard_ArticleVO pboard_articleVO);
		public void delete(String article_no);
		public Pboard_ArticleVO findByPrimaryKey(String article_no);
		public List<Pboard_ArticleVO> getAll();

}
