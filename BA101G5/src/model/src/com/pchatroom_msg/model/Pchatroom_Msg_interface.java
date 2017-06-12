package com.pchatroom_msg.model;
import java.util.List;

public interface Pchatroom_Msg_interface {
		public void insert(Pchatroom_MsgVO pchatroom_msgVO);
		public void update(Pchatroom_MsgVO pchatroom_msgVO);
		public void delete(String pcrmsg_no);
		public Pchatroom_MsgVO findByPrimaryKey(String pcrmsg_no);
		public List<Pchatroom_MsgVO> getAll();
}
