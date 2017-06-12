package com.chatroom_message.model;
import java.util.List;

public interface Chatroom_Message_interface {
    	public void insert(Chatroom_MessageVO chatroom_MessageVO);
    	public void update(Chatroom_MessageVO chatroom_MessageVO);
    	public void delete(String cr_msg_no);
    	public Chatroom_MessageVO findByPrimaryKey(String cr_msg_no);
    	public List<Chatroom_MessageVO> getAll();
}
