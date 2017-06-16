package com.board_article.model;
import java.util.List;

public interface Board_ArticleDAO_interface {
		public void insert(Board_ArticleVO board_articleVO);
		public void update(Board_ArticleVO board_articleVO);
		public void delete(String article_no);
		public Board_ArticleVO findByPrimaryKey(String article_no);
		public List<Board_ArticleVO> getAll();
}
