package com.general_member.model;
import java.util.List;

public interface General_Member_interface {
		public void insert (General_MemberVO general_memberVO);
		public void update (General_MemberVO general_memberVO);
		public void delete (String mem_no);
		public General_MemberVO findByPrimaryKey(String mem_no);
		public List<General_MemberVO>getAll();
}
