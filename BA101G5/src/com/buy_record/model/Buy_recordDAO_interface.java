package buy_record.model;

import java.util.List;



public interface Buy_recordDAO_interface {

	public void insert(Buy_recordVO BUY_RECORDVO);
    public void update(Buy_recordVO BUY_RECORDVO);
    public void delete(String BR_NO);
    public Buy_recordVO findByPrimaryKey(String BR_NO);
    public List<Buy_recordVO> getAll();
}
