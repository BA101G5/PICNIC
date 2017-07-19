package com.contact_list.model;

import java.util.List;

public class Contact_ListService {

	private Contact_ListDAO_interface dao;

	public Contact_ListService() {
		dao = new Contact_ListDAO();
	}

	public Contact_ListVO addContact_List(String contact_no, String relationship) {

		Contact_ListVO contactListVO = new Contact_ListVO();

		contactListVO.setContact_no(contact_no);
		contactListVO.setRelationship(relationship);
		dao.insert(contactListVO);

		return contactListVO;
	}

	public Contact_ListVO updateContact_List(String mem_no, String contact_no, String relationship) {

		Contact_ListVO contactListVO = new Contact_ListVO();

		contactListVO.setMem_no(mem_no);
		contactListVO.setContact_no(contact_no);
		contactListVO.setRelationship(relationship);
		dao.update(contactListVO);

		return contactListVO;
	}

	public void deleteContact_List(String mem_no, String contact_no) {
		dao.delete(mem_no, contact_no);
	}

	public List<Contact_ListVO> getOneContact_List(String mem_no, String contact_no) {
		return dao.findByPrimaryKey(mem_no, contact_no);
	}

	public List<Contact_ListVO> getAll() {
		return dao.getAll();
	}
	
	public List<Contact_ListVO> getAll(String mem_no, String relationship) {
		return dao.getAll(mem_no, relationship);
	}
}
