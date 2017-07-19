package com.chatroom.model;
import java.util.List;

public interface Chatroom_interface {
		public void insert(ChatroomVO chatroomVO);
		public void update(ChatroomVO chatroomVO);
		public void Delete(String chatromm_no);
		public ChatroomVO findByPrimaryKey(String chatromm_no);
		public List<ChatroomVO>getAll();

}
