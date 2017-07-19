package com.chatroom_members.model;

import java.util.List;


public class Chatroom_MembersService {
	private Chatroom_MembersDAO_interface dao;
	
	public Chatroom_MembersService(){
		dao=new Chatroom_MembersDAO();
	}
	
	public Chatroom_MembersVO addChatroom_Members(String chatroom_no, String mem_no, Character chatroom_role, java.sql.Timestamp ban_until) {

		Chatroom_MembersVO chatroom_MembersVO = new Chatroom_MembersVO();

		chatroom_MembersVO.setChatroom_no(chatroom_no);
		chatroom_MembersVO.setMem_no(mem_no);
		chatroom_MembersVO.setChatroom_role(chatroom_role);
		chatroom_MembersVO.setBan_until(ban_until);

		dao.insert(chatroom_MembersVO);

		return chatroom_MembersVO;
	}

	public Chatroom_MembersVO updateChatroom_Members(String chatroom_no, String mem_no, Character chatroom_role, java.sql.Timestamp ban_until) {

		Chatroom_MembersVO chatroom_MembersVO = new Chatroom_MembersVO();

		chatroom_MembersVO.setChatroom_no(chatroom_no);
		chatroom_MembersVO.setMem_no(mem_no);
		chatroom_MembersVO.setChatroom_role(chatroom_role);
		chatroom_MembersVO.setBan_until(ban_until);
		dao.update(chatroom_MembersVO);

		return chatroom_MembersVO;
	}

	public void deleteChatroom_Members(String chatroom_no,String mem_no) {
		dao.delete(chatroom_no,mem_no);
	}

	public List<Chatroom_MembersVO> getOneEmp(String chatroom_no,String mem_no) {
		return dao.findByPrimaryKey(chatroom_no, mem_no);
	}

	public List<Chatroom_MembersVO> getAll() {
		return dao.getAll();
	}
	
	public List<Chatroom_MembersVO2> getAllwCond() {
		return dao.getAllwCond();
	}
	
	public Chatroom_MembersVO2 getOnewCond(String mem_no, String mem_no2) {
		return dao.getOnewCond(mem_no, mem_no2);
	}
}
