package com.chatroom.model;

import java.util.*;


public interface ChatroomDAO_interface {
	public void insert(ChatroomVO chatroomVo);
	public void update(ChatroomVO chatroomVo);
	public void delete(String chatroom_no);
	public ChatroomVO findByPrimaryKey(String chatroom_no);
	public List<ChatroomVO> getAll();	
}
