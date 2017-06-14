package com.manufacturers.model;

import java.util.List;



public interface ManufacturersDAO_interface {

	public void insert(ManufacturersVO MANUFACTURERSVO);
    public void update(ManufacturersVO MANUFACTURERSVO);
    public void delete(String MF_NO);
    public ManufacturersVO findByPrimaryKey(String MF_NO);
    public List<ManufacturersVO> getAll();
}
