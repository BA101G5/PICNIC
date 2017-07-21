package com.manufacturers.model;

import java.util.List;

import com.general_member.model.GeneralMemberVO;

public interface ManufacturersDAO_interface {

	public void insert(ManufacturersVO MANUFACTURERSVO);
    public void update(ManufacturersVO MANUFACTURERSVO);
    public void delete(String MF_NO);
    public ManufacturersVO findByPrimaryKey(String MF_NO);
    public List<ManufacturersVO> getAll();
    public void updateforSTA(ManufacturersVO ManufacturersVO);
	public String findByMfName(String mf_name);
	//------------------------------------------------------
	public void k_insert(ManufacturersVO MANUFACTURERSVO);
    public void k_update(ManufacturersVO MANUFACTURERSVO);
    public void k_delete(String MF_NO);
    public ManufacturersVO k_findByPrimaryKey(String MF_NO);
    public List<ManufacturersVO> k_getAll();
}
