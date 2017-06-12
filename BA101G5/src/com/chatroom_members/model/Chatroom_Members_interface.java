package com.chatroom_members.model;
import java.util.List;

public interface Chatroom_Members_interface {
		public void insert(Chatroom_MembersVO chatroom_membersVO);
		public void update(Chatroom_MembersVO chatroom_membersVO);
		public void delete(String chatroom_no,String mem_no);
		public Chatroom_MembersVO findByPrimaryKey(String chatroom_no);
		public List<Chatroom_MembersVO>getAll();

}
