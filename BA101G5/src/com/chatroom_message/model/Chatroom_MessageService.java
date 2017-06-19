package com.chatroom_message.model;

import java.util.List;


public class Chatroom_MessageService {
	private Chatroom_MessageDAO_interface dao;
	
	public Chatroom_MessageService(){
		dao=new Chatroom_MessageDAO();
	}
	
	public Chatroom_MessageVO addChatroom_Message(String cr_msg_no, String chatroom_no, String mem_no, java.sql.Timestamp message_date, String message_text, byte[] message_img) {

		Chatroom_MessageVO chatroom_MessageVO = new Chatroom_MessageVO();

		chatroom_MessageVO.setCr_msg_no(cr_msg_no);
		chatroom_MessageVO.setChatroom_no(chatroom_no);
		chatroom_MessageVO.setMem_no(mem_no);
		chatroom_MessageVO.setMessage_date(message_date);
		chatroom_MessageVO.setMessage_text(message_text);
		chatroom_MessageVO.setMessage_img(message_img);
		
		dao.insert(chatroom_MessageVO);

		return chatroom_MessageVO;
	}

	public Chatroom_MessageVO updateChatroom_Message(String cr_msg_no, String chatroom_no, String mem_no, java.sql.Timestamp message_date, String message_text, byte[] message_img) {

		Chatroom_MessageVO chatroom_MessageVO = new Chatroom_MessageVO();

		chatroom_MessageVO.setCr_msg_no(cr_msg_no);
		chatroom_MessageVO.setChatroom_no(chatroom_no);
		chatroom_MessageVO.setMem_no(mem_no);
		chatroom_MessageVO.setMessage_date(message_date);
		chatroom_MessageVO.setMessage_text(message_text);
		chatroom_MessageVO.setMessage_img(message_img);
		
		dao.update(chatroom_MessageVO);

		return chatroom_MessageVO;
	}

	public void deleteChatroom_Message(String cr_msg_no) {
		dao.delete(cr_msg_no);
	}

	public Chatroom_MessageVO getOneEmp(String cr_msg_no) {
		return dao.findByPrimaryKey(cr_msg_no);
	}

	public List<Chatroom_MessageVO> getAll() {
		return dao.getAll();
	}
	
}
