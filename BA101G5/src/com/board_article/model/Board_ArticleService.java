package com.board_article.model;

import java.sql.Timestamp;
import java.util.List;

public class Board_ArticleService {

	private Board_ArticleDAO_interface dao;

	public Board_ArticleService() {
		dao = new Board_ArticleDAO();
	}

	public Board_ArticleVO addBoard_Article(String author_no, String topic_no, String board_no, String article_title, String article_text, Timestamp article_post, Timestamp article_edit, Integer article_view, String article_sta, Integer article_kind, String article_pw) {

		Board_ArticleVO boardArticleVO = new Board_ArticleVO();

		boardArticleVO.setAuthor_no(author_no);
		boardArticleVO.setTopic_no(topic_no);
		boardArticleVO.setBoard_no(board_no);
		boardArticleVO.setArticle_title(article_title);
		boardArticleVO.setArticle_text(article_text);
		boardArticleVO.setArticle_post(article_post);
		boardArticleVO.setArticle_edit(article_edit);
		boardArticleVO.setArticle_view(article_view);
		boardArticleVO.setArticle_sta(article_sta);
		boardArticleVO.setArticle_kind(article_kind);
		boardArticleVO.setArticle_pw(article_pw);
		dao.insert(boardArticleVO);

		return boardArticleVO;
	}

	public Board_ArticleVO updateBoard_Article(String article_no, String author_no, String topic_no, String board_no, String article_title, String article_text, Timestamp article_post, Timestamp article_edit, Integer article_view, String article_sta, Integer article_kind, String article_pw) {

		Board_ArticleVO boardArticleVO = new Board_ArticleVO();

		boardArticleVO.setArticle_no(article_no);
		boardArticleVO.setAuthor_no(author_no);
		boardArticleVO.setTopic_no(topic_no);
		boardArticleVO.setBoard_no(board_no);
		boardArticleVO.setArticle_title(article_title);
		boardArticleVO.setArticle_text(article_text);
		boardArticleVO.setArticle_post(article_post);
		boardArticleVO.setArticle_edit(article_edit);
		boardArticleVO.setArticle_view(article_view);
		boardArticleVO.setArticle_sta(article_sta);
		boardArticleVO.setArticle_kind(article_kind);
		boardArticleVO.setArticle_pw(article_pw);
		dao.update(boardArticleVO);

		return boardArticleVO;
	}

	public void deleteBoard_Article(String article_no) {
		dao.delete(article_no);
	}

	public Board_ArticleVO getOneBoard_Article(String article_no) {
		return dao.findByPrimaryKey(article_no);
	}

	public List<Board_ArticleVO> getAll() {
		return dao.getAll();
	}
}
