package com.buy_record.model;
import java.util.List;

public interface Buy_Record_interface {
	public void insert(Buy_RecordVO buy_recordVO);
	public void update(Buy_RecordVO buy_recordVO);
	public void delete(String br_id);
	public Buy_RecordVO findByPrimaryKey(String br_id);
	public List<Buy_RecordVO> getAll();
}
