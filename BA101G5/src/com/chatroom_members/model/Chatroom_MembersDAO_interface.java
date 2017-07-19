package com.chatroom_members.model;

import java.util.List;


public interface Chatroom_MembersDAO_interface {
    public void insert(Chatroom_MembersVO empVO);
    public void update(Chatroom_MembersVO empVO);
    public void delete(String chatroom_no, String mem_no);
    public List<Chatroom_MembersVO> findByPrimaryKey(String chatroom_no,String mem_no);
    public List<Chatroom_MembersVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//    public List<Chatroom_MembersVO> getAll(Map<String, String[]> map); 
    public List<Chatroom_MembersVO2> getAllwCond();
    public Chatroom_MembersVO2 getOnewCond(String mem_no, String mem_no2);

}
