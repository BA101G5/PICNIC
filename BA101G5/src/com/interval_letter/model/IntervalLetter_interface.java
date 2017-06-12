package com.interval_letter.model;

import java.util.List;

public interface IntervalLetter_interface {
    public void insert(IntervalLetterVO intervalLetterVO);
    public void update(IntervalLetterVO intervalLetterVO);
    public void delete(Integer intervalLetterVO);
    public IntervalLetterVO findByPrimaryKey(Integer intervalLetterVO);
    public List<IntervalLetterVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<IntervalLetterVO> getAll(Map<String, String[]> map); 
}
