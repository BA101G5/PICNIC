package com.pchatroom_msg.model;

import java.util.*;

public interface Pchatroom_mesDAO_interface {
	public void insert(Pchatroom_mesVO pchatroom_MesVO);
	public void update(Pchatroom_mesVO pchatroom_MesVO);
	public void delete(String crmsg_no);
	public Pchatroom_mesVO findByPrimaryKey(String crmsg_no);
	public List<Pchatroom_mesVO> getAll();
}
