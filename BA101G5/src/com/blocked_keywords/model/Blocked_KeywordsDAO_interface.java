package com.blocked_keywords.model;
import java.util.List;

public interface Blocked_KeywordsDAO_interface {
	public void insert (Blocked_KeywordsVO blocked_keywordVO);
 	public void update (Blocked_KeywordsVO blocked_keywordVO);
 	public void delete (String keyword_no);
 	public Blocked_KeywordsVO findByPrimaryKey(String keyword_no);
 	public List<Blocked_KeywordsVO> getAll();
}
