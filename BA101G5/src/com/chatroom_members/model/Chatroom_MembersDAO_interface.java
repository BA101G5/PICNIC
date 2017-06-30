package com.chatroom_members.model;

import java.util.List;


public interface Chatroom_MembersDAO_interface {
    public void insert(Chatroom_MembersVO empVO);
    public void update(Chatroom_MembersVO empVO);
    public void delete(String chatroom_no,String mem_no);
    public Chatroom_MembersVO findByPrimaryKey(String chatroom_no,String mem_no);
    public List<Chatroom_MembersVO> getAll();
    public List<Chatroom_MembersVO> getAllpk();
    public List<Chatroom_MembersVO> findByCrno(String chatroom_no);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//    public List<Chatroom_MembersVO> getAll(Map<String, String[]> map); 

}
