package com.interval_letter.model;
import java.util.List;

public interface Interval_LetterDAO_interface {
		public void insert(Interval_LetterVO interval_letterVO);
		public void update(Interval_LetterVO interval_letterVO);
		public void delete(String letter_no);
		public Interval_LetterVO findByPrimaryKey(String letter_no);
		public List<Interval_LetterVO> getAll();
}
