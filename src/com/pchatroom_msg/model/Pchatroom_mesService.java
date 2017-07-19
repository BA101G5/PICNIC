package com.pchatroom_msg.model;

import java.sql.Timestamp;
import java.util.List;

public class Pchatroom_mesService {
	
	private Pchatroom_mesDAO_interface dao;
	
	public Pchatroom_mesService(){
		dao = new Pchatroom_mesDAO();
	}
	
	public Pchatroom_mesVO addPchatroom_mes(String crmsg_no, String picnic_no, String mem_no, java.sql.Timestamp message_date,
			String message_text, byte[] message_img) {

		Pchatroom_mesVO pchatroom_mesVO = new Pchatroom_mesVO();
		pchatroom_mesVO.setPicnic_no(picnic_no);
		pchatroom_mesVO.setMem_no(mem_no);
		pchatroom_mesVO.setMessage_date(message_date);
		pchatroom_mesVO.setMessage_text(message_text);
		pchatroom_mesVO.setMessage_img(message_img);
		dao.insert(pchatroom_mesVO);
		
		return pchatroom_mesVO;
	}

	public Pchatroom_mesVO updatePchatroom_mes(String crmsg_no, String picnic_no, String mem_no,
			Timestamp message_date, String message_text, byte[] message_img) {

		Pchatroom_mesVO pchatroom_mesVO = new Pchatroom_mesVO();

		pchatroom_mesVO.setCrmsg_no(crmsg_no);
		pchatroom_mesVO.setPicnic_no(picnic_no);
		pchatroom_mesVO.setMem_no(mem_no);
		pchatroom_mesVO.setMessage_date(message_date);
		pchatroom_mesVO.setMessage_text(message_text);
		pchatroom_mesVO.setMessage_img(message_img);
		dao.update(pchatroom_mesVO);		
		
		return pchatroom_mesVO;
	}
 
//   �ק�  ���\�Ϊ���ѫǰT���O�� �n�����έק�
//	public void deleteEmp(Integer empno) {
//		dao.delete(empno);
//	}

	//  �� picnic_no �Ӧ��M
	public Pchatroom_mesVO getOne(String picnic_no) {
		return dao.findByPrimaryKey(picnic_no);
	}

	public List<Pchatroom_mesVO> getAll() {
		return dao.getAll();
	}	
}
