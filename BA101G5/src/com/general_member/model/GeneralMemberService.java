package com.general_member.model;

import java.sql.Date;
import java.util.List;

public class GeneralMemberService {
	GeneralMemberDAO_interface dao;

	public GeneralMemberService() {
		dao = new GeneralMemberDAO();
	}

	public GeneralMemberVO addGeneralMember(String MEM_NAME, Character MEM_GEN, Date MEM_BIRTH,
			String MEM_ADDR, String MEM_MAIL, String MEM_PSW, String MEM_SELF, byte[] MEM_PIC, Integer MEM_COIN,
			Character MEM_STA, String MEM_PHONE, Character MEM_PBOARD) {
		GeneralMemberVO gVO = new GeneralMemberVO();


		gVO.setMEM_GEN(MEM_GEN);
		gVO.setMEM_NAME(MEM_NAME);
		gVO.setMEM_BIRTH(MEM_BIRTH);
		gVO.setMEM_ADDR(MEM_ADDR);
		gVO.setMEM_MAIL(MEM_MAIL);
		gVO.setMEM_PSW(MEM_PSW);
		gVO.setMEM_SELF(MEM_SELF);
		gVO.setMEM_PIC(MEM_PIC);
		gVO.setMEM_COIN(MEM_COIN);
		gVO.setMEM_STA(MEM_STA);
		gVO.setMEM_PHONE(MEM_PHONE);
		gVO.setMEM_PBOARD(MEM_PBOARD);
		dao.insert(gVO);
		return gVO;
	}

	public GeneralMemberVO updateGeneralMember(String MEM_NO, String MEM_NAME, Character MEM_GEN, Date MEM_BIRTH,
			String MEM_ADDR, String MEM_MAIL, String MEM_PSW, String MEM_SELF, byte[] MEM_PIC, Integer MEM_COIN,
			Character MEM_STA, String MEM_PHONE, Character MEM_PBOARD) {
		GeneralMemberVO gVO = new GeneralMemberVO();
		
		gVO.setMEM_NO(MEM_NO);
		gVO.setMEM_GEN(MEM_GEN);
		gVO.setMEM_NAME(MEM_NAME);
		gVO.setMEM_BIRTH(MEM_BIRTH);
		gVO.setMEM_ADDR(MEM_ADDR);
		gVO.setMEM_MAIL(MEM_MAIL);
		gVO.setMEM_PSW(MEM_PSW);
		gVO.setMEM_SELF(MEM_SELF);
		gVO.setMEM_PIC(MEM_PIC);
		gVO.setMEM_COIN(MEM_COIN);
		gVO.setMEM_STA(MEM_STA);
		gVO.setMEM_PHONE(MEM_PHONE);
		gVO.setMEM_PBOARD(MEM_PBOARD);
		dao.update(gVO);

		return gVO;
	}
	

	public void deleteGeneralMember(String MEM_NO) {
		dao.delete(MEM_NO);
	}

	public GeneralMemberVO getOneGeneralMember(String MEM_NO) {
		return dao.findByPrimaryKey(MEM_NO);
	}

	public List<GeneralMemberVO> getAll() {
		return dao.getAll();
	}
	
	public void updatecoin(String MEM_NO,Integer MEM_COIN)
			 {
		GeneralMemberVO gVO = new GeneralMemberVO();
		
		gVO.setMEM_NO(MEM_NO);
		
		gVO.setMEM_COIN(MEM_COIN);
		
		dao.updatefromcoin(gVO);

		
	}
}
