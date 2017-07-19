package com.chatroom.model;

import java.sql.Timestamp;
import java.util.List;

public class ChatroomService {

	private ChatroomDAO_interface dao;
	
	public ChatroomService(){
		dao = new ChatroomDAO();
	}
	
	public ChatroomVO addChatroom(String chatroom_no,String chatroom_name,int chatroom_kind) {

		ChatroomVO chatroomVO = new ChatroomVO();
		chatroomVO.setChatroom_no(chatroom_no);
		chatroomVO.setChatroom_name(chatroom_name);
		chatroomVO.setChatroom_kind(chatroom_kind);

		dao.insert(chatroomVO);
		
		return chatroomVO;
	}

	public ChatroomVO updateChatroom(String chatroom_no,String chatroom_name,int chatroom_kind) {

		ChatroomVO chatroomVO = new ChatroomVO();
		chatroomVO.setChatroom_no(chatroom_no);
		chatroomVO.setChatroom_name(chatroom_name);
		chatroomVO.setChatroom_kind(chatroom_kind);
		dao.update(chatroomVO);		
		
		return chatroomVO;
	}
 
	public void deleteChatroom(String chatroom_no) {
		dao.delete(chatroom_no);
	}

	//  ¥Î picnic_no ¨Ó¦¬´M
	public ChatroomVO getOne(String chatroom_no) {
		return dao.findByPrimaryKey(chatroom_no);
	}

	public ChatroomVO getOneChatroom(String chatroom_no) {
		return dao.findByPrimaryKey(chatroom_no);
	}
	
	public List<ChatroomVO> getAll() {
		return dao.getAll();
	}		
}
