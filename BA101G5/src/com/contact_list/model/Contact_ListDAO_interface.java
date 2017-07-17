package com.contact_list.model;
import java.util.List;

public interface Contact_ListDAO_interface {
		public void insert(Contact_ListVO contact_listVO);
		public void update(Contact_ListVO contact_listVO);
		public void delete(String mem_no,String contact_no);
		public List<Contact_ListVO> findByPrimaryKey(String mem_no,String contact_no);
		public List<Contact_ListVO>getAll();
		public List<Contact_ListVO> getAll(String mem_no, String relationship);
}
