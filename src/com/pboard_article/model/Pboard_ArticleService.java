package com.pboard_article.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class Pboard_ArticleService {

	private Pboard_ArticleDAO_interface dao;

	public Pboard_ArticleService() {
		dao = new Pboard_ArticleDAO();
	}

	public Pboard_ArticleVO addPboard_Article(String author_no, String picnic_no, String article_title, String article_text, Timestamp article_post, Integer article_view, Integer article_kind) {

		Pboard_ArticleVO pboardArticleVO = new Pboard_ArticleVO();

		pboardArticleVO.setAuthor_no(author_no);
		pboardArticleVO.setPicnic_no(picnic_no);
		pboardArticleVO.setArticle_title(article_title);
		pboardArticleVO.setArticle_text(article_text);
		pboardArticleVO.setArticle_post(article_post);
		pboardArticleVO.setArticle_views(article_view);
		pboardArticleVO.setArticle_kind(article_kind);
		dao.insert(pboardArticleVO);

		return pboardArticleVO;
	}
	
	public Pboard_ArticleVO addPboard_Article(String author_no, String topic_no, String picnic_no, String article_title, String article_text, Timestamp article_post, Timestamp article_edit, Integer article_view, String article_sta, Integer article_kind, String article_pw) {

		Pboard_ArticleVO pboardArticleVO = new Pboard_ArticleVO();

		pboardArticleVO.setAuthor_no(author_no);
		pboardArticleVO.setTopic_no(topic_no);
		pboardArticleVO.setPicnic_no(picnic_no);
		pboardArticleVO.setArticle_title(article_title);
		pboardArticleVO.setArticle_text(article_text);
		pboardArticleVO.setArticle_post(article_post);
		pboardArticleVO.setArticle_edit(article_edit);
		pboardArticleVO.setArticle_views(article_view);
		pboardArticleVO.setArticle_sta(article_sta);
		pboardArticleVO.setArticle_kind(article_kind);
		pboardArticleVO.setArticle_pw(article_pw);
		dao.insert(pboardArticleVO);

		return pboardArticleVO;
	}
	
	public Pboard_ArticleVO updatePboard_Article(String article_no, String author_no, String picnic_no, String article_title, String article_text, Timestamp article_post, Integer article_view, Integer article_kind) {

		Pboard_ArticleVO pboardArticleVO = new Pboard_ArticleVO();

		pboardArticleVO.setArticle_no(article_no);
		pboardArticleVO.setAuthor_no(author_no);
		pboardArticleVO.setPicnic_no(picnic_no);
		pboardArticleVO.setArticle_title(article_title);
		pboardArticleVO.setArticle_text(article_text);
		pboardArticleVO.setArticle_post(article_post);
		pboardArticleVO.setArticle_views(article_view);
		pboardArticleVO.setArticle_kind(article_kind);
		dao.update(pboardArticleVO);

		return pboardArticleVO;
	}

	public Pboard_ArticleVO updatePboard_Article(String article_no, String author_no, String topic_no, String picnic_no, String article_title, String article_text, Timestamp article_post, Timestamp article_edit, Integer article_view, String article_sta, Integer article_kind, String article_pw) {

		Pboard_ArticleVO pboardArticleVO = new Pboard_ArticleVO();

		pboardArticleVO.setArticle_no(article_no);
		pboardArticleVO.setAuthor_no(author_no);
		pboardArticleVO.setTopic_no(topic_no);
		pboardArticleVO.setPicnic_no(picnic_no);
		pboardArticleVO.setArticle_title(article_title);
		pboardArticleVO.setArticle_text(article_text);
		pboardArticleVO.setArticle_post(article_post);
		pboardArticleVO.setArticle_edit(article_edit);
		pboardArticleVO.setArticle_views(article_view);
		pboardArticleVO.setArticle_sta(article_sta);
		pboardArticleVO.setArticle_kind(article_kind);
		pboardArticleVO.setArticle_pw(article_pw);
		dao.update(pboardArticleVO);

		return pboardArticleVO;
	}

	public void deletePboard_Article(String article_no) {
		dao.delete(article_no);
	}

	public Pboard_ArticleVO getOnePboard_Article(String article_no) {
		return dao.findByPrimaryKey(article_no);
	}

	public List<Pboard_ArticleVO> getAll() {
		return dao.getAll();
	}
	
	public List<Pboard_ArticleVO> getAll(String picnic_no) {
		return dao.getAll(picnic_no);
	}

	public Object getPboard_ArticlesByGeneralMemberno(String mem_no) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pboard_ArticleVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	//------------------------------------------------------
	public List<Pboard_ArticleVO> k_getPboard(String search_string) {
		return dao.k_research(search_string);
	}
	public String k_getPboardName(String article_no){
		return dao.k_get_search_name(article_no);
	}
}
