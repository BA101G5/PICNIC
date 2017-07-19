package com.chatroom_message.model;

import java.util.List;


public interface Chatroom_MessageDAO_interface {
    public void insert(Chatroom_MessageVO chatroom_MessageVO);
    public void update(Chatroom_MessageVO chatroom_MessageVO);
    public void delete(String cr_msg_no);
    public Chatroom_MessageVO findByPrimaryKey(String cr_msg_no);
    public List<Chatroom_MessageVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
