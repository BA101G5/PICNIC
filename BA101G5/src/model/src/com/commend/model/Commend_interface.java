package com.commend.model;
import java.util.List;

public interface Commend_interface {
		public void insert(CommendVO commendVO);
		public void update(CommendVO commendVO);
		public void delete(String comm_memno,String comm_be_no);
		public CommendVO findByPrimaryKey(String comm_memno,String comm_be_no);
		public List<CommendVO> getAll();
		

}
