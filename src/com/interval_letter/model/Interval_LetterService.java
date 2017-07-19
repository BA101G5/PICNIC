package com.interval_letter.model;

import java.util.List;

public class Interval_LetterService {

	private Interval_LetterDAO_interface dao;

	public Interval_LetterService() {
		dao = new Interval_LetterDAO();
	}

	public Interval_LetterVO addInterval_Letter(String sender_no, String recipient_no) {

		Interval_LetterVO intervalLetterVO = new Interval_LetterVO();

		intervalLetterVO.setSender_no(sender_no);
		intervalLetterVO.setRecipient_no(recipient_no);
		dao.insert(intervalLetterVO);

		return intervalLetterVO;
	}

	public Interval_LetterVO updateInterval_Letter(String letter_no, String sender_no, String recipient_no) {

		Interval_LetterVO intervalLetterVO = new Interval_LetterVO();

		intervalLetterVO.setLetter_no(letter_no);
		intervalLetterVO.setSender_no(sender_no);
		intervalLetterVO.setRecipient_no(recipient_no);
		dao.update(intervalLetterVO);

		return intervalLetterVO;
	}

	public void deleteInterval_Letter(String letter_no) {
		dao.delete(letter_no);
	}

	public Interval_LetterVO getOneInterval_Letter(String letter_no) {
		return dao.findByPrimaryKey(letter_no);
	}

	public List<Interval_LetterVO> getAll() {
		return dao.getAll();
	}
}
