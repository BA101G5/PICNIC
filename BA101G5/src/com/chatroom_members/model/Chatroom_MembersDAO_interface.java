package com.chatroom_members.model;

import java.util.List;


public interface Chatroom_MembersDAO_interface {
    public void insert(Chatroom_MembersVO empVO);
    public void update(Chatroom_MembersVO empVO);
    public void delete(String chatroom_no,String mem_no);
    public List<Chatroom_MembersVO> findByPrimaryKey(String chatroom_no,String mem_no);
    public List<Chatroom_MembersVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//    public List<Chatroom_MembersVO> getAll(Map<String, String[]> map); 

}
