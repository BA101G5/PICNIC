package com.general_member.model;

import java.util.List;



public interface GeneralMemberDAO_interface {
	public void insert(GeneralMemberVO generalmemberVO);
    public void update(GeneralMemberVO generalmemberVO);
    public void delete(String MEM_NO);
    public GeneralMemberVO findByPrimaryKey(String MEM_NO);
    public List<GeneralMemberVO> getAll();
}
