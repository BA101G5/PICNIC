package com.general_member.model;

import java.util.List;



public interface GeneralMemberDAO_interface {
	public void insert(GeneralMemberVO generalmemberVO);
    public void update(GeneralMemberVO generalmemberVO);
    public void delete(String MEM_NO);
    public GeneralMemberVO findByPrimaryKey(String MEM_NO);
    public List<GeneralMemberVO> getAll();
    public void updatefromcoin(GeneralMemberVO generalmemberVO);
    public void updateforSTA(GeneralMemberVO generalmemberVO);
    //--------------------------------------------------------
    public void k_insert(GeneralMemberVO generalmemberVO);
    public void k_update(GeneralMemberVO generalmemberVO);
    public void k_delete(String MEM_NO);
    public GeneralMemberVO k_findByPrimaryKey(String MEM_NO);
    public List<GeneralMemberVO> k_getAll();

}
