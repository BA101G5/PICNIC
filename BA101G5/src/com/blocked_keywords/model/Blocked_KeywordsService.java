package com.blocked_keywords.model;

import java.util.List;

public class Blocked_KeywordsService {

	private Blocked_KeywordsDAO_interface dao;

	public Blocked_KeywordsService() {
		dao = new Blocked_KeywordsDAO();
	}

	Blocked_KeywordsVO addBlocked_Keywords(String keyword, String replacement) {

		Blocked_KeywordsVO blockedKeywordsVO = new Blocked_KeywordsVO();

		blockedKeywordsVO.setKeyword(keyword);
		blockedKeywordsVO.setReplacement(replacement);
		dao.insert(blockedKeywordsVO);

		return blockedKeywordsVO;
	}

	public Blocked_KeywordsVO updateBlocked_Keywords(String keyword_no, String keyword, String replacement) {

		Blocked_KeywordsVO blockedKeywordsVO = new Blocked_KeywordsVO();

		blockedKeywordsVO.setKeyword_no(keyword_no);
		blockedKeywordsVO.setKeyword(keyword);
		blockedKeywordsVO.setReplacement(replacement);
		dao.update(blockedKeywordsVO);

		return blockedKeywordsVO;
	}

	public void deleteBlocked_Keywords(String ann_no) {
		dao.delete(ann_no);
	}

	public Blocked_KeywordsVO getOneBlocked_Keywords(String ann_no) {
		return dao.findByPrimaryKey(ann_no);
	}

	public List<Blocked_KeywordsVO> getAll() {
		return dao.getAll();
	}
}

