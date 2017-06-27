package com.forum_board.model;

import java.util.List;

public class Forum_BoardService {

	private Forum_BoardDAO_interface dao;

	public Forum_BoardService() {
		dao = new Forum_BoardDAO();
	}

	public Forum_BoardVO addForum_Board(String forum_name, String forum_desc, String forum_sta) {

		Forum_BoardVO forumBoardVO = new Forum_BoardVO();

		forumBoardVO.setForum_name(forum_name);
		forumBoardVO.setForum_desc(forum_desc);
		forumBoardVO.setForum_sta(forum_sta);
		dao.insert(forumBoardVO);

		return forumBoardVO;
	}

	public Forum_BoardVO updateForum_Board(String forum_no, String forum_name, String forum_desc, String forum_sta) {

		Forum_BoardVO forumBoardVO = new Forum_BoardVO();

		forumBoardVO.setForum_no(forum_no);
		forumBoardVO.setForum_name(forum_name);
		forumBoardVO.setForum_desc(forum_desc);
		forumBoardVO.setForum_sta(forum_sta);
		dao.update(forumBoardVO);

		return forumBoardVO;
	}

	public void deleteForum_Board(String forum_no) {
		dao.delete(forum_no);
	}

	public Forum_BoardVO getOneForum_Board(String forum_no) {
		return dao.findByPrimaryKey(forum_no);
	}

	public List<Forum_BoardVO> getAll() {
		return dao.getAll();
	}
}
