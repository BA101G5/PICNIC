package com.forum_board.model;
import java.util.List;

public interface Forum_BoardDAO_interface {
	 	public void insert (Forum_BoardVO forum_board);
	 	public void update (Forum_BoardVO forum_board);
	 	public void delete (String forum_no);
	 	public Forum_BoardVO findByPrimaryKey(String forum_no);
	 	public List<Forum_BoardVO> getAll();
}
