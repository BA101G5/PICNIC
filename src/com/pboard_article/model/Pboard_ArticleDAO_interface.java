package com.pboard_article.model;
import java.util.List;

public interface Pboard_ArticleDAO_interface {
		public void insert(Pboard_ArticleVO pboard_articleVO);
		public void update(Pboard_ArticleVO pboard_articleVO);
		public void delete(String article_no);
		public Pboard_ArticleVO findByPrimaryKey(String article_no);
		public List<Pboard_ArticleVO> getAll();
		public List<Pboard_ArticleVO> getAll(String picnic_no);
		//------------------------------------------------------------
				public List<Pboard_ArticleVO> k_research(String search_string);
				public String k_get_search_name(String article_no);

}
