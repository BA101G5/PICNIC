package manufacturers.model;

import java.util.List;

import com.genereal_member.model.GeneralMemberVO;

public interface ManufacturersDAO_interface {

	public void insert(ManufacturersVO MANUFACTURERSVO);
    public void update(ManufacturersVO MANUFACTURERSVO);
    public void delete(String MF_NO);
    public ManufacturersVO findByPrimaryKey(String MF_NO);
    public List<ManufacturersVO> getAll();
}
