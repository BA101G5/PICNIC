package com.buy_record.model;

import java.util.List;



public interface Buy_RecordDAO_interface {

	public void insert(Buy_RecordVO BUY_RECORDVO);
    public void update(Buy_RecordVO BUY_RECORDVO);
    public void delete(String BR_NO);
    public Buy_RecordVO findByPrimaryKey(String BR_NO);
    public List<Buy_RecordVO> getAll();
}
